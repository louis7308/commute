package com.example.commute.domain.email.repository;

import com.example.commute.domain.email.EmailAuth;
import org.springframework.data.repository.CrudRepository;

public interface EmailAuthRepository extends CrudRepository<EmailAuth, String> {
}
