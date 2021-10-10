package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"partnerList"})
@Builder
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    // Category 1:N Partner
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    List<Partner> partnerList;
}
