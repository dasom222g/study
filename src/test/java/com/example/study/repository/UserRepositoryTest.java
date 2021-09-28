package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
    }

    @Test
    @Transactional
    public void read() {
    }

    @Test
    public void update() {
    }

    @Test
    @Transactional
    public void delete() {
    }
}
