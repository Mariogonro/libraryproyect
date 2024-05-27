package com.ada.library.service.impl;

import com.ada.library.dto.RoleDto;
import com.ada.library.repository.entity.Role;
import com.ada.library.repository.mongo.RoleMongoRepository;
import com.ada.library.repository.postgres.RolePostgresRepository;
import com.ada.library.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RolePostgresRepository rolePostgresRepository;

    @Autowired
    private RoleMongoRepository roleMongoRepository;

    @Override
    public RoleDto createRole(RoleDto roleDto, String dbType) {
        Role role = Role.fromEntity(roleDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                role = rolePostgresRepository.save(role);
                break;
            case "MONGO":
                role = roleMongoRepository.save(role);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return Role.toEntity(role);
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, String dbType) {
        Role role = Role.fromEntity(roleDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                if (rolePostgresRepository.existsById(role.getId())) {
                    role = rolePostgresRepository.save(role);
                } else {
                    throw new RuntimeException("Role not found");
                }
                break;
            case "MONGO":
                if (roleMongoRepository.existsById(role.getIdMongo())) {
                    role = roleMongoRepository.save(role);
                } else {
                    throw new RuntimeException("Role not found");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return Role.toEntity(role);
    }

    @Override
    public void deleteRole(String roleId, String dbType) {
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(roleId);
                rolePostgresRepository.deleteById(id);
                break;
            case "MONGO":
                roleMongoRepository.deleteById(roleId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
    }

    @Override
    public Optional<RoleDto> getRoleById(String roleId, String dbType) {
        Optional<Role> role;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(roleId);
                role = rolePostgresRepository.findById(id);
                break;
            case "MONGO":
                role = roleMongoRepository.findById(roleId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return role.map(Role::toEntity);
    }

    @Override
    public List<RoleDto> getAllRoles(String dbType) {
        List<Role> roles;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                roles = rolePostgresRepository.findAll();
                break;
            case "MONGO":
                roles = roleMongoRepository.findAll();
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return roles.stream().map(Role::toEntity).collect(Collectors.toList());
    }
}
