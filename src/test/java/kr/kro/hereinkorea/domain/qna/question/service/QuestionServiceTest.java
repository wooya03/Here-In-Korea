package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.qna.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class QuestionServiceTest {
    @Autowired
    private QuestionRepository questionRepository;

//    @Test
//    void testInsert() {
//        MemberEntity memberEntity = MemberEntity.builder().memId("user003").build();
//
//        QuestionEntity questionEntity = QuestionEntity.builder()
//                .qCategory("숙소문의")
//                .qTitle("test4")
//                .qStatus(false)
//                .qContents("test444")
//                .member(memberEntity)
//                .build();
//
//        questionRepository.save(questionEntity);
//    }
}