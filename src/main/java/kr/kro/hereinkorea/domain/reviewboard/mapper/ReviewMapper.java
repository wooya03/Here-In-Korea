package kr.kro.hereinkorea.domain.reviewboard.mapper;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import org.springframework.stereotype.Component;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
@Component
public class ReviewMapper {

    // Entity -> DTO 변환
    public ReviewDTO toDTO(ReviewEntity review) {

        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .memId(review.getMemId() != null ? review.getMemId().getMemId() : "") // 기본값 제공
                .reviewTag(review.getReviewTag())
                .createdDate(review.getCreatedDate())
                .reviewViews(review.getReviewViews())
                .reviewLikes(review.getReviewLikes())
                .build();

    }

    // DTO -> Entity 변환
    public ReviewEntity toEntity(ReviewDTO reviewDto, MemberEntity member) {
        ReviewEntity review = new ReviewEntity();
        review.setReviewId(reviewDto.getReviewId());
        review.setCreatedDate(reviewDto.getCreatedDate());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewTag(reviewDto.getReviewTag());
        review.setMemId(member); // MemberEntity 설정
        return review;
    }


}