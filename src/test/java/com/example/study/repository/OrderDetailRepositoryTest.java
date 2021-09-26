package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRepositoryTest extends StudyApplicationTests {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail item = new OrderDetail();
        item.setOrderAt(LocalDateTime.now());
//        item.setUser_id(6L);
//        item.setItem_id(1L);
        OrderDetail newItem = orderDetailRepository.save(item);
        Assertions.assertNotNull(newItem);
    }
}