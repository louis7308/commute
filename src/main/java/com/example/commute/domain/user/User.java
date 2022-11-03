package com.example.commute.domain.user;

import com.example.commute.domain.manager.Manager;
import com.example.commute.domain.user.enums.Policy;
import com.example.commute.domain.work.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private String uuid;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private Policy policy;

    private String place;

    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToOne(mappedBy = "user")
    private Work work;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
