package com.example.study.controller.api;

import com.example.study.controller.CRUDController;
import com.example.study.model.entity.Item;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemApiController extends CRUDController<ItemApiRequest, ItemApiResponse, Item> {
    /*
    @Autowired
    private ItemApiService itemApiService;

    // itemApiService 주입후 실행
    @PostConstruct
    public void init() {
        this.baseApiService = itemApiService;
    }
     */
}
