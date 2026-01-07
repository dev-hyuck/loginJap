package com.example.loginjpa.repository;

import com.example.loginjpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByOrderByModifiedAtDesc();
    List<Schedule> findAllByAuthorOrderByModifiedAtDesc(String author);
}
