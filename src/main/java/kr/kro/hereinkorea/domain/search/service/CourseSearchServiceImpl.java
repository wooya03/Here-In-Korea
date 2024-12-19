//package kr.kro.hereinkorea.domain.search.service;
//
//import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
//import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
//import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
//import kr.kro.hereinkorea.domain.search.repository.CourseSearchRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CourseSearchServiceImpl implements CourseSearchService {
//
//    @Autowired
//    private CourseSearchRepository courseSearchRepository;
//
//
//    @Override
//    public List<CourseDTO> searchCourseByTitle(String courseTitle) {
//
//        List<Object[]> result = courseSearchRepository.findTop4ByTitleContaining(courseTitle);
//        List<CourseDTO> courseDtoResult = new ArrayList<>();
//
//
//        for (Object[] dto : result) {
//            CourseEntity courseEntity = (CourseEntity) dto[0];
//            CourseImageEntity courseImageEntity = (CourseImageEntity) dto[1];
//
//            courseDtoResult.add(entityToDTO(courseEntity, courseImageEntity));
//        }
//        return courseDtoResult;
//    }
//}
