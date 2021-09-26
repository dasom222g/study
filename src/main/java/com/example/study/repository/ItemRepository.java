package com.example.study.repository;

import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
