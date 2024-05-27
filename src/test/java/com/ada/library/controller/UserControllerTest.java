package com.ada.library.controller;
import com.ada.library.dto.RoleDto;
import com.ada.library.dto.UserDto;
import com.ada.library.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class UserControllerTest {

    final String BASE_URL = "/api/users";

    @MockBean
    private UserService userService;
    @Autowired
    private UserController userController;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void getAllUsersPostgres() throws Exception {
        testGetAllUsers("POSTGRES");
    }

    @Test
    public void getAllUsersMongo() throws Exception {
        testGetAllUsers("MONGO");
    }

    private void testGetAllUsers(String dbType) throws Exception {
        UserDto user1 = new UserDto();
        UserDto user2 = new UserDto();
        RoleDto role = new RoleDto();

       if (dbType.toUpperCase() == "MONGO"){
           role.setId("6651aa5b158789421363e2cd");
           role.setName("ROLE_USER");
            user1 = new UserDto("1feasd541seaf45asfs89esbf", "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
            user2 = new UserDto("2sfadhg784ri6utjhgtsrq815", "Jackelyn", "Urías", "jurias", "jackelyn.urias@example.com", "password", true, role);

        }
        if (dbType.toUpperCase() == "POSTGRES"){
            role.setId("3");
            role.setName("ROLE_USER");
            user1 = new UserDto("1", "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
            user2 = new UserDto("2", "Jackelyn", "Urías", "jurias", "jackelyn.urias@example.com", "password", true, role);

        }
        List<UserDto> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers(dbType)).thenReturn(users);

        mockMvc.perform(get(BASE_URL)
                        .param("dbType", dbType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(users.size())))
                .andExpect(jsonPath("$[0].username", is(user1.getUsername())))
                .andExpect(jsonPath("$[1].username", is(user2.getUsername())));

        verify(userService, times(1)).getAllUsers(dbType);
    }


    @Test
    public void getUserByIdPostgres() throws Exception {
        testGetUserById("POSTGRES");
    }

    @Test
    public void getUserByIdMongo() throws Exception {
        testGetUserById("MONGO");
    }

    private void testGetUserById(String dbType) throws Exception {
        UserDto user = new UserDto();
        RoleDto role = new RoleDto();
        String userId = "";

        if (dbType.equalsIgnoreCase("MONGO")) {
            role.setId("6651aa5b158789421363e2cd");
            role.setName("ROLE_USER");
            userId = "1feasd541seaf45asfs89esbf";
            user = new UserDto(userId, "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
        }
        if (dbType.equalsIgnoreCase("POSTGRES")) {
            role.setId("3");
            role.setName("ROLE_USER");
            userId = "1";
            user = new UserDto(userId, "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
        }

        when(userService.getUserById(userId, dbType)).thenReturn(Optional.of(user));

        mockMvc.perform(get(BASE_URL + "/{id}", userId)
                        .param("dbType", dbType)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.username", is(user.getUsername())));

        verify(userService, times(1)).getUserById(userId, dbType);
    }





    @Test
    public void createUserPostgres() throws Exception {
        testCreateUser("POSTGRES");
    }

    @Test
    public void createUserMongo() throws Exception {
        testCreateUser("MONGO");
    }

    private void testCreateUser(String dbType) throws Exception {
        UserDto user = new UserDto();
        RoleDto role = new RoleDto();
        String userId = "";

        if (dbType.equalsIgnoreCase("MONGO")) {
            role.setId("6651aa5b158789421363e2cd");
            role.setName("ROLE_USER");
            userId = "1feasd541seaf45asfs89esbf";
            user = new UserDto(userId, "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
        }
        if (dbType.equalsIgnoreCase("POSTGRES")) {
            role.setId("3");
            role.setName("ROLE_USER");
            userId = "1";
            user = new UserDto(userId, "Jorge", "Gonzaléz", "jgonzalez", "jorge.gonzalez@example.com", "password", true, role);
        }

        when(userService.createUser(any(), eq(dbType))).thenReturn(user);
        String json = "{\"id\":\"null\",\"name\":\"Jorge\",\"lastName\":\"Gonzaléz\",\"username\":\"jgonzalez\",\"email\":\"jorge.gonzalez@example.com\",\"password\":\"password\",\"enabled\":true,\"role\":{\"id\":\"" + role.getId() + "\",\"name\":\"ROLE_USER\"}}";

        mockMvc.perform(post(BASE_URL)
                        .param("dbType", dbType)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        verify(userService, times(1)).createUser(any(), eq(dbType));
    }

}
