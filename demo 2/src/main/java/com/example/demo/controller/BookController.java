package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Collection<Book>> getAll() {
        log.info("Received a request to get all book info ");
        return ResponseEntity.ok(bookService.getMap().values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id) {
        log.info("Received a request to get book by its ID : {}", id);
        Book book = bookService.getById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.OK)
    public void insert(@RequestBody @Valid Book book) {
        log.info("Received a request to insert book : {}", book);
        bookService.insert(book);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Book book) {
        log.info("Received a request to update book : {}", book);
        bookService.update(book);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        log.info("Received a request to delete book whose book ID is : {}", id);
        bookService.delete(id);
    }
}