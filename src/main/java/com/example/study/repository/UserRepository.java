package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 쿼리 메소드
    // SELECT * FROM User WHERE id = ?
    Optional<User> findByAccount(String account);

    // SELECT * FROM User WHERE id = ? AND email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
