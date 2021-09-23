package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SearchParam {
    private String id;
    private String password;
    private String email;
    private int page;
}
