package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection(DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // 추가할 user생성(id는 autoIncreament타입으로 자동할당 되므로 set안함)
        User user = new User();
        user.setAccount("test04");
        user.setEmail("test04@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy("test04");

        // db에 추가한 후 추가한 데이터 반환(id값 포함)
        User newUser = userRepository.save(user);
        System.out.println("완료: " + newUser);
    }

    @Test
    public void read() {
        /*
        List<User> users = userRepository.findAll(); // 테이블의 데이터를 List로 반환함
        for(User user: users) {
            System.out.println(user);
        }
        */
        // id값이 2인 데이터 추출
        Optional<User> user = userRepository.findById(2L); // 배열형태 반환이며 get메소드로 객체형태 받을 수 있음
        // user에 객체가 있을경우(null이 아닐경우) 실행되며 파라미터값은 객체형태
        user.ifPresent(item -> {
            System.out.println(item);
            System.out.println(item.getEmail());
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(3L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("change22");
            selectUser.setEmail("change22@gmail.com");
            selectUser.setUpdateAt(LocalDateTime.now());
            selectUser.setUpdateBy("dasomi");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete() {
        Optional<User> user = userRepository.findById(3L);
        Assertions.assertTrue(user.isPresent()); // user.isPresent()이 true여야 테스트 통과

        if (user.isPresent()) userRepository.delete(user.get());

        // 삭제 후 데이터 확인
        Optional<User> deleteUser = userRepository.findById(3L);
        Assertions.assertFalse(deleteUser.isPresent()); // deleteUser.isPresent()이 false여야 테스트 통과

        if (deleteUser.isPresent()) System.out.println("데이터 있음" + deleteUser.get());
        else System.out.println("데이터 삭제됨");
    }
}
