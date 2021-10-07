package com.example.study.controller.api;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemApiController implements CRUDInterface<ItemApiRequest, ItemApiResponse> {
    @Autowired
    private ItemApiService itemApiService;

    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}", request);
        return itemApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return itemApiService.delete(id);
    }
}
