package com.ada.libraryproyect.service;

import com.ada.libraryproyect.repository.entity.Role;

import java.util.List;

public interface IRoleService {
    void save(Role role);
    List<Role> getAllRoles();
}
