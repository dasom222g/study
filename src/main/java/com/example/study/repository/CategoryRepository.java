package com.example.study.repository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByType(String type);
    Optional<Category> findFirstByType(String type);
    Optional<Category> findFirstByTypeOrderByIdDesc(String type);
}
