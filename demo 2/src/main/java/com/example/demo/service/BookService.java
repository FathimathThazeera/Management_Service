package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.exceptions.DuplicateKeyException;
import com.example.demo.repository.table.BookRepository;
import com.example.demo.repository.table.BookTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getMap() {
        return bookRepository.findAll().stream().map(BookTable::toBook).collect(Collectors.toList());
    }

    public void insert(Book book) {
        if (bookRepository.existsById(book.getId())) {
            log.warn("Duplicate message is trying to be inserted");
            throw new DuplicateKeyException();
        }
        bookRepository.save(book.toBookTable());
    }

    public Book getById(long id) {
        Optional<BookTable> optionalBookTable = bookRepository.findById(id);
        if (!optionalBookTable.isPresent()) {
            log.warn("Book not found");
            throw new BookNotFoundException();
        }
        return optionalBookTable.get().toBook();
    }

    public void update(Book book) {
        Optional<BookTable> oldBookOptional = bookRepository.findById(book.getId());
        if (!oldBookOptional.isPresent()) {
            log.warn("Book not found while updating");
            throw new BookNotFoundException();
        }
        BookTable newBook = book.toBookTable();
        newBook.setCreatedAt(oldBookOptional.get().getCreatedAt());
        bookRepository.save(newBook);
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
            log.warn("Invalid ID");
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(id);
    }
}
