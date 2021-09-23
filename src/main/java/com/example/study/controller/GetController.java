package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:5000/api/getMethod
    public String getMethod() {
        return "Hello world";
    }

    // parameter
    @GetMapping("/getParameter") // localhost:5000/api/getParameter?id=1234&password=abcd
    // url의 id값에 파라미터로 받아온 값이 매핑됨
    // url의 password에 매핑되어야 할 값이 있는데 해당 이름은 로컬변수로 사용해야 할경우 RequestParam의 인자로 넣어줌
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        System.out.println("id: " + id);
        System.out.println("password: " + pwd);
        return "id: " + id + ", password: " + pwd;
    }

    // multi-parameter
    @GetMapping("/getMultiParameter") // localhost:5000/api/getMultiParameter?id=1234&password=abcd&email=dasom228@gmail.com&page=10
    public String getMultiParameter(SearchParam searchParam)  {
        return searchParam.getEmail();
    }

    // return json
    @GetMapping("/getJson") // localhost:5000/api/getJson?id=1234&password=abcd&email=dasom228@gmail.com&page=10
    public SearchParam getJson(SearchParam searchParam)  {
        // { id: "1234", password: "abcd", email: "dasom228@gmail.com", page: 10}
        return searchParam;
    }
}
