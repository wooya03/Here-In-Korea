package kr.kro.hereinkorea.domain.course.mapper;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;

public class CourseMapper {

      // Entity -> DTO 변환
      public static CourseDTO entityToDTO(CourseEntity course, MemberEntity member) {
        return CourseDTO.builder()
                .courseId(course.getCourseId()) // Long 타입으로 매핑
                .courseTitle(course.getCourseTitle())
                .courseContent(course.getCourseContent())
                .memId(member.getMemId())
                .courseTag(course.getCourseTag())
                .createdDate(course.getCreatedDate())
                .courseViews(course.getCourseViews())
                .courseLikes(course.getCourseLikes())
                .courseName(course.getCourseName())
                .build();
    }

    // DTO -> Entity 변환
    public CourseEntity dtoToEntity(CourseDTO courseDto) {
        MemberEntity member = MemberEntity.builder().memId(courseDto.getMemId()).build();
        return CourseEntity.builder()
                .courseId(courseDto.getCourseId()) // Long 타입으로 매핑
                .courseTitle(courseDto.getCourseTitle())
                .courseContent(courseDto.getCourseContent())
                .courseTag(courseDto.getCourseTag())
                .courseViews(courseDto.getCourseViews())
                .courseLikes(courseDto.getCourseLikes())
                .memId(member) // MemberEntity 객체 매핑
                .courseName(courseDto.getCourseName())
                .build();
    }
}
