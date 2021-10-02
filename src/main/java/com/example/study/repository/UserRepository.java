package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByPhoneNumber(String phoneNumber);
    Optional<User> findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    Optional<User> findByAccount(String account);
}
