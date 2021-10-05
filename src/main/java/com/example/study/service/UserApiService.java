package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiService implements CRUDInterface<UserApiRequest, UserApiResponse> {
    @Autowired
    private UserRepository userRepository;
    /*
    todo
    1. request data 가져오기
    2. user 생성
    3. response data return
     */

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 받은 id로 response 형태의 객체 리턴하거나 잘못된 id로 객체가 없을경우 error 메세지 리턴
        Optional<User> findUser = userRepository.findById(id);
        return findUser.map(user -> response(user)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user) {
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .status(user.getStatus())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        return Header.OK(userApiResponse);
    }
}
