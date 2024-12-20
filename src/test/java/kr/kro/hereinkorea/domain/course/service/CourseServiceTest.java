package kr.kro.hereinkorea.domain.course.service;

import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.course.repository.CourseRepository;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    CourseRepository courseRepository;
    @Test
    void testInsert() {
        CourseEntity course = CourseEntity.builder()
                .courseTitle("산책코스")
                .courseContent("테스트 데이터입니다.")
                .courseTag("대구")
                .courseViews(25)
                .courseLikes(10)
                .memId(MemberEntity.builder().memId("user001").build())
                .courseName("Test")
                .courseImageUrl("null")
                .build();

        courseRepository.save(course);
    }
}