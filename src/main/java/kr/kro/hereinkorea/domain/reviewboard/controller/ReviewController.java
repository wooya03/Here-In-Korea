package kr.kro.hereinkorea.domain.reviewboard.controller;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 목록 조회 (페이징)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy) {
        Page<ReviewDTO> reviewPage = reviewService.getReviews(page, size, sortBy);
        return ResponseEntity.ok(Map.of(
                "dtoList", reviewPage.getContent(),
                "totalPages", reviewPage.getTotalPages()
        ));
    }

    // 특정 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 리뷰 생성
    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewDTO updatedReviewDTO,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(reviewService.updateReview(id, updatedReviewDTO));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
