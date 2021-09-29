package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        String status = "REGISTERED";
        String name = "삼성 노트북";
        String title = "삼성 노트북 100";
        String content = "2021년형 노트북 입니다.";
        BigDecimal price = new BigDecimal("90000000");
        String brandName = "삼성";

        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "Admin Server";

        Long partnerId = 1L;

        Item item = new Item();
        item.setStatus(status);
        item.setName(name);
        item.setTitle(title);
        item.setContent(content);
        item.setPrice(price);
        item.setBrandName(brandName);
        item.setRegisteredAt(registeredAt);
        item.setCreatedAt(createdAt);
        item.setCreatedBy(createdBy);
        item.setPartnerId(partnerId);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }
}