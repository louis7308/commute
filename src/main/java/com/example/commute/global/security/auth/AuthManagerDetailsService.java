package com.example.commute.global.security.auth;

import com.example.commute.domain.manager.repository.ManagerRepository;
import com.example.commute.domain.user.exception.UserNotFoundException;
import com.example.commute.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthManagerDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String email) {
        return managerRepository.findManagerByEmail(email)
                .map(AuthManagerDetail::new)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
    }
}
