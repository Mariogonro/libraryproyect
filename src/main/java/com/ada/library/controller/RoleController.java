package com.ada.library.controller;

import com.ada.library.dto.RoleDto;
import com.ada.library.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(@RequestParam String dbType) {
        List<RoleDto> roles = roleService.getAllRoles(dbType);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable String roleId, @RequestParam String dbType) {
        return roleService.getRoleById(roleId, dbType)
                .map(roleDto -> new ResponseEntity<>(roleDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto, @RequestParam String dbType) {
        RoleDto createdRole = roleService.createRole(roleDto, dbType);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable String roleId, @RequestBody RoleDto roleDto, @RequestParam String dbType) {
        roleDto.setId(roleId);
        RoleDto updatedRole = roleService.updateRole(roleDto, dbType);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable String roleId, @RequestParam String dbType) {
        roleService.deleteRole(roleId, dbType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
