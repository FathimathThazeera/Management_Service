package com.example.demo.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

public class Book {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String author;
    @NotNull
    private Year publishedYear;
    @Min(0)
    @NotNull
    private Integer cost;

    public Book(long id, String name, String author, Year publishedYear, Integer cost) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.cost = cost;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Year publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
