package com.example.commute.global.Util;

import com.example.commute.domain.user.User;
import com.example.commute.domain.user.exception.UserNotFoundException;
import com.example.commute.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public User currentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("유저가 없습니다"));
    }
}