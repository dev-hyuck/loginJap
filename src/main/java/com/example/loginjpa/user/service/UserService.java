package com.example.loginjpa.user.service;

import com.example.loginjpa.schedule.repository.ScheduleRepository;
import com.example.loginjpa.user.dto.*;
import com.example.loginjpa.user.entity.User;
import com.example.loginjpa.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    @Transactional(readOnly = true)
    public UserGetResponse getOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 유저 입니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UserUpdateResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("없는 ID 입니다.")
        );

        user.update(request.getUsername(), request.getEmail(), request.getPassword());
        return new UserUpdateResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    public void delete(Long userId) {
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new IllegalArgumentException("없는 Id 입니다 삭제할 수 없습니다.");
        }
        userRepository.deleteById(userId);
    }


    @Transactional(readOnly = true)
    public UserUpdateResponse singin(@Valid UserUpdateRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 이메일입니다.")
        );
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalArgumentException(" 비밀번호가 일치하지 않습니다.");
        }
        return new UserUpdateResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
