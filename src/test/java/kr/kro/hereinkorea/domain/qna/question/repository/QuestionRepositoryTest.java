package kr.kro.hereinkorea.domain.qna.question.repository;


import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testInsert() {
        MemberEntity memberEntity = MemberEntity.builder().memId("user002").build();

        QuestionEntity questionEntity = QuestionEntity.builder()
                .qTitle("test2")
                .qContents("test222")
                .qStatus(false)
                .member(memberEntity)
                .qCategory("행사문의").build();

        questionRepository.save(questionEntity);
    }
}