package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class BookService {
    private Map<Long, Book> map = new HashMap<>();

    public Map<Long, Book> getMap() {
        return map;
    }

    public void insert(Book book) {
        if (map.containsKey(book.getId())) {
            log.warn("Duplicate message is trying to be inserted");
            throw new DuplicateKeyException();
        }
        map.put(book.getId(), book);
    }

    public Book getById(long id) {
        if (!map.containsKey(id)) {
            log.warn("Book not found");
            throw new BookNotFoundException();
        }
        return map.get(id);
    }

    public void update(Book book) {
        if (!map.containsKey(book.getId())) {
            log.warn("Book not found while updating");
            throw new BookNotFoundException();
        }
        map.put(book.getId(), book);
    }

    public void delete(long id) {
        if (!map.containsKey(id)) {
            log.warn("Invalid ID");
            throw new BookNotFoundException();
        }
        map.remove(id);
    }
}
