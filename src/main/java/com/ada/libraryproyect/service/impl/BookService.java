package com.ada.libraryproyect.service.impl;

import com.ada.libraryproyect.repository.BookRepository;
import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book update(String id, Book book){
        if (bookRepository.existsById(id)){
            return bookRepository.save(book);
        } else {
           throw new RuntimeException("Book not found");
        }
    }
}
