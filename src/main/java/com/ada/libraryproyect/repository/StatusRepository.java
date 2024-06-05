package com.ada.libraryproyect.repository;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.constants.EStatus;
import com.ada.libraryproyect.repository.entity.Role;
import com.ada.libraryproyect.repository.entity.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository {
    Status findByName(EStatus name);
}
