package com.example.demo.controller;

import com.example.demo.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private Map<Long, Book> map = new HashMap<>();

    @GetMapping("/all")
    public ResponseEntity<Collection<Book>> getAll() {
        return ResponseEntity.ok(map.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id) {
        Book book = map.get(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        if (map.containsKey(book.getId())) {
            return ResponseEntity.badRequest().build();
        }
        map.put(book.getId(), book);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Book book) {
        if (!map.containsKey(book.getId())) {
            return ResponseEntity.badRequest().build();
        }
        map.put(book.getId(), book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!map.containsKey(id)) {
            return ResponseEntity.ok().build();
        }
        map.remove(id);
        return ResponseEntity.ok().build();
    }

}
