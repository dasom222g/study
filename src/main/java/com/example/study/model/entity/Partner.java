package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import com.example.study.model.enumclass.Status;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"itemList", "category"})
@Builder
@Accessors(chain = true)
public class Partner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // Partner 1:N Item
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    List<Item> itemList;

    // Partner N:1 Category
    @ManyToOne
    Category category;
}
