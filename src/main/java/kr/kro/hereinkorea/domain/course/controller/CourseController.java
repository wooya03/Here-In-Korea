package kr.kro.hereinkorea.domain.course.controller;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // 코스 생성
    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO savedCourse = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(savedCourse);
    }

    // 코스 수정
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseDTO updatedCourseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, updatedCourseDTO));
    }

    // 코스 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
