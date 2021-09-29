package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest extends StudyApplicationTests {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "FOOD";
        String title = "음식";
        LocalDateTime createAt = LocalDateTime.now();
        String createdBy = "Admin Server";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);
        Assertions.assertEquals(newCategory.getCreatedBy(), createdBy);
    }

    @Test
    public void read() {
        String type = "FOOD";
        Optional<Category> item = categoryRepository.findByType(type);

        item.ifPresent(detail -> {
            Assertions.assertEquals(detail.getType(), type);
            System.out.println(detail.getId());
            System.out.println(detail.getType());
            System.out.println(detail.getTitle());
            System.out.println(detail.getCreatedBy());
        });
    }

    @Test
    public void update() {
        String type = "Beauty";
        Optional<Category> findCategory = categoryRepository.findFirstByTypeOrderByIdDesc(type);
        findCategory.ifPresent(item -> {
            item.setType("BEAUTY");
            item.setUpdatedAt(LocalDateTime.now());
            item.setUpdatedBy("Admin Server");

            Category updateCategory = categoryRepository.save(item);
            Assertions.assertNotNull(updateCategory);
        });
    }
}