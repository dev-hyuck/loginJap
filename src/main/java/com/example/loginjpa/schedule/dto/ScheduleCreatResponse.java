package com.example.loginjpa.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class ScheduleCreatResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleCreatResponse(Long id, String title, String content, String author, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
