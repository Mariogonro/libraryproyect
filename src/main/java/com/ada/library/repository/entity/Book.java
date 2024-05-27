package com.ada.library.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "books")
public class Book {

    @Transient // Ignorado por JPA para persistencia en base de datos relacional
    @MongoId // ID para MongoDB
    private String idMongo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID para PostgreSQL

    private String title;
    private String author;
    private String edition;
    private String publicationDate;
    private String location;
    private char status;

    public static BookDTO bookDTO(Book book) {
        BookDTO dto = new BookDTO();
        if (book.getId() != null) {
            dto.setId(String.valueOf(book.getId()));
        } else {
            dto.setId(book.getIdMongo());
        }
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setEdition(book.getEdition());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setLocation(book.getLocation());
        dto.setStatus(book.getStatus());
        return dto;
    }

    public static Book book(BookDTO dto) {
        Book book = new Book();
        if (dto.getId() != null) {
            try {
                Long id = Long.parseLong(dto.getId());
                book.setId(id);
            } catch (NumberFormatException e) {
                book.setIdMongo(dto.getId());
            }
        }
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setEdition(dto.getEdition());
        book.setPublicationDate(dto.getPublicationDate());
        book.setLocation(dto.getLocation());
        book.setStatus(dto.getStatus());
        return book;
    }
}
