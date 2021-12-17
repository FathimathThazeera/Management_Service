package com.example.demo.entity;

import java.time.Year;

public class Asset {
    private long id;
    private String name;
    private int amount;
    private Year make;
    private Type type;
    private Brand brand;

    public Asset(long id, String name, int amount, Year make, Type type, Brand brand ) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.make = make;
        this.type = type;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int cost) {
        this.amount = amount;
    }

    public Year getMake() {
        return make;
    }

    public void setMake(Year make) {
        this.make = make;
    }

    public Type getType(){return  type;}

    public  void setType(Type type){this.type = type;}

    public Brand getBrand(){return brand;}

    public void setBrand(Brand brand){this.brand = brand;}
}
