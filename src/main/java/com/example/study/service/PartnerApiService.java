package com.example.study.service;

import com.example.study.model.entity.Partner;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartnerApiService extends BaseApiService<PartnerApiRequest, PartnerApiResponse, Partner> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest requestBody = request.getData();
        Partner partner = Partner.builder()
                .name(requestBody.getName())
                .status(requestBody.getStatus())
                .address(requestBody.getAddress())
                .callCenter(requestBody.getCallCenter())
                .partnerNumber(requestBody.getPartnerNumber())
                .businessNumber(requestBody.getBusinessNumber())
                .ceoName(requestBody.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getById(requestBody.getCategoryId()))
                .build();
        Partner newPartner = baseRepository.save(partner);
        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        Optional<Partner> findItem = baseRepository.findById(id);
        return findItem.map(this::response).map(Header::OK).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest requestBody = request.getData();
        Optional<Partner> findItem = baseRepository.findById(requestBody.getId());
        return findItem.map(item -> {
            item.setName(requestBody.getName())
                    .setStatus(requestBody.getStatus())
                    .setAddress(requestBody.getAddress())
                    .setCallCenter(requestBody.getCallCenter())
                    .setPartnerNumber(requestBody.getPartnerNumber())
                    .setBusinessNumber(requestBody.getBusinessNumber())
                    .setCeoName(requestBody.getCeoName())
                    .setRegisteredAt(requestBody.getRegisteredAt())
                    .setUnregisteredAt(requestBody.getUnregisteredAt())
                    .setCategory(categoryRepository.getById(requestBody.getCategoryId()));
            return item;
        })
        .map(setItem -> baseRepository.save(setItem))
        .map(updatedItem -> response(updatedItem))
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Partner> findItem = baseRepository.findById(id);
        return findItem.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner) {
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();
        return partnerApiResponse;
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponsesList = partners.stream()
                .map(this::response).collect(Collectors.toList());
        return Header.OK(partnerApiResponsesList);
    }
}
