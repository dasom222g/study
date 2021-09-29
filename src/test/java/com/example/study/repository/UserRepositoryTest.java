package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection(DI)
    @Autowired
    UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test02";
        String password = "Test02PW";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Admin Server";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(newUser.getAccount(), account);
    }

    @Test
    public void read() {
        String phoneNumber = "010-1111-1111";
        Optional<User> findUserAsc = userRepository.findFirstByPhoneNumber(phoneNumber);
        Optional<User> findUserDesc = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);
        System.out.println("findUserAsc" + findUserAsc);
        System.out.println("findUserDesc" + findUserDesc);
    }
}