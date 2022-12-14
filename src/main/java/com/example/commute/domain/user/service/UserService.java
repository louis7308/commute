package com.example.commute.domain.user.service;

import com.example.commute.domain.user.presentation.dto.request.SignInUserRequest;
import com.example.commute.domain.user.presentation.dto.request.SignUpRequest;
import com.example.commute.domain.user.presentation.dto.response.SignInUserResponse;
import com.example.commute.domain.user.presentation.dto.response.UserMainDataResponse;

public interface UserService {
    void signup(SignUpRequest signUpRequest);
    SignInUserResponse login(SignInUserRequest signInUserRequest);
    void logout();
    UserMainDataResponse mainDataLoad();
}
