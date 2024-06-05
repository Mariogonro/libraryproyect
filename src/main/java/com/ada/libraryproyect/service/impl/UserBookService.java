package com.ada.libraryproyect.service.impl;

import com.ada.libraryproyect.repository.UserBookRepository;
import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.repository.entity.UserBook;
import com.ada.libraryproyect.service.IUserBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookService implements IUserBookService {
    private final UserBookRepository userBookRepository;

    public UserBookService(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    @Override
    public List<UserBook> getAll() {
        return userBookRepository.findAll();
    }

    @Override
    public Optional<UserBook> findById(String id) {
        return userBookRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        userBookRepository.deleteById(id);
    }

    @Override
    public void save(UserBook userBook) {

        userBookRepository.save(userBook);
    }


    @Override
    public UserBook update(String id, UserBook userBook){
        if (userBookRepository.existsById(id)){
            return userBookRepository.save(userBook);
        } else {
            throw new RuntimeException("UserBook not found");
        }
    }
}
