package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.Status;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiService extends BaseApiService<UserApiRequest, UserApiResponse, User> {

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        /*
        todo
        1. request data 가져오기
        2. user 생성
        3. response data return
         */

        // request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(Status.REGISTERED)
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);
        // response data return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 받은 id로 response 형태의 객체 리턴하거나 잘못된 id로 객체가 없을경우 error 메세지 리턴
        Optional<User> findUser = baseRepository.findById(id);
        return findUser.map(user -> response(user))
                        .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        /*
        todo
        1. 수정할 request data 가져오기
        2. data의 id로 해당 객체 가져오기
        3. 객체 set 해서 update 하기
        4. response data return
         */

        // request data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // data의 id로 해당 객체 가져오기
        Optional<User> user = baseRepository.findById(userApiRequest.getId());
        // 각 map함수 안에서 데이터가 없거나 오류가 날 경우 orElseGet 함수로 감
        return user.map(item -> {
            // 객체 set 하여 리턴
            item.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setEmail(userApiRequest.getEmail())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return item;
        })
        // 수정된 객체 update 후 잘 저장되면 update 되어 저장된 객체 반환
        .map(item -> baseRepository.save(item))
        // update 되어 저장된 객체로 response data 만들어서 return
        .map(updatedItem -> response(updatedItem))
        .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        /*
        todo
        1. id 파라메터로 삭제할 객체 가져오기
        2. 객체 삭제하기
        3. ok 메세지 리턴
         */
        Optional<User> user = baseRepository.findById(id);
        return user.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
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
