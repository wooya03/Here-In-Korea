package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

}