package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    PENDING(0, "배송 대기", "주문 배송 대기"),
    COMPLETE(1, "배송 완료", "주문 배송 완료"),
    CANCEL(2, "주문 취소", "주문 취소 상태");

    private int id;
    private String title;
    private String description;
}
