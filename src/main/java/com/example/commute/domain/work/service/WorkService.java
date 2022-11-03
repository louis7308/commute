package com.example.commute.domain.work.service;

import com.example.commute.domain.user.User;
import com.example.commute.domain.work.Work;
import com.example.commute.domain.work.dto.response.WorkStatusResponse;
import com.example.commute.domain.work.enums.WorkStatus;
import com.example.commute.domain.work.repository.WorkRepository;
import com.example.commute.global.Util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PrePersist;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final UserUtil userUtil;
    // 근무 -> 마감 에서 이 시간 사이 를 시간으로 채우고 다시 카피 근무 -> 마감 -> 사이 시간을 저장 -> 카피 근무 -> 마감

    @Transactional
    public WorkStatusResponse startWork() {
        User user = userUtil.currentUser();
        if(workRepository.existsByUser(user)) {
            Work workUser = workRepository.findByUser(user);
            LocalDateTime dateTime = LocalDateTime.now();
            workUser.updateCopyWorkTime(dateTime);
            workRepository.save(workUser);
            return new WorkStatusResponse(WorkStatus.WORK);
        }
        else {
            Work work = Work.builder()
                    .workDate(LocalDateTime.now())
                    .copyWorkDate(LocalDateTime.now())
                    .finishDate(LocalDate.now().atStartOfDay())
                    .status(WorkStatus.WORK)
                    .user(user)
                    .build();

            workRepository.save(work);
            return new WorkStatusResponse(WorkStatus.WORK);
        }
    }

    @PrePersist
    @Transactional
    public WorkStatusResponse finishWork() {
        User user = userUtil.currentUser();
        Work work = workRepository.findByUser(user);
        int currentHour = work.getCopyWorkDate().getHour();
        int currentMinute = work.getCopyWorkDate().getMinute();
        int finishHour = work.getFinishDate().getHour();
        int finishMinute = work.getFinishDate().getMinute();
        int time = 0;
        if(work.getFinishDate().getHour() == 0 && work.getFinishDate().getHour() == 0) {
            finishHour = LocalDateTime.now().getHour();
            finishMinute = LocalDateTime.now().getMinute();
            time = (finishHour * 3600 + finishMinute * 60) - (currentHour * 3600 + currentMinute * 60) / 60;
            work.updateFinishTime(LocalDateTime.now());
            work.updateTime(time);
            workRepository.save(work);
            return new WorkStatusResponse(WorkStatus.FINISH);
        }
        else {
            time = (finishHour * 3600 + finishMinute * 60) - (currentHour * 3600 + currentMinute * 60) / 60;



            work.updateFinishTime(LocalDateTime.now());
            work.updateTime(time);
            workRepository.save(work);
            return new WorkStatusResponse(WorkStatus.FINISH);
        }


    }
}
