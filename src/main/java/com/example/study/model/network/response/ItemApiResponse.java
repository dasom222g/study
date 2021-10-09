package com.example.study.model.network.response;

import com.example.study.model.enumclass.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ItemApiResponse {
    private Long id;

    private Status status;

    private String statusTitle;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private Long partnerId;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
