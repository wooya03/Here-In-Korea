package kr.kro.hereinkorea.domain.reviewboard.controller;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.service.ReviewService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 목록 조회 (페이징)
    @GetMapping()
    public PageResultDTO<ReviewDTO, Object[]> getAllReviews(PageRequestDTO requestDTO){
        return reviewService.getReviews(requestDTO);
    }

    // 특정 리뷰 조회
    @GetMapping("/{id}")
    public ReviewDTO getReview(@PathVariable("id") Long id) {
        return reviewService.getReviewById(id);
    }

    // 리뷰 생성
    @PostMapping("/create")
    public String createReview(@RequestBody ReviewDTO reviewDTO) {
        try {
            reviewService.createReview(reviewDTO);
            return "리뷰 작성 완료!";
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 에러 출력
            return "문제 발생: " + e.getMessage();
        }
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable("id") Long id,
            @RequestBody ReviewDTO updatedReviewDTO,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(reviewService.updateReview(id, updatedReviewDTO));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
