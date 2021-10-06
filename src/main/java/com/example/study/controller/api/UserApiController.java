package com.example.study.controller.api;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserApiController implements CRUDInterface<UserApiRequest, UserApiResponse> {
    @Autowired
    private UserApiService userApiService;

    @Override
    @PostMapping("") // api/user
    public Header create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userApiService.create(request);
    }

    @Override
    @GetMapping("{id}") // api/user/{id}
    public Header<UserApiResponse> read(@PathVariable Long id) {
        log.info("read: {}", id);
        return userApiService.read(id);
    }

    @Override
    @PutMapping("") // api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header delete(@PathVariable Long id) {
        return userApiService.delete(id);
    }
}
