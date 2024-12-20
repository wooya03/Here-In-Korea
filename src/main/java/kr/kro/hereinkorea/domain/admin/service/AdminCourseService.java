package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.course.repository.CourseRepository;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCourseService {
    private final CourseRepository courseRepository;

    public PageResultDTO<CourseDTO, Object[]> getCourse(String courseTitle, String memId, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result;

        if(courseTitle == null || courseTitle.isEmpty()){
            if(memId == null || memId.isEmpty()){
                result = courseRepository.getCourseCount(pageable);
            } else {
                result = courseRepository.getCourseByMemId(memId, pageable);
            }
        } else {
            if(memId == null || memId.isEmpty()){
                result = courseRepository.getCourseByTitle(courseTitle, pageable);
            } else {
                result = courseRepository.getCourseByTitleAndMemId(memId, courseTitle, pageable);
            }
        }

        return new PageResultDTO<>(result,
                en -> entityToDTO((CourseEntity) en[0], (MemberEntity) en[1]));
    }

    public CourseDTO entityToDTO(CourseEntity course, MemberEntity member){
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseTag(course.getCourseTag())
                .courseTitle(course.getCourseTitle())
                .courseViews(course.getCourseViews())
                .courseLikes(course.getCourseLikes())
                .createdDate(course.getCreatedDate())
                .courseContent(course.getCourseContent())
                .memId(member.getMemId()).build();
    }

    public void deleteCourse(List<Long> courseIds) {
        try {
            for(Long id : courseIds){
                courseRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("리뷰 삭제 중 오류 발생", e);
        }
    }
}
