package com.example.loginjpa.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCreateResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UserCreateResponse(Long id, String username, String email, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
