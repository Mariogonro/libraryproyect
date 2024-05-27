package com.ada.libraryproyect.repository.factory;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.handler.exception.RoleNotFoundException;
import com.ada.libraryproyect.repository.RoleRepository;
import com.ada.libraryproyect.repository.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(ERole.ROLE_ADMIN);
            }
            case "user" -> {
                return roleRepository.findByName(ERole.ROLE_USER);
            }
            case "super_admin" -> {
                return roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
            }
            default -> throw  new RoleNotFoundException("No role found for " +  role);
        }
    }
}

