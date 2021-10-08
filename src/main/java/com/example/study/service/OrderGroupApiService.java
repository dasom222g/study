package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupRequest;
import com.example.study.model.network.response.OrderGroupResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderGroupApiService implements CRUDInterface<OrderGroupRequest, OrderGroupResponse> {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupResponse> create(Header<OrderGroupRequest> request) {
        OrderGroupRequest requestBody = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(requestBody.getStatus())
                .orderType(requestBody.getOrderType())
                .revAddress(requestBody.getRevAddress())
                .revName(requestBody.getRevName())
                .paymentType(requestBody.getPaymentType())
                .totalPrice(requestBody.getTotalPrice())
                .totalQuantity(requestBody.getTotalQuantity())
                .orderAt(requestBody.getOrderAt())
                .arrivalDate(requestBody.getArrivalDate())
                .user(userRepository.getById(requestBody.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupResponse> read(Long id) {
        Optional<OrderGroup> findOrderGroup = orderGroupRepository.findById(id);
        System.out.println("findOrderGroup=====> " + orderGroupRepository.findById(1L));
        return findOrderGroup.map(item -> response(item)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupResponse> update(Header<OrderGroupRequest> request) {
        OrderGroupRequest requestBody = request.getData();
        Optional<OrderGroup> findItem = orderGroupRepository.findById(requestBody.getId());
        return findItem.map(item -> {
            item.setStatus(requestBody.getStatus())
                    .setOrderType(requestBody.getOrderType())
                    .setRevAddress(requestBody.getRevAddress())
                    .setRevName(requestBody.getRevName())
                    .setPaymentType(requestBody.getPaymentType())
                    .setTotalPrice(requestBody.getTotalPrice())
                    .setTotalQuantity(requestBody.getTotalQuantity())
                    .setOrderAt(requestBody.getOrderAt())
                    .setArrivalDate(requestBody.getArrivalDate())
                    .setUser(userRepository.getById(requestBody.getUserId()));
            return item;
        })
        .map(item -> orderGroupRepository.save(item))
        .map(updatedItem -> response(updatedItem))
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> findItem = orderGroupRepository.findById(id);
        return findItem.map(item -> {
            orderGroupRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupResponse> response(OrderGroup orderGroup) {
        OrderGroupResponse orderGroupResponse = OrderGroupResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(orderGroupResponse);
    }
}
