package com.example.commute.domain.user.presentation.dto.request;

import com.example.commute.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String email;
    private String authKey;
    private String nickname;
    private String password;
    private String companyCode;

    public User toEntity(String password) {
        return User.builder()
                .uuid(UUID.randomUUID().toString())
                .email(email )
                .password(password)
                .nickname(nickname)
                .place(null)
                .build();
    }
}
