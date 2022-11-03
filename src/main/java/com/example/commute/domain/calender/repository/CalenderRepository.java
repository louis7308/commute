package com.example.commute.domain.calender.repository;

import com.example.commute.domain.calender.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<Calender, Integer> {
}
