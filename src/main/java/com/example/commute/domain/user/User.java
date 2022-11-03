package com.example.commute.domain.user;

import com.example.commute.domain.calender.Calender;
import com.example.commute.domain.calender.enums.PlaceStatus;
import com.example.commute.domain.manager.Manager;
import com.example.commute.domain.user.enums.Policy;
import com.example.commute.domain.work.Work;
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
public class User {
    @Id
    private String uuid;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private PlaceStatus place;

    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToOne(mappedBy = "user")
    private Work work;

    @OneToMany(mappedBy = "user")
    private List<Calender> calenders = new ArrayList<>();

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
