package com.example.commute.domain.manager.presentation.dto.request;

import com.example.commute.domain.manager.Manager;
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
public class SignUpManagerRequest {
    private String email;
    private String password;
    private String nickname;
    private String phone;
    private String rank;
    private String companyCode;

    public Manager toEntity(String password) {
        return Manager.builder()
                .email(email )
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .managerRank(rank)
                .build();
    }
}
