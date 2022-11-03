package com.example.commute.domain.manager.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NicknameChangeRequest {
    private String nickname;
    private String email;
}
