package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
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

    private String status;

    private String orderType;

    private String revAddress;

    private String revName;

    private String paymentType;

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
