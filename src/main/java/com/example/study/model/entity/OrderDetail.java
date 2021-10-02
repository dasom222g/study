package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"orderGroup", "item"})
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDate arrivalDate;

    private int quantity;

    private BigDecimal totalPrice;

    // OrderDetail N:1 OrderGroup
    @ManyToOne
    OrderGroup orderGroup;

    // OrderDetail N:1 Item
    @ManyToOne
    Item item;
}
