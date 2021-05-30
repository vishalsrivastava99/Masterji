package com.example.Masterji;

public class model {
    String name,college,image,fees;

    public model() {
    }

    public model(String name, String college, String image,String fees) {
        this.name = name;
        this.college = college;
        this.image = image;
        this.fees = fees;

    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public String getImage() {
        return image;
    }

    public String getFees() {
        return fees;
    }
}
