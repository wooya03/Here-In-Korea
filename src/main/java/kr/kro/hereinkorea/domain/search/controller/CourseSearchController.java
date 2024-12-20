package kr.kro.hereinkorea.domain.search.controller;


import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.search.service.CourseSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseSearchController {
    private final CourseSearchServiceImpl courseService;

    @GetMapping("/search6")
    public List<CourseDTO> searchTitle(@RequestParam("courseTitle")String courseTitle){
        return courseService.searchCourseByTitle(courseTitle);
    }

}
