package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.enumclass.OrderStatus;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderStatus status = OrderStatus.ORDERING;
        LocalDate arrivalDate = LocalDate.now().plusDays(2);
        int quantity = 1;
        BigDecimal totalPrice = BigDecimal.valueOf(900000);

        // LocalDateTime createdAt = LocalDateTime.now();
        // String createdBy = "Admin Server";

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(status);
        orderDetail.setArrivalDate(arrivalDate);
        orderDetail.setQuantity(quantity);
        orderDetail.setTotalPrice(totalPrice);
        // orderDetail.setCreatedAt(createdAt);
        // orderDetail.setCreatedBy(createdBy);
        // orderDetail.setItemId(itemId);
        // orderDetail.setOrderGroupId(orderGroupId);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test
    public void update() {
        LocalDate arrivalDate = LocalDate.now().plusDays(2);
        OrderStatus status = OrderStatus.ORDERING;

        Optional<OrderDetail> findOrderDetail = orderDetailRepository.findById(1L);
        findOrderDetail.ifPresent(item -> {
            System.out.println("값이 있습니다" + item);
            item.setStatus(status);
            // item.setUpdatedAt(LocalDateTime.now());
            // item.setUpdatedBy("Admin Server");

            OrderDetail updatedOrderDetail = orderDetailRepository.save(item);
            Assertions.assertNotNull(updatedOrderDetail);
        });
    }
}