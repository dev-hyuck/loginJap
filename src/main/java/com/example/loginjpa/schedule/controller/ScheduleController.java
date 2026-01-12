package com.example.loginjpa.schedule.controller;

import com.example.loginjpa.schedule.dto.ScheduleCreatRequest;
import com.example.loginjpa.schedule.dto.ScheduleCreatResponse;
import com.example.loginjpa.schedule.dto.ScheduleGetResponse;
import com.example.loginjpa.schedule.dto.ScheduleUpdateResponse;
import com.example.loginjpa.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> findAll( @RequestBody(required = false) String author) {
        List<ScheduleGetResponse> schedules = scheduleService.findAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(schedules);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    @PutMapping("/schedules/{schedulesId}")
    public ResponseEntity<ScheduleUpdateResponse> update (
            @RequestBody ScheduleGetResponse request,
            @PathVariable Long schedulesId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(schedulesId, request));

    }

    @DeleteMapping("/chedules/{schedulesId}")
    public void delete(@PathVariable Long schedulesId) {
        scheduleService.delete(schedulesId);
    }

}
