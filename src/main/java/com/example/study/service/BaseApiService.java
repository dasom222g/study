package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class BaseApiService<Req, Res, Entity> implements CRUDInterface<Req, Res> {
    // 공통 repository 생성
    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

    public abstract Header<List<Res>> search(Pageable pageable);
}
