package com.example.commute.domain.manager.repository;


import com.example.commute.domain.manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, String> {
    Optional<Manager> findManagerByEmail(String email);
    boolean existsByEmail(String email);
}
