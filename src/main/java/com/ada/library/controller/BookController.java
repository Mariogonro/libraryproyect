package com.ada.library.controller;

import com.ada.library.repository.entity.Book;
import com.ada.library.repository.entity.BookDTO;
import com.ada.library.service.BookServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookServiceMap bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(@RequestParam("dbType") String dbType) {
        switch (dbType.toUpperCase()) {
            case "MONGO":
                List<Book> booksM = bookService.allMongo();
                List<BookDTO> bookDTOsM = booksM.stream().map(BookDTO::bookDTO).toList();
                return new ResponseEntity<>(bookDTOsM, HttpStatus.OK);
            case "POSTGRES":
                List<Book> booksP = bookService.allPostgres();
                List<BookDTO> bookDTOsP = booksP.stream().map(BookDTO::bookDTO).toList();
                return new ResponseEntity<>(bookDTOsP, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@RequestParam("dbType") String dbType, @PathVariable String id) {
        switch (dbType.toUpperCase()) {
            case "MONGO":
                Optional<Book> bookM = bookService.findByIdMongo(id);
                return bookM.map(book -> new ResponseEntity<>(BookDTO.bookDTO(book), HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            case "POSTGRES":
                Long postgresId;
                try {
                    postgresId = Long.parseLong(id);
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                }
                Optional<Book> bookP = bookService.findByIdPostgres(postgresId);
                return bookP.map(book -> new ResponseEntity<>(BookDTO.bookDTO(book), HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            default:
                return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestParam("dbType") String dbType, @RequestBody BookDTO bookDTO) {
        try {
            Book book = Book.book(bookDTO);
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    Book createdBookM = bookService.saveMongo(book);
                    return new ResponseEntity<>(BookDTO.bookDTO(createdBookM), HttpStatus.CREATED);
                case "POSTGRES":
                    Book createdBookP = bookService.savePostgres(book);
                    return new ResponseEntity<>(BookDTO.bookDTO(createdBookP), HttpStatus.CREATED);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestParam("dbType") String dbType, @PathVariable String id, @RequestBody BookDTO bookDTO) {
        try {
            Book book = Book.book(bookDTO);
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    Book updatedBookM = bookService.updateMongo(book, id);
                    return new ResponseEntity<>(BookDTO.bookDTO(updatedBookM), HttpStatus.OK);
                case "POSTGRES":
                    Long postgresId;
                    try {
                        postgresId = Long.parseLong(id);
                    } catch (NumberFormatException e) {
                        return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                    }
                    Book updatedBookP = bookService.updatePostgres(book, postgresId);
                    return new ResponseEntity<>(BookDTO.bookDTO(updatedBookP), HttpStatus.OK);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@RequestParam("dbType") String dbType, @PathVariable String id) {
        try {
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    bookService.deleteByIdMongo(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                case "POSTGRES":
                    Long postgresId;
                    try {
                        postgresId = Long.parseLong(id);
                    } catch (NumberFormatException e) {
                        return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                    }
                    bookService.deleteByIdPostgres(postgresId);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
