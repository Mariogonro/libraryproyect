package com.ada.libraryproyect.controller;
import com.ada.libraryproyect.constants.EStatus;
import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.service.impl.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class BookControllerTest {

    final String BASE_URL = "/api/books";

    @MockBean
    private BookService bookService;
    @Autowired
    private BookController bookController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(bookController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book("6651aa5b158789421363e2lb", "El Señor de los Anillos", "J.R.R. Tolkien", "Primera Edición", "1954", "Estantería A1", EStatus.DISPONIBLE);
        Book book2 = new Book("2651aa5b158789421363e2lb", "Yo, Robot", "Isaac Asimov", "Primera Edición", "1950", "Estantería B2", EStatus.DISPONIBLE);

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.getAll()).thenReturn(books);

        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(books.size())))
                .andExpect(jsonPath("$[0].title", is(book1.getTitle())))
                .andExpect(jsonPath("$[1].title", is(book2.getTitle())));

        verify(bookService, times(1)).getAll();
    }

    @Test
    public void testFindBookById() throws Exception {
        Book book = new Book("6651aa5b158789421363e2lb", "El Señor de los Anillos", "J.R.R. Tolkien", "Primera Edición", "1954", "Estantería A1", EStatus.DISPONIBLE);

        when(bookService.findById("6651aa5b158789421363e2lb")).thenReturn(Optional.of(book));

        mockMvc.perform(get(BASE_URL + "/6651aa5b158789421363e2lb")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(book.getTitle())))
                .andExpect(jsonPath("$.author", is(book.getAuthor())));

        verify(bookService, times(1)).findById("6651aa5b158789421363e2lb");
    }

    @Test
    public void testSaveBook() throws Exception {
        Book book = new Book("3651aa5b158789421363e2lb", "1984", "George Orwell", "Segunda Edición", "1949", "Estantería C3", EStatus.DISPONIBLE);

        doNothing().when(bookService).save(any(Book.class));

        String json = "{\"title\":\"1984\",\"author\":\"George Orwell\",\"edition\":\"Segunda Edición\",\"publicationDate\":\"1949\",\"location\":\"Estantería C3\",\"status\":\"DISPONIBLE\"}";

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(bookService, times(1)).save(any(Book.class));
    }

    @Test
    public void testDeleteBookById() throws Exception {
        doNothing().when(bookService).deleteById("6651aa5b158789421363e2lb");

        mockMvc.perform(delete(BASE_URL + "/6651aa5b158789421363e2lb")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteById("6651aa5b158789421363e2lb");
    }
}
