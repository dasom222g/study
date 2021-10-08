package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderGroupRepositoryTest extends StudyApplicationTests {
    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void create() {
        String status = "COMPLETE";
        String orderType = "All";
        String revAddress = "서울시 강남구";
        String revName = "김다솜";
        String paymentType = "CARD";
        BigDecimal totalPrice = BigDecimal.valueOf(900000);
        int totalQuantity = 5;
        LocalDate orderAt = LocalDate.now().minusDays(2);
        LocalDate arrivalDate = LocalDate.now();

        // LocalDateTime createdAt = LocalDateTime.now();
        // String createdBy = "Admin Server";

        Long userId = 3L;

        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus(status);
        orderGroup.setOrderType(orderType);
        orderGroup.setRevAddress(revAddress);
        orderGroup.setRevName(revName);
        orderGroup.setPaymentType(paymentType);
        orderGroup.setTotalPrice(totalPrice);
        orderGroup.setTotalQuantity(totalQuantity);
        orderGroup.setOrderAt(orderAt);
        orderGroup.setArrivalDate(arrivalDate);
        // orderGroup.setCreatedAt(createdAt);
        // orderGroup.setCreatedBy(createdBy);
         orderGroup.setUser(userRepository.getById(userId));

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assertions.assertNotNull(newOrderGroup);
    }
}