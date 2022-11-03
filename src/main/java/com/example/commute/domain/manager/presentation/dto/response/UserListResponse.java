package com.example.commute.domain.manager.presentation.dto.response;

import com.example.commute.domain.calender.enums.PlaceStatus;
import com.example.commute.domain.company.enums.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {
    private String department;
    private PolicyStatus policy;
    private PlaceStatus place;

}
