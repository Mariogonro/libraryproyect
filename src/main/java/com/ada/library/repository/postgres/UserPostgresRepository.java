package com.ada.library.repository.postgres;
import com.ada.library.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPostgresRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}