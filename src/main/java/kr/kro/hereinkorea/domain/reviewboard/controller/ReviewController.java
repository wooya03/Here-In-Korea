package kr.kro.hereinkorea.domain.reviewboard.controller;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 목록 조회 (페이징)
    @GetMapping
    public ResponseEntity<Object> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy) {

        Page<ReviewDTO> reviewPage = reviewService.getReviews(page, size, sortBy);

        return ResponseEntity.ok(Map.of(
                "dtoList", reviewPage.getContent(),   // 실제 리뷰 리스트
                "totalPages", reviewPage.getTotalPages() // 전체 페이지 수
        ));
    }

    // 특정 리뷰 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(
            @RequestBody ReviewDTO reviewDTO,
            @AuthenticationPrincipal MemberEntity currentMember) { // 현재 사용자 정보
        return ResponseEntity.ok(reviewService.createReview(reviewDTO, currentMember));
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewDTO updatedReviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, updatedReviewDTO));
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
