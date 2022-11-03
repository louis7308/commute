package com.example.commute.domain.company;

import com.example.commute.domain.company.enums.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Company {
    @Id
    private String id;

    private String name;

    private String category;

    private String code;

    private PolicyStatus policy;
}
