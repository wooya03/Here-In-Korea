package kr.kro.hereinkorea.domain.reviewboard.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDto;
import kr.kro.hereinkorea.domain.reviewboard.service.ReviewService;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 목록 조회 (페이징)
    @GetMapping
    public ResponseEntity<Object> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy) {

        Page<ReviewDto> reviewPage = reviewService.getReviews(page, size, sortBy);

        return ResponseEntity.ok(Map.of(
                "dtoList", reviewPage.getContent(),   // 실제 리뷰 리스트
                "totalPages", reviewPage.getTotalPages() // 전체 페이지 수
        ));
    }

    // 특정 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal MemberEntity currentMember) { // 현재 사용자 정보
        return ResponseEntity.ok(reviewService.createReview(reviewDto, currentMember));
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewDto updatedReviewDto) {
        return ResponseEntity.ok(reviewService.updateReview(id, updatedReviewDto));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
