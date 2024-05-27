package com.ada.library.repository.entity;

import com.ada.library.common.EActionType;
import com.ada.library.dto.BookDto;
import com.ada.library.dto.UserBookActionDto;
import com.ada.library.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_book_actions")
@Document(collection = "user_book_actions")
public class UserBookAction {

    @Transient
    @MongoId
    private String idMongo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime actionDate;

    @Enumerated(EnumType.STRING)
    private EActionType actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @DBRef
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @DBRef
    private Book book;

    public static UserBookAction fromEntity(UserBookActionDto userBookActionDto) {
        UserBookAction userBookAction = new UserBookAction();
        if (userBookActionDto.getId() != null) {
            try {
                userBookAction.setId(Long.parseLong(userBookActionDto.getId()));
            } catch (NumberFormatException e) {
                userBookAction.setIdMongo(userBookActionDto.getId());
            }
        }
        userBookAction.setIdMongo(userBookActionDto.getIdMongo());
        userBookAction.setActionDate(userBookActionDto.getActionDate());
        userBookAction.setActionType(userBookActionDto.getActionType());
        return userBookAction;
    }

    public static UserBookActionDto toEntity(UserBookAction userBookAction) {
        UserBookActionDto userBookActionDto = new UserBookActionDto();
        if (userBookAction.getId() != null) {
            userBookActionDto.setId(String.valueOf(userBookAction.getId()));
        } else {
            userBookActionDto.setId(userBookAction.getIdMongo());
        }
        userBookActionDto.setIdMongo(userBookAction.getIdMongo());
        userBookActionDto.setActionDate(userBookAction.getActionDate());
        userBookActionDto.setActionType(userBookAction.getActionType());
        return userBookActionDto;
    }
}

