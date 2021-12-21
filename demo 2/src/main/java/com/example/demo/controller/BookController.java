package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;


@RestController
@RequestMapping("/book")
public class BookController {

    //private Map<Long, Book> map = new HashMap<>();
    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Collection<Book>> getAll() {
        return ResponseEntity.ok(bookService.getMap().values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Book book) {
        bookService.insert(book);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean res = bookService.delete(id);
        if (!res) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }

    }
}