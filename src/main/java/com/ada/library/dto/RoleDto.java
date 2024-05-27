package com.ada.library.dto;

import com.ada.library.common.ERole;
import com.ada.library.repository.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String id;
    private String name;

    public static Role fromEntity(RoleDto roleDto) {
        Role role = new Role();
        if (roleDto.getId() != null) {
            try {
                role.setId(Long.parseLong(roleDto.getId()));
            } catch (NumberFormatException e) {
                role.setIdMongo(roleDto.getId());
            }
        }
        role.setName(ERole.valueOf(roleDto.getName()));
        return role;
    }

    public static RoleDto toEntity(Role role) {
        RoleDto roleDto = new RoleDto();
        if (role.getId() != null) {
            roleDto.setId(String.valueOf(role.getId()));
        } else {
            roleDto.setId(role.getIdMongo());
        }
        roleDto.setName(role.getName().name());
        return roleDto;
    }
}
