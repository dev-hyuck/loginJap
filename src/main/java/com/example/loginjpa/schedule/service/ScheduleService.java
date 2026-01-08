package com.example.loginjpa.schedule.service;

import com.example.loginjpa.schedule.dto.ScheduleCreatRequest;
import com.example.loginjpa.schedule.dto.ScheduleCreatResponse;
import com.example.loginjpa.schedule.dto.ScheduleGetResponse;
import com.example.loginjpa.schedule.entity.Schedule;
import com.example.loginjpa.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreatResponse save(ScheduleCreatRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword());

        Schedule saveschedule = scheduleRepository.save(schedule);
        return new ScheduleCreatResponse(
                saveschedule.getId(),
                saveschedule.getTitle(),
                saveschedule.getContent(),
                saveschedule.getAuthor(),
                saveschedule.getPassword(),
                saveschedule.getCreatedAt(),
                saveschedule.getModifiedAt()

        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll(String author) {
        List<Schedule> schedules;
        List<ScheduleGetResponse> dtos = new ArrayList<>();

        if (author != null && !author.isEmpty()) {
            schedules = scheduleRepository.findAllByAuthorOrderByModifiedAtDesc(author);
        } else {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
    }

        for(Schedule schedule : schedules) {
        ScheduleGetResponse scheduleGetResponse = new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
        dtos.add(scheduleGetResponse);

        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public ScheduleGetResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 멤버 입니다.")
        );

        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
