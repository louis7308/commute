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

    private LocalDateTime copyWorkDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime finishDate;

    @Column
    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    private Integer time;

    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user;



    public void updateCopyWorkTime(LocalDateTime localDateTime) {
        this.copyWorkDate = localDateTime;
    }

    public void updateFinishTime(LocalDateTime localDateTime) {
        this.finishDate = localDateTime;
    }

    public void updateTime(Integer time) {
        this.time = time;
    }

}
