package com.example.commute.domain.manager.presentation;

import com.example.commute.domain.manager.presentation.dto.request.SignInManagerRequest;
import com.example.commute.domain.manager.presentation.dto.request.SignUpManagerRequest;
import com.example.commute.domain.manager.presentation.dto.response.SignInManagerResponse;
import com.example.commute.domain.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/login")
    public SignInManagerResponse login(@RequestBody SignInManagerRequest signInManagerRequest) {
        return managerService.login(signInManagerRequest);
    }

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpManagerRequest signUpManagerRequest) {
        managerService.signup(signUpManagerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
