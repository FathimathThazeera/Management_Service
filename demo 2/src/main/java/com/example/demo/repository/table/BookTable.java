package com.example.demo.repository.table;

import com.example.demo.entity.Book;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.Year;


@Entity
@Data
@Table(name = "book_tbl")
@NoArgsConstructor

public class BookTable {
    @Id
    private Long id;
    private String name;
    private String author;
    @Column(name = "published_year")
    private String publishedYear;
    private int cost;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public BookTable(Long id, String name, String author, Year publishedYear, int cost) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear.toString();
        this.cost = cost;
    }

    public Book toBook() {
        return new Book(this.id, this.name, this.author, Year.parse(this.publishedYear), this.cost);
    }
}
