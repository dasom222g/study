package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    ORDERING(0, "주문 대기", "주문 대기"),
    COMPLETE(1, "주문 완료", "주문 주문 완료"),
    CONFIRM(2, "배송 완료", "배송 완료 상태");

    private int id;
    private String title;
    private String description;
}
