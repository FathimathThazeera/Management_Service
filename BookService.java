package com.example.demo.service;

import com.example.demo.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

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
            return false;
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
            return false;
        }
        map.put(book.getId(), book);
        return true;
    }

    public boolean delete(long id){
        if (!map.containsKey(id)) {
            return false;
        }
        map.remove(id);
        return true;
    }
}
