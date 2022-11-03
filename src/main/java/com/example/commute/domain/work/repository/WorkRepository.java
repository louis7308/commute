package com.example.commute.domain.work.repository;

import com.example.commute.domain.user.User;
import com.example.commute.domain.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    boolean existsByUser(User user);
    Work findByUser(User user);

}
