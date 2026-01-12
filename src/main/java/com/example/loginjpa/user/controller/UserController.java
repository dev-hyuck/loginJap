package com.example.loginjpa.user.controller;

import com.example.loginjpa.schedule.service.ScheduleService;
import com.example.loginjpa.user.dto.*;
import com.example.loginjpa.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @PostMapping("/signup") // 회원가입
    public ResponseEntity<UserCreateResponse> signup (
            @RequestBody UserCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin (
            @Valid @RequestBody UserUpdateRequest request,
            HttpSession session
    ) {
        UserUpdateResponse result = userService.singin(request);
        SessionUser sessionUser = new SessionUser(
                result.getId(),
                result.getEmail()
        );
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.ok("success");
    }


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

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false)
            SessionUser sessionUser,HttpSession session)
    {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/users/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }


}
