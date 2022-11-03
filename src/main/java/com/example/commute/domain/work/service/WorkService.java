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

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final UserUtil userUtil;

    @Transactional
    public WorkStatusResponse startWork() {
        User user = userUtil.currentUser();
        if(workRepository.existsByUser(user)) {
            Work workUser = workRepository.findByUser(user);
            LocalDateTime dateTime = LocalDateTime.now();
            workUser.updateWorkTime(dateTime);
            workRepository.save(workUser);
            return new WorkStatusResponse(WorkStatus.WORK);
        }
        else {
            Work work = Work.builder()
                    .workDate(LocalDateTime.now())
                    .finishDate(null)
                    .status(WorkStatus.WORK)
                    .user(user)
                    .build();

            workRepository.save(work);
            return new WorkStatusResponse(WorkStatus.WORK);
        }
    }

    @Transactional
    public WorkStatusResponse finishWork() {
        User user = userUtil.currentUser();
        LocalDateTime localDateTime = user.getWork().getWorkDate();
        LocalDateTime finishDateTime = LocalDateTime.now();
        int localDateHour = localDateTime.getHour();
        int localDateMinute = localDateTime.getMinute();
        int finishDateHour = finishDateTime.getHour();
        int finishDateMinute = finishDateTime.getMinute();
        int time = (finishDateHour * 3600 + finishDateMinute * 60) - (localDateHour * 3600 + localDateMinute * 60);
        Work workUser = workRepository.findByUser(user);
        if(workUser.getTime() != 0) {
            localDateHour = workUser.getFinishDate().getHour();
            localDateMinute = workUser.getFinishDate().getMinute();
            finishDateHour = LocalDateTime.now().getHour();
            finishDateMinute = LocalDateTime.now().getMinute();
            time = (finishDateHour * 3600 + finishDateMinute * 60) - (localDateHour * 3600 + localDateMinute * 60);
            workUser.updateFinishTime(LocalDateTime.now());
            workUser.updateTime(time / 60);
            workRepository.save(workUser);
            return new WorkStatusResponse(WorkStatus.FINISH);
        }
        else {
            Work work = Work.builder()
                    .finishDate(LocalDateTime.now())
                    .status(WorkStatus.WORK)
                    .user(user)
                    .time(time / 60)
                    .build();

            workRepository.save(work);
            return new WorkStatusResponse(WorkStatus.FINISH);
        }
    }
}
