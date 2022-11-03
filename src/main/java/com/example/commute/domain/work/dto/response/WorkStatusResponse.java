package com.example.commute.domain.work.dto.response;

import com.example.commute.domain.work.enums.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkStatusResponse{
    private WorkStatus workStatus;
}
