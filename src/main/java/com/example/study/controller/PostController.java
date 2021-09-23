package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {
    @PostMapping(value = "/postMethod") // localhost:5000/api/postMethod
    public String postMethod(@RequestBody SearchParam searchParam) {
        return "OK";
    }

    @PostMapping(value = "/postMethodJson", produces = {"application/json"}) // localhost:5000/api/postMethodJson
    public SearchParam postMethodJson(@RequestBody SearchParam searchParam) {
        return searchParam;
    }
}
