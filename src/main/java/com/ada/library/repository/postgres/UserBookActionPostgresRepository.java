package com.ada.library.repository.postgres;

import com.ada.library.repository.entity.UserBookAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookActionPostgresRepository extends JpaRepository<UserBookAction, Long> {
}

