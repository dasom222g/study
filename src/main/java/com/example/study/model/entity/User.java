package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String account;
    String email;
    String phoneNumber;

    LocalDateTime createAt;
    String createBy;
    LocalDateTime updateAt;
    String updateBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<OrderDetail> orderDetailList;
}
