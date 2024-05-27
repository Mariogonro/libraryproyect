package com.ada.library.service;

import com.ada.library.dto.BookDto;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    BookDto createBook(BookDto bookDto, String dbType);

    BookDto updateBook(BookDto bookDto, String dbType);

    void deleteBook(String bookId, String dbType);

    Optional<BookDto> getBookById(String bookId, String dbType);

    List<BookDto> getAllBooks(String dbType);
}

