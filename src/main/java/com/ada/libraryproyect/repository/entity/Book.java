package com.ada.libraryproyect.repository.entity;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.constants.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    private String author;
    private String edition;
    private String publicationDate;
    private String location;
    private EStatus status;

    public Book(EStatus name) {
        this.status = status;
    }
}