package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.repository.entity.Role;
import com.ada.libraryproyect.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<Role> createUser(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
}