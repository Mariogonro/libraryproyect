package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.repository.entity.Role;
import com.ada.libraryproyect.repository.entity.User;
import com.ada.libraryproyect.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

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
    public void testGetAllUsers() throws Exception {
        Role role = new Role();
        role.setId("6651aa5b158789421363e2cd");
        role.setName(ERole.ROLE_USER);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user1 = User.builder().id("1feasd541seaf45asfs89esbf").username("jgonzalez").email("jorge.gonzalez@example.com").password("password").enabled(true).roles(roles).build();
        User user2 = User.builder().id("2sfadhg784ri6utjhgtsrq815").username("jurias").email("jackelyn.urias@example.com").password("password").enabled(true).roles(roles).build();

        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(users.size())))
                .andExpect(jsonPath("$[0].username", is(user1.getUsername())))
                .andExpect(jsonPath("$[1].username", is(user2.getUsername())));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testCreateUser() throws Exception {
        Role role = new Role();
        role.setId("6651aa5b158789421363e2cd");
        role.setName(ERole.ROLE_USER);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = User.builder().id("1feasd541seaf45asfs89esbf").username("jgonzalez").email("jorge.gonzalez@example.com").password("password").enabled(true).roles(roles).build();

        doNothing().when(userService).save(any(User.class));

        String json = "{\"username\":\"jgonzalez\",\"email\":\"jorge.gonzalez@example.com\",\"password\":\"password\",\"enabled\":true,\"roles\":[{\"id\":\"" + role.getId() + "\",\"name\":\"ROLE_USER\"}]}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        verify(userService, times(1)).save(any(User.class));
    }

}
