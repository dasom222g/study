package com.example.study.service;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderGroupApiService extends BaseApiService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest requestBody = request.getData();
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
        OrderGroup newOrderGroup = baseRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        Optional<OrderGroup> findOrderGroup = baseRepository.findById(id);
        return findOrderGroup.map(item -> response(item)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest requestBody = request.getData();
        Optional<OrderGroup> findItem = baseRepository.findById(requestBody.getId());
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
        .map(item -> baseRepository.save(item))
        .map(updatedItem -> response(updatedItem))
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> findItem = baseRepository.findById(id);
        return findItem.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
        OrderGroupApiResponse orderGroupResponse = OrderGroupApiResponse.builder()
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
