package com.example.study.service;

import com.example.study.ifs.CRUDInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.Status;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiService extends BaseApiService<UserApiRequest, UserApiResponse, User> {
    @Autowired
    OrderGroupApiService orderGroupApiService;

    @Autowired
    ItemApiService itemApiService;

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
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 받은 id로 response 형태의 객체 리턴하거나 잘못된 id로 객체가 없을경우 error 메세지 리턴
        Optional<User> findUser = baseRepository.findById(id);
        return findUser.map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
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
        .map(userApiResponse -> Header.OK(userApiResponse))
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

    private UserApiResponse response(User user) {
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
        return userApiResponse;
    }

    @Override
    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponsesList = users.stream()
                .map(this::response).collect(Collectors.toList());
        return Header.OK(userApiResponsesList);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // user 가져오기
        User user = baseRepository.getById(id);
        UserApiResponse userApiResponse = response(user);
        // orderGroup 가져와서 item 세팅
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiService.response(orderGroup);
                    // 해당 item 가져오기
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiService.response(item))
                            .collect(Collectors.toList());
                    // 해당 item 세팅하여 리턴
                    orderGroupApiResponse.setItemList(itemApiResponseList);
                    // OrderGroupApiResponse 리턴
                    return orderGroupApiResponse;
                })
                .collect(Collectors.toList());
        // 세팅된 setOrderGroupApiResponseList 을 userApiResponse 에 세팅하여 리턴
        userApiResponse.setOrderGroupList(orderGroupApiResponseList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .user(userApiResponse)
                .build();
        return Header.OK(userOrderInfoApiResponse);
    }
}
