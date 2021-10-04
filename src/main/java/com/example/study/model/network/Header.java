package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Header<T> {
    // api 통신 시간
    private String transactionTime;

    // api 응답 코드
    private String responseCode;

    // api 부가설명
    private String description;

    private T data;
}
