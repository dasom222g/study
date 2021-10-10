package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import com.example.study.model.enumclass.OrderStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import com.example.study.model.enumclass.Status;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user, orderDetailList"})
@Builder
@Accessors(chain = true)
public class OrderGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ORDERING/COMPLETE/CONFIRM

    @Enumerated(EnumType.STRING)
    private OrderType orderType; // ALL/EACH

    private String revAddress;

    private String revName;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType; // CARD/CASH

    private BigDecimal totalPrice;

    private int totalQuantity;

    private LocalDate orderAt;

    private LocalDate arrivalDate;

    @ManyToOne
    // OrderGroup N:1 User
    private User user;

    // OrderGroup 1:N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    List<OrderDetail> orderDetailList;
}
