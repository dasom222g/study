package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Header<T> {
    // api 통신 시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String responseCode;

    // api 부가설명
    private String description;

    private T data;

    // OK (data 없는 Header)
    @SuppressWarnings("unchecked")
    public static <T> Header<T> OK() {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .responseCode("OK")
                .description("OK")
                .build();
    }

    // OK (data 받아서 추가하는 Header)
    @SuppressWarnings("unchecked")
    public static <T> Header<T> OK(T data) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .responseCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // Error
    @SuppressWarnings("unchecked")
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .responseCode("ERROR")
                .description(description)
                .build();
    }
}
