package com.example.commute.domain.user.presentation.dto.response;

import com.example.commute.domain.calender.enums.PlaceStatus;
import com.example.commute.domain.work.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMainDataResponse {
    private WorkStatus work_status;
    private String work_date;
    private PlaceStatus place;
    private Integer time;
}
