package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.constants.EStatus;
import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.repository.entity.User;
import com.ada.libraryproyect.repository.entity.UserBook;
import com.ada.libraryproyect.service.impl.UserBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class UserBookControllerTest {

    final String BASE_URL = "/api/userbooks";

    @MockBean
    private UserBookService userBookService;

    @Autowired
    private UserBookController userBookController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(userBookController).build();
    }

    @Test
    public void testGetAllUserBooks() throws Exception {
        User user1 = User.builder().id("1feasd541seaf45asfs89esbf").username("jgonzalez").email("jorge.gonzalez@example.com").password("password").enabled(true).build();
        User user2 = User.builder().id("2sfadhg784ri6utjhgtsrq815").username("jurias").email("jackelyn.urias@example.com").password("password").enabled(true).build();

        Book book1 = new Book("6651aa5b158789421363e2lb", "El Señor de los Anillos", "J.R.R. Tolkien", "Primera Edición", "1954", "Estantería A1", EStatus.DISPONIBLE);
        Book book2 = new Book("2651aa5b158789421363e2lb", "Yo, Robot", "Isaac Asimov", "Primera Edición", "1950", "Estantería B2", EStatus.RESERVADO);

        Set<User> users = new HashSet<>(Arrays.asList(user1, user2));
        Set<Book> books = new HashSet<>(Arrays.asList(book1, book2));

        UserBook userBook1 = new UserBook("urfai2s784ri6utlbgtsrq910", users, books, EStatus.DISPONIBLE);
        UserBook userBook2 = new UserBook("2rfai0s172ri6utlbmgrrq7se", users, books, EStatus.RESERVADO);

        List<UserBook> userBooks = Arrays.asList(userBook1, userBook2);
        when(userBookService.getAll()).thenReturn(userBooks);

        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(userBooks.size())))
                .andExpect(jsonPath("$[0].status", is(userBook1.getStatus().name())))
                .andExpect(jsonPath("$[1].status", is(userBook2.getStatus().name())));

        verify(userBookService, times(1)).getAll();
    }

    @Test
    public void testFindUserBookById() throws Exception {
        User user1 = User.builder().id("1feasd541seaf45asfs89esbf").username("jgonzalez").email("jorge.gonzalez@example.com").password("password").enabled(true).build();
        Book book1 = new Book("6651aa5b158789421363e2lb", "El Señor de los Anillos", "J.R.R. Tolkien", "Primera Edición", "1954", "Estantería A1", EStatus.DISPONIBLE);

        Set<User> users = new HashSet<>(Arrays.asList(user1));
        Set<Book> books = new HashSet<>(Arrays.asList(book1));

        UserBook userBook = new UserBook("urfai2s784ri6utlbgtsrq910", users, books, EStatus.DISPONIBLE);

        when(userBookService.findById("urfai2s784ri6utlbgtsrq910")).thenReturn(Optional.of(userBook));

        mockMvc.perform(get(BASE_URL + "/urfai2s784ri6utlbgtsrq910")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(userBook.getStatus().name())));

        verify(userBookService, times(1)).findById("urfai2s784ri6utlbgtsrq910");
    }

    @Test
    public void testCreateUserBook() throws Exception {
        User user1 = User.builder().id("1feasd541seaf45asfs89esbf").username("jgonzalez").email("jorge.gonzalez@example.com").password("password").enabled(true).build();
        Book book1 = new Book("6651aa5b158789421363e2lb", "El Señor de los Anillos", "J.R.R. Tolkien", "Primera Edición", "1954", "Estantería A1", EStatus.DISPONIBLE);

        Set<User> users = new HashSet<>(Arrays.asList(user1));
        Set<Book> books = new HashSet<>(Arrays.asList(book1));

        UserBook userBook = new UserBook("urfai2s784ri6utlbgtsrq910", users, books, EStatus.DISPONIBLE);

        doNothing().when(userBookService).save(any(UserBook.class));

        String json = "{\"users\":[{\"id\":\"1feasd541seaf45asfs89esbf\",\"username\":\"jgonzalez\",\"email\":\"jorge.gonzalez@example.com\",\"password\":\"password\",\"enabled\":true}],\"books\":[{\"id\":\"6651aa5b158789421363e2lb\",\"title\":\"El Señor de los Anillos\",\"author\":\"J.R.R. Tolkien\",\"edition\":\"Primera Edición\",\"publicationDate\":\"1954\",\"location\":\"Estantería A1\",\"status\":\"DISPONIBLE\"}],\"status\":\"DISPONIBLE\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(userBookService, times(1)).save(any(UserBook.class));
    }

    @Test
    public void testDeleteUserBookById() throws Exception {
        doNothing().when(userBookService).deleteById("urfai2s784ri6utlbgtsrq910");

        mockMvc.perform(delete(BASE_URL + "/urfai2s784ri6utlbgtsrq910")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userBookService, times(1)).deleteById("urfai2s784ri6utlbgtsrq910");
    }


}