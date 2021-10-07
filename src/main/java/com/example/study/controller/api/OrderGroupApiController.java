package com.example.study.controller.api;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupRequest;
import com.example.study.model.network.response.OrderGroupResponse;
import com.example.study.service.OrderGroupApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController implements CRUDInterface<OrderGroupRequest, OrderGroupResponse> {
    @Autowired
    OrderGroupApiService orderGroupApiService;
    @Override
    public Header<OrderGroupResponse> create(Header<OrderGroupRequest> request) {
        return orderGroupApiService.create(request);
    }

    @Override
    public Header<OrderGroupResponse> read(Long id) {
        return orderGroupApiService.read(id);
    }

    @Override
    public Header<OrderGroupResponse> update(Header<OrderGroupRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
