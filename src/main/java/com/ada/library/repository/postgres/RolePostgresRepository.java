package com.ada.library.repository.postgres;

import com.ada.library.repository.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePostgresRepository extends JpaRepository<Role, Long> {
}
