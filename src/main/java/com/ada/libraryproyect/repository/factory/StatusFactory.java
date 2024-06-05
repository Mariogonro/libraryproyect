package com.ada.libraryproyect.repository.factory;

import com.ada.libraryproyect.constants.EStatus;
import com.ada.libraryproyect.handler.exception.RoleNotFoundException;
import com.ada.libraryproyect.repository.StatusRepository;
import com.ada.libraryproyect.repository.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusFactory {

    StatusRepository statusRepository;

    public Status getInstance(String status) throws RoleNotFoundException {
        switch (status) {
            case "disponible" -> {
                return statusRepository.findByName(EStatus.DISPONIBLE);
            }
            case "prestado" -> {
                return statusRepository.findByName(EStatus.PRESTADO);
            }
            case "reservado" -> {
                return statusRepository.findByName(EStatus.RESERVADO);
            }
            default -> throw  new RoleNotFoundException("No status found for " +  status);
        }
    }
}
