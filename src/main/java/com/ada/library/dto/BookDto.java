package com.ada.library.dto;

import com.ada.library.repository.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private String id;
    private String title;
    private String author;
    private String edition;
    private String publicationDate;
    private String location;
    private char status;
    private UserDto user;

    public static Book fromEntity(BookDto bookDto) {
        Book book = new Book();
        if (bookDto.getId() != null) {
            try {
                book.setId(Long.parseLong(bookDto.getId()));
            } catch (NumberFormatException e) {
                book.setIdMongo(bookDto.getId());
            }
        }
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setEdition(bookDto.getEdition());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setLocation(bookDto.getLocation());
        book.setStatus(bookDto.getStatus());
        return book;
    }

    public static BookDto toEntity(Book book) {
        BookDto bookDto = new BookDto();
        if (book.getId() != null) {
            bookDto.setId(String.valueOf(book.getId()));
        } else {
            bookDto.setId(book.getIdMongo());
        }
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setEdition(book.getEdition());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setLocation(book.getLocation());
        bookDto.setStatus(book.getStatus());
        return bookDto;
    }
}

