package com.example.study.controller.api;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupRequest;
import com.example.study.model.network.response.OrderGroupResponse;
import com.example.study.service.OrderGroupApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController implements CRUDInterface<OrderGroupRequest, OrderGroupResponse> {
    @Autowired
    OrderGroupApiService orderGroupApiService;

    @Override
    @PostMapping("")
    public Header<OrderGroupResponse> create(@RequestBody Header<OrderGroupRequest> request) {
        return orderGroupApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupResponse> read(@PathVariable Long id) {
        return orderGroupApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupResponse> update(@RequestBody Header<OrderGroupRequest> request) {
        return orderGroupApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return orderGroupApiService.delete(id);
    }
}
