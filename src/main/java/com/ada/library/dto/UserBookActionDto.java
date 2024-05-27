package com.ada.library.dto;

import com.ada.library.common.EActionType;
import com.ada.library.repository.entity.UserBookAction;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBookActionDto {
    private String id;
    private String idMongo;
    private LocalDateTime actionDate;
    private EActionType actionType;
    private UserDto user;
    private BookDto book;

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

