package com.example.commute.domain.work.presentation;

import com.example.commute.domain.work.dto.response.WorkStatusResponse;
import com.example.commute.domain.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("work")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @PostMapping("/start")
    public WorkStatusResponse startWork() {
        return workService.startWork();
    }

    @PostMapping("/finish")
    public WorkStatusResponse finishWork() {
        return workService.finishWork();
    }

}
