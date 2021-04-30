package com.example.demo.Labs.PranavLinkedList;
import com.example.demo.Labs.PranavInheritence.*;
import com.example.demo.Labs.PranavInheritence.ConsoleMethods.ConsoleMethods;
import com.example.demo.Labs.PranavInheritence.Technicals.CircleQueue;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Controller
public class PranavLinkedList {

    public int sorttime;
    Duration a;

    private CircleQueue queue;
    private int count;


    private Car.KeyType carKey;
    private boolean car;

    public PranavLinkedList(){
        count = 0;
        queue = new CircleQueue();
    }

    public void addCQueue(Object[] objects){
        ConsoleMethods.println("Add " + objects.length);
        for(Object o: objects){
            queue.addobj(o);
            ConsoleMethods.println("Add: " + queue.getObject() + " " + queue);
            this.count++;
        }
    }

    public void deleteCQueue(){
        int length = this.count;
        ConsoleMethods.println("Delete "+ length);

        for (int i = 0; i<length; i++){
            ConsoleMethods.println("Delete: " + queue.deleteobj() + " " + queue);
            this.count--;
        }
    }

    public List<String> getCQList(){
        List<String> log = new ArrayList<>();
        Object first = queue.getFirst();
        do{
            log.add(queue.getObject().toString());
        } while (queue.setnext() != first);
        return log;
    }

    @GetMapping("/PranavLL")
    public String data(Model model){
        this.count = 0;
        this.queue = new CircleQueue();

        this.carKey = Car.KeyType.title;
        Car.key = this.carKey;


        this.car = true;

        this.addCQueue(Car.carData());

        model.addAttribute("ctl", this);
        return "labs/PranavLLLab"; //NEED TO CHANGE
    }

    @PostMapping("/PranavLL")
    public String dataFilter(
            @RequestParam(value = "car", required = false) String car,
            @RequestParam(value = "carKey",required = false) Car.KeyType carKey,
            Model model){

        count = 0;
        queue = new CircleQueue();


        if(car != null){
            this.addCQueue(Car.carData());
            this.car = true;
            this.carKey = carKey;
            Car.key = this.carKey;
        } else{
            this.car = false;
        }
        Instant startCalc = Instant.now();
        this.queue.InsertionSort();
        Instant endCalc = Instant.now();
        a = Duration.between(startCalc,endCalc);
        sorttime = a.getNano();
        model.addAttribute("ctl", this);
        model.addAttribute("time",sorttime);
        return "labs/PranavLLLab"; //NEED TO CHANGE
    }

    public void printCQueue(){
        ConsoleMethods.println("Size: " + count);
        ConsoleMethods.println("First Element: " + queue.getFirst());
        ConsoleMethods.println("Last Element: " + queue.getLast());
        ConsoleMethods.println("Full cqueue: " + queue);
        for (String line : this.getCQList()) {
            ConsoleMethods.println(line);
        }
        ConsoleMethods.println();

    }

    public static void main(String[] args) {
        PranavLinkedList trial = new PranavLinkedList();

        trial.addCQueue(Car.carData());
        ConsoleMethods.println("Add order (all data)");
        trial.printCQueue();

        Car.key = Car.KeyType.name;
        trial.queue.InsertionSort();
        ConsoleMethods.println("Sorted order (key only)");
        trial.printCQueue();

        Car.key = Car.KeyType.title;
        ConsoleMethods.println("Retain sorted order (all data)");
        trial.printCQueue();
        trial.queue.InsertionSort();
        ConsoleMethods.println("Order by data type (all data)");
        trial.printCQueue();

        ConsoleMethods.println("Delete from front (all data)");
        trial.deleteCQueue();
    }


}
