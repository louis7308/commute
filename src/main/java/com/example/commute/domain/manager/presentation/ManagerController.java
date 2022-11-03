package com.example.commute.domain.manager.presentation;

import com.example.commute.domain.manager.presentation.dto.request.NicknameChangeRequest;
import com.example.commute.domain.manager.presentation.dto.request.SearchUserListRequest;
import com.example.commute.domain.manager.presentation.dto.request.SignInManagerRequest;
import com.example.commute.domain.manager.presentation.dto.request.SignUpManagerRequest;
import com.example.commute.domain.manager.presentation.dto.response.SignInManagerResponse;
import com.example.commute.domain.manager.presentation.dto.response.UserListResponse;
import com.example.commute.domain.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/changeNickname")
    public ResponseEntity<Void> changeNickname(@RequestBody NicknameChangeRequest request) {
        managerService.changeNickname(request.getNickname(), request.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<UserListResponse> userList(@RequestBody SearchUserListRequest request) {
        return managerService.userList(request.getEmail());
    }
}
