package com.example.study.model.network.response;

import com.example.study.model.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserApiResponse {
    private  Long id;

    private String account;

    private String password;

    private Status status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    // 연관 데이터 추가
    private List<OrderGroupApiResponse> orderGroupList;
}
