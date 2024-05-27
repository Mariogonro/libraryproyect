package com.ada.library.repository.postgres;
import com.ada.library.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostgresRepository extends JpaRepository<User, Long> {
}
