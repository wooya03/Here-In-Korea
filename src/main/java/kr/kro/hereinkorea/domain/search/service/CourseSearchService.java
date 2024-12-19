//package kr.kro.hereinkorea.domain.search.service;
//
//import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
//import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
//import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
//
//import java.util.List;
//
//public interface CourseSearchService {
//
//    List<CourseDTO> searchCourseByTitle(String courseTitle);
//
//    default CourseDTO entityToDTO(CourseEntity course, CourseImageEntity img){
//        return CourseDTO.builder()
//                .courseId(course.getCourseId())
//                .courseTitle(course.getCourseTitle())
//                .courseContent(course.getCourseContent())
//                .courseTag(course.getCourseTag())
//                .createdDate(course.getCreatedDate())
//                .courseViews(course.getCourseViews())
//                .courseLikes(course.getCourseLikes())
////                .courseImgId(course.getcourseImgId())
//                .build();
//
//
//
//    }
//
//}
