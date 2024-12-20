package kr.kro.hereinkorea.domain.course.service;

import kr.kro.hereinkorea.domain.course.dto.CourseImageDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
import kr.kro.hereinkorea.domain.course.repository.CourseImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseImageService {

    private final CourseImageRepository courseImageRepository;

    public CourseImageEntity saveCourseImage(CourseImageDTO courseImageDTO) {
        CourseImageEntity courseImageEntity = new CourseImageEntity();
        courseImageEntity.setCourseId(courseImageDTO.getCourseId()); // String 타입으로 설정
        courseImageEntity.setCourseImageUrl(courseImageDTO.getCourseImageUrl());
        return courseImageRepository.save(courseImageEntity);
    }

    // 코스 이미지 등록
    public CourseImageDTO addCourseImage(CourseImageDTO courseImageDTO) {
        CourseImageEntity courseImageEntity = new CourseImageEntity();
        courseImageEntity.setCourseId(courseImageDTO.getCourseId());
        courseImageEntity.setCourseImageUrl(courseImageDTO.getCourseImageUrl());
        courseImageEntity = courseImageRepository.save(courseImageEntity);
        return CourseImageDTO.entityToDTO(courseImageEntity);
    }

    // 코스 이미지 목록 조회
    public List<CourseImageDTO> getCourseImagesByCourseId(Long courseId) {
        List<CourseImageEntity> courseImageEntities = courseImageRepository.findAll();
        return courseImageEntities.stream()
                .filter(entity -> entity.getCourseId().equals(courseId)) // courseId를 String으로 비교
                .map(CourseImageDTO::entityToDTO)
                .collect(Collectors.toList());
    }

    // 코스 이미지 삭제
    public void deleteCourseImage(Long courseImgId) {
        courseImageRepository.deleteById(courseImgId);
    }
}
