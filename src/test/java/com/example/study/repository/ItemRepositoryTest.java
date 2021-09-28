package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create() {}
}