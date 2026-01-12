package com.example.loginjpa.user.repository;

import com.example.loginjpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsernameOrderByModifiedAtDesc(String username);
    List<User> findAllByOrderByModifiedAtDesc();
    Optional<User> findByEmail(String email);

}
