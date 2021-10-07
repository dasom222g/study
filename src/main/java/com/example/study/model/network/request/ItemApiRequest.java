package com.example.study.model.network.request;

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
public class ItemApiRequest {
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private Long partnerId;
}
