package com.ada.library.repository.entity;

import com.ada.library.common.ERole;
import com.ada.library.dto.RoleDto;
import com.ada.library.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Document(collection = "users")
public class User {

    @Transient
    @MongoId
    private String idMongo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean enabled;

    @DBRef
    @ManyToOne
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;


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
