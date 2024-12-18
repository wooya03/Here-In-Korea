package kr.kro.hereinkorea.domain.reviewboard.mapper;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.stereotype.Component;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;

import java.time.LocalDateTime;

@Component
public class ReviewMapper {

    // Entity -> DTO 변환
    public ReviewDTO toDTO(ReviewEntity review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .memId(review.getMemId() != null ? review.getMemId().getMemId() : null) // String 타입
                .reviewTag(review.getReviewTag())
                .createdDate(review.getCreatedDate())
                .reviewViews(review.getReviewViews())
                .reviewLikes(review.getReviewLikes())
                .build();
    }

    // DTO -> Entity 변환
    public ReviewEntity toEntity(ReviewDTO reviewDto, MemberEntity member) {
        return ReviewEntity.builder()
                .reviewId(reviewDto.getReviewId())
                .reviewTitle(reviewDto.getReviewTitle())
                .reviewTag(reviewDto.getReviewTag())
                .reviewContent(reviewDto.getReviewContent())
                .createdDate(reviewDto.getCreatedDate() != null ? reviewDto.getCreatedDate() : LocalDateTime.now())
                .reviewViews(reviewDto.getReviewViews())
                .reviewLikes(reviewDto.getReviewLikes())
                .memId(member) // MemberEntity 객체 매핑
                .build();
    }
}