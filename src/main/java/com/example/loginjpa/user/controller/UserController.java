package com.example.loginjpa.user.controller;

import com.example.loginjpa.schedule.service.ScheduleService;
import com.example.loginjpa.user.dto.*;
import com.example.loginjpa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserCreateResponse> create (@RequestBody UserCreateRequest userCreateRequest) {
        UserCreateResponse user = userService.save(userCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponse>> gettAll(
            @RequestParam(required = false) String author
    ) {
        List<UserGetResponse> users = userService.findAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getOne(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(userId));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable Long id, @RequestBody UserUpdateRequest request) {

        UserUpdateResponse updateUser = userService.update(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }


}
