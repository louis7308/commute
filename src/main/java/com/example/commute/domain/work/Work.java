package com.example.commute.domain.work;

import com.example.commute.domain.user.User;
import com.example.commute.domain.work.enums.WorkStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime workDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime finishDate;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @ColumnDefault("0")
    private Integer time = 0;

    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;



    public void updateWorkTime(LocalDateTime localDateTime) {
        this.workDate = localDateTime;
    }

    public void updateFinishTime(LocalDateTime localDateTime) {
        this.finishDate = localDateTime;
    }

    public void updateTime(int time) {
        this.time = time;
    }

}
