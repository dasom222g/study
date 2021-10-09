package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import com.example.study.model.enumclass.Status;
import lombok.*;
import lombok.experimental.Accessors;

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
@Builder
@Accessors(chain = true)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;

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
