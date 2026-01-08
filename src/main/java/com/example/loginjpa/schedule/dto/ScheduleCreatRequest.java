package com.example.loginjpa.schedule.dto;

import lombok.Getter;

@Getter

public class ScheduleCreatRequest {

    private String title;
    private String content;
    private String author;
    private String password;

}
