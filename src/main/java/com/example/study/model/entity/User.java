package com.example.study.model.entity;

import com.example.study.component.BaseEntity;
import com.example.study.model.enumclass.Status;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"orderGroupList"})
@Builder
@Accessors(chain = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status; // REGISTERED , UNREGISTERED

    private String email;

    private  String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<OrderGroup> orderGroupList;
}
