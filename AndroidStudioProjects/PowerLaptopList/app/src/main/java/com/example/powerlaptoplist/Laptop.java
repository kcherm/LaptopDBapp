package com.example.powerlaptoplist;

import java.util.Calendar;

public class Laptop {

    private int laptopID;
    private String name;
    private String winversion;
    private String makemodel;
    private String processor;

    public Laptop() {
        laptopID = -1;
    }

    public int getLaptopID() {
        return laptopID;
    }

    public void setLaptopID(int laptopID) {
        this.laptopID = laptopID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getwinVersion() {
        return winversion;
    }

    public void setwinVersion(String winversion) {
        this.winversion = winversion;
    }

    public String getmakeModel() {
        return makemodel;
    }

    public void setmakeModel(String makemodel) {
        this.makemodel = makemodel;
    }


    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

}


