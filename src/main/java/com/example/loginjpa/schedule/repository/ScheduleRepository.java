package com.example.loginjpa.schedule.repository;

import com.example.loginjpa.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findAllByAuthorOrderByModifiedAtDesc(String author);
}
