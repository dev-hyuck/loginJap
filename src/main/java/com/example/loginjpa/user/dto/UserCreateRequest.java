package com.example.loginjpa.user.dto;

import lombok.Getter;

@Getter
public class UserCreateRequest {

    private String username;
    private String email;
    private String password;
}
