package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseApiService<Req, Res, Entity> implements CRUDInterface<Req, Res> {
    // 공통 repository 생성
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
}
