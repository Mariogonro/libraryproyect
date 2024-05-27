package com.ada.library.repository.entity;

import com.ada.library.common.ERole;
import com.ada.library.dto.RoleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Document(collection = "roles")
public class Role {

    @Transient
    @MongoId
    private String idMongo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

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
