package com.ada.libraryproyect.service;

import com.ada.libraryproyect.repository.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAll();
    Optional<Book> findById(String id);
    void deleteById(String id);
    void save(Book book);
    Book update(String id, Book book);
}
