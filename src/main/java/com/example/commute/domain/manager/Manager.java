package com.example.commute.domain.manager;

import com.example.commute.domain.company.Company;
import com.example.commute.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Manager {
    @Id
    private String email;

    private String password;

    private String nickname;

    private String phone;

    private String managerRank;

    private String refreshToken;

    private String department;

    @OneToMany(mappedBy = "manager")
    private List<User> users = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
