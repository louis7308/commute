package com.example.commute.domain.user.presentation;

import com.example.commute.domain.user.presentation.dto.response.UserMainDataResponse;
import com.example.commute.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public UserMainDataResponse mainData() {
        return userService.mainDataLoad();
    }
}
