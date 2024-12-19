package kr.kro.hereinkorea.domain.course.mapper;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CourseMapper {

    // Entity -> DTO 변환
    public CourseDTO toDTO(CourseEntity course) {
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .courseTitle(course.getCourseTitle())
                .courseContent(course.getCourseContent())
                .memId(course.getMemId() != null ? course.getMemId().getMemId() : null) // String 타입
                .courseTag(course.getCourseTag())
                .createdDate(course.getCreatedDate())
                .courseViews(course.getCourseViews())
                .courseLikes(course.getCourseLikes())
                .courseName(course.getCourseName())
                .build();
    }

    // DTO -> Entity 변환
    public CourseEntity toEntity(CourseDTO courseDto, MemberEntity member) {
        return CourseEntity.builder()
                .courseId(courseDto.getCourseId())
                .courseTitle(courseDto.getCourseTitle())
                .courseContent(courseDto.getCourseContent())
                .courseTag(courseDto.getCourseTag())
                .createdDate(courseDto.getCreatedDate() != null ? courseDto.getCreatedDate() : LocalDateTime.now())
                .courseViews(courseDto.getCourseViews())
                .courseLikes(courseDto.getCourseLikes())
                .memId(member) // MemberEntity 객체 매핑
                .courseName(courseDto.getCourseName())
                .build();
    }
}
