package com.ada.libraryproyect.service.impl;

import com.ada.libraryproyect.repository.RoleRepository;
import com.ada.libraryproyect.repository.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleService implements com.ada.libraryproyect.service.RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
