package com.example.demo.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

public class Asset {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @Min(0)
    @NotNull
    private Integer amount;
    @NotNull
    private Year make;
    @NotNull
    private Type type;
    @NotNull
    private Brand brand;

    public Asset(long id, String name, Integer amount, Year make, Type type, Brand brand) {
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
