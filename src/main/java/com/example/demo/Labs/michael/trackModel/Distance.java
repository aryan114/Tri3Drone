package com.example.demo.Labs.michael.trackModel;

public class Distance extends Athlete {
    public enum keyType {name, grade, gender, school, seedTime, split, event}
    public static keyType key = keyType.name;

    private final double split;
    private final String event;

    public Distance(String name, int grade, String gender, String school, double seedTime, double split, String event){
        super(name, grade, gender, school, seedTime);
        this.split = split;
        this.event = event;
    }

    @Override
    public String toString() {
        String formattedString = "";
        switch (key) {
            case name:
                formattedString = super.getName();
                break;
            case grade:
                formattedString += "00" + super.getGrade();
                break;
            case school:
                formattedString = super.getSchool();
                break;
            case seedTime:
                formattedString += super.getSeedTime();
                break;
            case split:
                formattedString += this.split;
                break;
            case event:
                formattedString += this.event;
            default:
                formattedString = super.getType() + ": " + super.getName() + " " + super.getGrade() + " " + super.getSchool() + " " + this.event + " " + this.split + " " + super.getSeedTime();
                break;
        }
        return formattedString;
    }
}
