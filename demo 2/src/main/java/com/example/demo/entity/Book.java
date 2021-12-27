package com.example.demo.entity;

import com.example.demo.repository.table.BookTable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Data

public class Book {
    @NotNull
    private final Long id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String author;
    @NotNull
    private final Year publishedYear;
    @Min(0)
    @NotNull
    private final Integer cost;


    public BookTable toBookTable() {
        return new BookTable(this.id, this.name, this.author, this.publishedYear, this.cost);
    }
}


