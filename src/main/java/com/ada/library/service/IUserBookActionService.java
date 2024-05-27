package com.ada.library.service;

import com.ada.library.dto.UserBookActionDto;
import java.util.List;
import java.util.Optional;

public interface IUserBookActionService {
    UserBookActionDto createUserBookAction(UserBookActionDto userBookActionDto, String dbType);

    UserBookActionDto updateUserBookAction(UserBookActionDto userBookActionDto, String dbType);

    void deleteUserBookAction(String userBookActionId, String dbType);

    Optional<UserBookActionDto> getUserBookActionById(String userBookActionId, String dbType);

    List<UserBookActionDto> getAllUserBookActions(String dbType);
}
