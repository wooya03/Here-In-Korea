package kr.kro.hereinkorea.domain.member;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testSelectMember() {
        Optional<MemberEntity> result=memberRepository.findByMemId("admin");
        if(result.isPresent()){
            MemberEntity memberEntity = result.get();
            System.out.println("=================");
            System.out.println(memberEntity.getMemId());
            System.out.println(memberEntity.getEmail());
            System.out.println(memberEntity.getRole());
            System.out.println("=================");
        }

    }
}