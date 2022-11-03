package com.example.commute.domain.user.presentation;

import com.example.commute.domain.user.presentation.dto.request.SignInUserRequest;
import com.example.commute.domain.user.presentation.dto.request.SignUpRequest;
import com.example.commute.domain.user.presentation.dto.response.SignInUserResponse;
import com.example.commute.domain.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
        userService.signup(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public SignInUserResponse login(@RequestBody SignInUserRequest request) {
        return userService.login(request);
    }

}
