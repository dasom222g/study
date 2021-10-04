package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserRepositoryTest extends StudyApplicationTests {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        String account = "Song123";
        String password = "Song123PW";
        String status = "REGISTERED";
        String role = "Partner";
        // LocalDateTime createdAt = LocalDateTime.now();
        // String createdBy = "Admin Server";

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount(account);
        adminUser.setPassword(password);
        adminUser.setStatus(status);
        adminUser.setRole(role);
        // adminUser.setCreatedAt(createdAt);
        // adminUser.setCreatedBy(createdBy);

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);
    }
}