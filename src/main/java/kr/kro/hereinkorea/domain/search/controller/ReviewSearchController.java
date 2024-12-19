package kr.kro.hereinkorea.domain.search.controller;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.search.service.ReviewSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewSearchController {
    private final ReviewSearchServiceImpl reviewService;


    @GetMapping("/search5")
    List<ReviewDTO> searchReview(@RequestParam("reviewTitle")String reviewTitle){
        return reviewService.searchReviewsByTitle(reviewTitle);
    }
}
