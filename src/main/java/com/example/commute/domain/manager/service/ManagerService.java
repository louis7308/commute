package com.example.commute.domain.manager.service;

import com.example.commute.domain.manager.Manager;
import com.example.commute.domain.manager.presentation.dto.request.SignInManagerRequest;
import com.example.commute.domain.manager.presentation.dto.request.SignUpManagerRequest;
import com.example.commute.domain.manager.presentation.dto.response.SignInManagerResponse;
import com.example.commute.domain.manager.repository.ManagerRepository;
import com.example.commute.domain.user.exception.PasswordNotMatchException;
import com.example.commute.domain.user.exception.UserExistException;
import com.example.commute.domain.user.exception.UserNotFoundException;
import com.example.commute.domain.user.presentation.dto.request.SignInUserRequest;
import com.example.commute.domain.user.presentation.dto.request.SignUpRequest;
import com.example.commute.domain.user.presentation.dto.response.SignInUserResponse;
import com.example.commute.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public void signup(SignUpManagerRequest signUpManagerRequest) {
        if(managerRepository.existsByEmail(signUpManagerRequest.getEmail())) {
            throw new UserExistException("이미 존재하는 회원입니다.");
        }
        managerRepository.save(signUpManagerRequest.toEntity(passwordEncoder.encode(signUpManagerRequest.getPassword())));
    }

    public SignInManagerResponse login(SignInManagerRequest signInManagerResponse) {
        Manager manager = managerRepository.findManagerByEmail(signInManagerResponse.getEmail()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(signInManagerResponse.getPassword(), manager.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = tokenProvider.generatedAccessToken(manager.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(manager.getEmail());

        manager.updateRefreshToken(refreshToken);

        return SignInManagerResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
