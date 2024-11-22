package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.qna.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Test
    void testInsert() {
        MemberEntity memberEntity = MemberEntity.builder().memId("user001").build();


        QuestionEntity questionEntity = QuestionEntity.builder()
                .qCategory("기타문의")
                .qTitle("test1")
                .qStatus(true)
                .qContents("test111")
                .member(memberEntity)
                .build();

        questionRepository.save(questionEntity);
    }
}