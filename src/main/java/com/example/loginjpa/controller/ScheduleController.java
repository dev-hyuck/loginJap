package com.example.loginjpa.controller;

import com.example.loginjpa.dto.ScheduleCreatRequest;
import com.example.loginjpa.dto.ScheduleCreatResponse;
import com.example.loginjpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// PostMapping  제작 완료
@RestController
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreatResponse> create (
            @RequestBody ScheduleCreatRequest request
    ) {
        ScheduleCreatResponse schedule = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);

    }

}
