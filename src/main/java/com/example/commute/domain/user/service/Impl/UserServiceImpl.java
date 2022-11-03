package com.example.commute.domain.user.service.Impl;

import com.example.commute.domain.email.service.MailCheckerService;
import com.example.commute.domain.user.User;
import com.example.commute.domain.user.presentation.dto.request.SignInUserRequest;
import com.example.commute.domain.user.presentation.dto.request.SignUpRequest;
import com.example.commute.domain.user.presentation.dto.response.SignInUserResponse;
import com.example.commute.domain.user.exception.PasswordNotMatchException;
import com.example.commute.domain.user.exception.UserExistException;
import com.example.commute.domain.user.exception.UserNotFoundException;
import com.example.commute.domain.user.presentation.dto.response.UserMainDataResponse;
import com.example.commute.domain.user.repository.UserRepository;
import com.example.commute.domain.user.service.UserService;
import com.example.commute.global.Util.UserUtil;
import com.example.commute.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MailCheckerService mailCheckerService;
    private final UserUtil userUtil;

    @Override
    public void signup(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserExistException("이미 존재하는 회원입니다.");
        }
        mailCheckerService.execute(signUpRequest.getEmail(), signUpRequest.getAuthKey());
        userRepository.save(signUpRequest.toEntity(passwordEncoder.encode(signUpRequest.getPassword())));
    }
    @Override
    public SignInUserResponse login(SignInUserRequest signInUserRequest) {
        User user = userRepository.findUserByEmail(signInUserRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(signInUserRequest.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
        String accessToken = tokenProvider.generatedAccessToken(user.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(user.getEmail());

        user.updateRefreshToken(refreshToken);

        return SignInUserResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logout() {

    }

    @Override
    @Transactional
    public UserMainDataResponse mainDataLoad() {
        User user = userUtil.currentUser();
        String date = user.getWork().getWorkDate().toString().substring(14, 19) + user.getWork().getWorkDate().toString().substring(19, 19);
        return UserMainDataResponse.builder()
                .work_status(user.getWork().getStatus())
                .work_date(date)
                .place(user.getPlace())
                .time(user.getWork().getTime())
                .build();
    }
}
