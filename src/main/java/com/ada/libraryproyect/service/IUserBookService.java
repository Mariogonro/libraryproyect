package com.ada.libraryproyect.service;

import com.ada.libraryproyect.repository.entity.UserBook;

import java.util.List;
import java.util.Optional;

public interface IUserBookService {
    List<UserBook> getAll();
    Optional<UserBook> findById(String id);
    void deleteById(String id);
    void save(UserBook book);
}
