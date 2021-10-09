package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    REGISTERED(0, "등록", "등록상태"),
    UNREGISTERED(1, "해지", "해지상태");

    private int id;
    private String title;
    private String description;
}
