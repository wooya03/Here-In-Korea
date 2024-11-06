package kr.kro.hereinkorea.domain.user;

import kr.kro.hereinkorea.domain.user.Entity.UserEntity;
import kr.kro.hereinkorea.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserEntityTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void createTableTest() {
        System.out.println(userRepository.findById("user001").get());
    }
}