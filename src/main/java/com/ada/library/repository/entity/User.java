package com.ada.library.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Document(collection = "users")
public class User {

    @Transient // Ignorado por JPA para persistencia en base de datos relacional
    @MongoId // ID para MongoDB
    private String idMongo;

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID para PostgreSQL
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private char status;

    public static UserDTO userDTO(User user) {
        UserDTO dto = new UserDTO();
        if (user.getId() != null) {
            dto.setId(String.valueOf(user.getId()));
        } else {
            dto.setId(user.getIdMongo());
        }
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setStatus(user.getStatus());
        return dto;
    }

    public static User user(UserDTO dto) {
        User user = new User();
        if (dto.getId() != null) {
            try {
                Long id = Long.parseLong(dto.getId());
                user.setId(id);
            } catch (NumberFormatException e) {
                user.setIdMongo(dto.getId());
            }
        }
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStatus(dto.getStatus());
        return user;
    }
}
