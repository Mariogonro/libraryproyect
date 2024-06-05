package com.ada.libraryproyect.repository.entity;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.constants.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "status")
@Data
public class Status {
    @Id
    private String id;

    private EStatus name;

    public Status() {
    }

    public Status(EStatus name) {
        this.name = name;
    }
}
