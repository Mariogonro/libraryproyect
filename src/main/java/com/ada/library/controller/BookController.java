package com.ada.library.controller;

import com.ada.library.dto.BookDto;
import com.ada.library.service.impl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam String dbType) {
        List<BookDto> books = bookService.getAllBooks(dbType);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String bookId, @RequestParam String dbType) {
        return bookService.getBookById(bookId, dbType)
                .map(bookDto -> new ResponseEntity<>(bookDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, @RequestParam String dbType) {
        BookDto createdBook = bookService.createBook(bookDto, dbType);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String bookId, @RequestBody BookDto bookDto, @RequestParam String dbType) {
        bookDto.setId(bookId);
        BookDto updatedBook = bookService.updateBook(bookDto, dbType);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId, @RequestParam String dbType) {
        bookService.deleteBook(bookId, dbType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
