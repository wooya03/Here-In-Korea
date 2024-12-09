package kr.kro.hereinkorea.domain.member;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class MemberEntityTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createTableTest() {
        System.out.println(memberRepository.findById("user001").get().getMemName());
    }
}