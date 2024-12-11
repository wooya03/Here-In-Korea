package kr.kro.hereinkorea.global.jwt.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void testGenerateAccessToken() {
        String accesstoken = jwtUtil.generateAccessToken("user001");
        System.out.println("=============================");
        System.out.println(accesstoken);
        System.out.println("=============================");
    }
}