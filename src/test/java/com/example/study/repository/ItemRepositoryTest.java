package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("그램 노트북");

        Item createdItem = itemRepository.save(item);
        // 정상적으로 create됐다면 createdItem에 값이 반환될것이므로 아래 테스트 코드 작성
        Assertions.assertNotNull(createdItem);
    }

    @Test
    public void read() {
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);
        Assertions.assertTrue(item.isPresent());
    }
}