package com.example.study.model.network.response;

import com.example.study.model.enumclass.OrderStatus;
import com.example.study.model.enumclass.OrderType;
import com.example.study.model.enumclass.PaymentType;
import com.example.study.model.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderGroupApiResponse {
    private Long id;

    private OrderStatus status; // PENDING, COMPLETE, CANCEL

    private OrderType orderType;

    private String revAddress;

    private String revName;

    private PaymentType paymentType;

    private BigDecimal totalPrice;

    private int totalQuantity;

    private LocalDate orderAt;

    private LocalDate arrivalDate;

    private Long userId;
}
