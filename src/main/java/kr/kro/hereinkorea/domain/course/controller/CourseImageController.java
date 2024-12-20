package kr.kro.hereinkorea.domain.course.controller;

import kr.kro.hereinkorea.domain.course.dto.CourseImageDTO;
import kr.kro.hereinkorea.domain.course.service.CourseImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/images")
@RequiredArgsConstructor
public class CourseImageController {

    private final CourseImageService courseImageService;

    // 코스 이미지 등록
    @PostMapping("/add")
    public ResponseEntity<CourseImageDTO> addCourseImage(@RequestBody CourseImageDTO courseImageDTO) {
        CourseImageDTO savedCourseImage = courseImageService.addCourseImage(courseImageDTO);
        return ResponseEntity.ok(savedCourseImage);
    }

    // 코스 이미지 목록 조회 (courseId에 해당하는 이미지들)
    @GetMapping("/{courseId}")
    public ResponseEntity<List<CourseImageDTO>> getCourseImages(@PathVariable Long courseId) {
        List<CourseImageDTO> courseImages = courseImageService.getCourseImagesByCourseId(courseId);
        return ResponseEntity.ok(courseImages);
    }

    // 코스 이미지 삭제
    @DeleteMapping("/{courseImgId}")
    public ResponseEntity<Void> deleteCourseImage(@PathVariable Long courseImgId) {
        courseImageService.deleteCourseImage(courseImgId);
        return ResponseEntity.noContent().build();
    }
}
