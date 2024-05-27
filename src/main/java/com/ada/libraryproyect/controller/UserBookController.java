package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.repository.entity.UserBook;
import com.ada.libraryproyect.service.impl.BookService;
import com.ada.libraryproyect.service.impl.UserBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userbooks")
public class UserBookController {
    private final UserBookService userBookService;

    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }


    @GetMapping
    public List<UserBook> getAllUsers() {
        return userBookService.getAll();
    }

    @GetMapping("/id")
    public Optional<UserBook> findById(@PathVariable String id) {
        return userBookService.findById(id);
    }
    @PostMapping
    public ResponseEntity<UserBook> createUser(@RequestBody UserBook userBook) {
        userBookService.save(userBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(userBook);
    }

    @DeleteMapping("/id")
    public void deleteById(@PathVariable String id) {
        userBookService.deleteById(id);
    }
}
