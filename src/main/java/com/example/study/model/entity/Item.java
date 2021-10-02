package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"orderDetailList", "partner"})
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // Item 1:N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    List<OrderDetail> orderDetailList;

    // Item N:1 Partner
    @ManyToOne
    Partner partner;
}
