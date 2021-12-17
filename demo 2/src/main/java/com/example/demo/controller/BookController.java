package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;

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
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        boolean res = bookService.insert(book);
        if (res) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Book book) {
        boolean res = bookService.update(book);
        if (!res) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
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