package com.example.commute.domain.company;

import com.example.commute.domain.company.enums.PolicyStatus;
import com.example.commute.domain.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    @Enumerated(EnumType.STRING)
    private PolicyStatus policy;

    @OneToMany(mappedBy = "company")
    private List<Manager> manager = new ArrayList<>();
}
