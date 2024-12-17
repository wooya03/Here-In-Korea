package kr.kro.hereinkorea.domain.reviewboard.mapper;

import org.springframework.stereotype.Component;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDto;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;

@Component
public class ReviewMapper {

    // Entity -> DTO 변환
    public ReviewDto toDto(ReviewEntity review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .memId(review.getMemId() != null ? review.getMemId().getMemId() : null) // Member에서 userId 추출
                .reviewTag(review.getReviewTag())
                .reviewTime(review.getReviewTime())
                .reviewViews(review.getReviewViews())
                .reviewLikes(review.getReviewLikes())
                .build();
    }

    // DTO -> Entity 변환
    public ReviewEntity toEntity(ReviewDto reviewDto, MemberEntity member) {
        ReviewEntity review = new ReviewEntity();
        review.setReviewId(reviewDto.getReviewId());
        review.setReviewTime(reviewDto.getReviewTime());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewTag(reviewDto.getReviewTag());
        review.setMemId(member); // MemberEntity 설정
        return review;
    }
}
