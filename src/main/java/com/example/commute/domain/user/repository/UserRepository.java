package com.example.commute.domain.user.repository;


import com.example.commute.domain.manager.Manager;
import com.example.commute.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);

    User findByEmail(String email);

    List<User> findByManager(Optional<Manager> manager);
}
