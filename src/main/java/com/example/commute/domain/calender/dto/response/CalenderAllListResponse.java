package com.example.commute.domain.calender.dto.response;

import com.example.commute.domain.calender.enums.PlaceStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class CalenderAllListResponse {
    private LocalDateTime date;
    private String content;
    private int time;
    private PlaceStatus status;
}
