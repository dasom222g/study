package com.example.study.controller;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CRUDController<Req, Res, Entity> implements CRUDInterface<Req, Res> {
    @Autowired(required = false)
    protected BaseApiService<Req, Res, Entity> baseApiService;

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseApiService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseApiService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseApiService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseApiService.delete(id);
    }
}
