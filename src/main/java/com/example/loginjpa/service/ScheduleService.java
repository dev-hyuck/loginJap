package com.example.loginjpa.service;

import com.example.loginjpa.dto.ScheduleCreatRequest;
import com.example.loginjpa.dto.ScheduleCreatResponse;
import com.example.loginjpa.entity.Schedule;
import com.example.loginjpa.repository.SheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ScheduleService {

    private final SheduleRepository sheduleRepository;

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
}
