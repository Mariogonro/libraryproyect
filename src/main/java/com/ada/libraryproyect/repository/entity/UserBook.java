package com.ada.libraryproyect.repository.entity;

import com.ada.libraryproyect.constants.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users_books")
public class UserBook {
    @Id
    private String id;
    @DBRef

    @Builder.Default
    private Set<User> users = new HashSet<>();

    @DBRef
    @Builder.Default
    private Set<Book> books = new HashSet<>();

    private EStatus status;
    public UserBook(EStatus name) {
        this.status = status;
    }
}
