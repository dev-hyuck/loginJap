package com.example.loginjpa.user.service;

import com.example.loginjpa.schedule.entity.Schedule;
import com.example.loginjpa.schedule.repository.ScheduleRepository;
import com.example.loginjpa.user.dto.UserCreateRequest;
import com.example.loginjpa.user.dto.UserCreateResponse;
import com.example.loginjpa.user.dto.UserGetResponse;
import com.example.loginjpa.user.entity.User;
import com.example.loginjpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor


public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public UserCreateResponse save(UserCreateRequest userCreateRequest) {

        if (userCreateRequest.getPassword().length() < 8) {
            throw new IllegalArgumentException(" 비밀번호는 8자 이상 입력해야 됩니다.");
        }
        String encodedPassword  = userCreateRequest.getPassword();

        User user = new User(
                userCreateRequest.getUsername(),
                userCreateRequest.getEmail(),
                encodedPassword
        );

        User saveuser = userRepository.save(user);
        return new UserCreateResponse(

                saveuser.getId(),
                saveuser.getUsername(),
                saveuser.getEmail(),
                saveuser.getPassword(),
                saveuser.getCreatedAt(),
                saveuser.getModifiedAt()
        );

    }

    public List<UserGetResponse> findAll(String author) {

        List<User> users;
        List<UserGetResponse> dtos = new ArrayList<>();

        if (author != null && !author.isEmpty()) {
            users = userRepository.findAllByUsernameOrderByModifiedAtDesc(author);
        } else {
            users = userRepository.findAllByOrderByModifiedAtDesc();
        }

        for (User user : users) {
            UserGetResponse dto = new UserGetResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }

        return dtos;

    }
}
