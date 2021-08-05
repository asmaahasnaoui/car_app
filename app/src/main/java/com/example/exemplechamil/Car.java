package com.example.exemplechamil;

public class Car {
    private int id;
    private String model;
    private String color;
    private int dbl;
    private String image;
    private String dsecription;

    public Car(int id, String model, String color, int dbl, String image, String description) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.dbl = dbl;
        this.image = image;
        this.dsecription = description;
    }
    public Car( String model, String color, int dpl, String image, String description) {
        this.model = model;
        this.color = color;
        this.dbl = dpl;
        this.image = image;
        this.dsecription = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDbl() {
        return dbl;
    }

    public void setDbl(int dbl) {
        this.dbl = dbl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDsecription() {
        return dsecription;
    }

    public void setDsecription(String description) {
        this.dsecription = description;
    }
}
