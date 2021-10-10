package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import com.example.study.model.enumclass.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"orderGroup", "item"})
@Builder
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ORDERING/COMPLETE/CONFIRM

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
