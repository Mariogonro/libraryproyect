package com.ada.library.dto;

import com.ada.library.repository.entity.Role;
import com.ada.library.repository.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private RoleDto role;

    public static User fromEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            try {
                user.setId(Long.parseLong(userDto.getId()));
            } catch (NumberFormatException e) {
                user.setIdMongo(userDto.getId());
            }
        }
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.isEnabled());
        user.setRole(Role.fromEntity(userDto.getRole()));
        return user;
    }

    public static UserDto toEntity(User user) {
        UserDto userDto = new UserDto();
        // Lógica para asignar el ID basada en la base de datos
        if (user.getId() != null) {
            userDto.setId(String.valueOf(user.getId()));
        } else {
            userDto.setId(user.getIdMongo());
        }
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setEnabled(user.isEnabled());
        userDto.setRole(RoleDto.toEntity(user.getRole()));
        return userDto;
    }
}