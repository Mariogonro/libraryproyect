package com.ada.library.controller;

import com.ada.library.dto.UserBookActionDto;
import com.ada.library.service.impl.UserBookActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userbookactions")
public class UserBookActionController {

    private final UserBookActionService userBookActionService;

    @Autowired
    public UserBookActionController(UserBookActionService userBookActionService) {
        this.userBookActionService = userBookActionService;
    }

    @GetMapping
    public ResponseEntity<List<UserBookActionDto>> getAllUserBookActions(@RequestParam String dbType) {
        List<UserBookActionDto> userBookActions = userBookActionService.getAllUserBookActions(dbType);
        return new ResponseEntity<>(userBookActions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookActionDto> getUserBookActionById(@PathVariable String userBookActionId, @RequestParam String dbType) {
        return userBookActionService.getUserBookActionById(userBookActionId, dbType)
                .map(userBookActionDto -> new ResponseEntity<>(userBookActionDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserBookActionDto> createUserBookAction(@RequestBody UserBookActionDto userBookActionDto, @RequestParam String dbType) {
        UserBookActionDto createdUserBookAction = userBookActionService.createUserBookAction(userBookActionDto, dbType);
        return new ResponseEntity<>(createdUserBookAction, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBookActionDto> updateUserBookAction(@PathVariable String userBookActionId, @RequestBody UserBookActionDto userBookActionDto, @RequestParam String dbType) {
        userBookActionDto.setId(userBookActionId);
        UserBookActionDto updatedUserBookAction = userBookActionService.updateUserBookAction(userBookActionDto, dbType);
        return new ResponseEntity<>(updatedUserBookAction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserBookAction(@PathVariable String userBookActionId, @RequestParam String dbType) {
        userBookActionService.deleteUserBookAction(userBookActionId, dbType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

