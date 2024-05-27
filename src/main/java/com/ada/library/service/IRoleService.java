package com.ada.library.service;

import com.ada.library.dto.RoleDto;
import java.util.List;
import java.util.Optional;

public interface IRoleService {
    RoleDto createRole(RoleDto roleDto, String dbType);

    RoleDto updateRole(RoleDto roleDto, String dbType);

    void deleteRole(String roleId, String dbType);

    Optional<RoleDto> getRoleById(String roleId, String dbType);

    List<RoleDto> getAllRoles(String dbType);
}

