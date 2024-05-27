package com.ada.library.repository.postgres;

import com.ada.library.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPostgresRepository extends JpaRepository<Book, Long> {
}
