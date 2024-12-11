package kr.kro.hereinkorea.domain.member;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testSelectMember() {
        Optional<MemberEntity> result=memberRepository.findByMemId("admin001");
        if(result.isPresent()){
            MemberEntity memberEntity = result.get();
            System.out.println("=================");
            System.out.println(memberEntity.getMemId());
            System.out.println(memberEntity.getEmail());
            System.out.println(memberEntity.getRole());
            System.out.println("=================");
        }else{
            System.out.println("오류임");
        }

    }
}