package com.example.study.controller.api;

import com.example.study.controller.CRUDController;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController extends CRUDController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
}
