package com.ada.libraryproyect.service.impl;

import com.ada.libraryproyect.repository.UserBookRepository;
import com.ada.libraryproyect.repository.entity.UserBook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookService {
    private final UserBookRepository userBookRepository;

    public UserBookService(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    public List<UserBook> getAll() {
        return userBookRepository.findAll();
    }

    public Optional<UserBook> findById(String id) {
        return userBookRepository.findById(id);
    }
    public void deleteById(String id) {
        userBookRepository.deleteById(id);
    }
    public void save(UserBook book) {

        userBookRepository.save(book);
    }
}
