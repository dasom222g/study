package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
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
    @Transactional
    public void read() {
        String phoneNumber = "010-1111-1111";
        Optional<User> findUserAsc = userRepository.findFirstByPhoneNumber(phoneNumber);
        Optional<User> findUserDesc = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);
        // System.out.println("findUserAsc" + findUserAsc);
        // System.out.println("findUserDesc" + findUserDesc);

        findUserAsc.ifPresent(userItem -> {
            userItem.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("==========================*고객*의 주문 묶음 (OrderGroup)==========================");
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("배송지 주소: " + orderGroup.getRevAddress());
                System.out.println("주문 묶음 상태: " + orderGroup.getStatus());
                System.out.println("총 주문 금액: " + orderGroup.getTotalPrice());
                System.out.println("총 주문 수량: " + orderGroup.getTotalQuantity());

                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("==========================*고객*의 주문 상세 (OrderDetail)==========================");
                    System.out.println("주문 묶음: " + orderDetail.getOrderGroup());
                    System.out.println("주문 상태: " + orderDetail.getStatus());
                    System.out.println("주문 도착 예정일: " + orderDetail.getArrivalDate());

                    System.out.println("주문 상품: " + orderDetail.getItem().getName());
                    System.out.println("콜센터 전화번호: " + orderDetail.getItem().getPartner().getCallCenter());

                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                });
            });
        });
    }
}