package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BookService {
    private Map<Long, Book> map = new HashMap<>();

    public Map<Long, Book> getMap() {
        return map;
    }

    public boolean insert(Book book){
        if (map.containsKey(book.getId())) {
            throw new DuplicateKeyException();
        }
        map.put(book.getId(), book);
        return true;
    }

    public Book getById(long id){
        Book book = map.get(id);
        return book;
    }

    public boolean update(Book book){
        if(!map.containsKey(book.getId()))
        {
            throw new BookNotFoundException();
        }
        map.put(book.getId(), book);
        return true;
    }

    public boolean delete(long id){
        if (!map.containsKey(id)) {
            throw new BookNotFoundException();
        }
        map.remove(id);
        return true;
    }
}
