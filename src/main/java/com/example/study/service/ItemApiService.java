package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemApiService implements CRUDInterface<ItemApiRequest, ItemApiResponse> {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest ItemApiRequest = request.getData();
        Item item = Item.builder()
                .status(ItemApiRequest.getStatus())
                .name(ItemApiRequest.getName())
                .title(ItemApiRequest.getTitle())
                .content(ItemApiRequest.getContent())
                .price(ItemApiRequest.getPrice())
                .brandName(ItemApiRequest.getBrandName())
                .partner(partnerRepository.getById(ItemApiRequest.getPartnerId()))
                .build();
        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        return  findItem.map(item -> response(item)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> findItem = itemRepository.findById(itemApiRequest.getId());
        return findItem.map(item -> {
            item.setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setTitle(itemApiRequest.getTitle())
                    .setContent(itemApiRequest.getContent())
                    .setPrice(itemApiRequest.getPrice())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setPartner(partnerRepository.getById(itemApiRequest.getPartnerId()));
            return item;
        })
        .map(setItem -> itemRepository.save(setItem))
        .map(updatedItem -> response(updatedItem))
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        return findItem.map(item -> {
            itemRepository.delete(item);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .partnerId(item.getPartner().getId())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .build();
        return Header.OK(itemApiResponse);
    }
}
