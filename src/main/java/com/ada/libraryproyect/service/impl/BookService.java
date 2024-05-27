package com.ada.libraryproyect.service.impl;

import com.ada.libraryproyect.repository.BookRepository;
import com.ada.libraryproyect.repository.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
    public void save(Book book) {
        bookRepository.save(book);
    }
}
