package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemApiService extends BaseApiService<ItemApiRequest, ItemApiResponse, Item> {
   /*
    BaseApiService 에서 생성됨
   @Autowired
   private ItemRepository itemRepository;
    */

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
        Item newItem = baseRepository.save(item);
        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> findItem = baseRepository.findById(id);
        return  findItem.map(item -> response(item))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> findItem = baseRepository.findById(itemApiRequest.getId());
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
        .map(setItem -> baseRepository.save(setItem))
        .map(updatedItem -> response(updatedItem))
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> findItem = baseRepository.findById(id);
        return findItem.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public ItemApiResponse response(Item item) {
        String statusTitle = item.getStatus().getTitle();
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .statusTitle(statusTitle)
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .partnerId(item.getPartner().getId())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .build();
        return itemApiResponse;
    }

    @Override
    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);
        List<ItemApiResponse> itemApiResponseList = items.stream()
                .map(this::response).collect(Collectors.toList());
        return Header.OK(itemApiResponseList);
    }
}
