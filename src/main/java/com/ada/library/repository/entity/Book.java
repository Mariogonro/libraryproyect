package com.ada.library.repository.entity;

import com.ada.library.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@Document(collection = "books")
public class Book {

    @Transient
    @MongoId
    private String idMongo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String edition;
    private String publicationDate;
    private String location;
    private char status;

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