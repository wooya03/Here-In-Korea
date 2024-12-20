package kr.kro.hereinkorea.domain.reviewboard.mapper;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;

import java.time.LocalDateTime;

public class ReviewMapper {

    // Entity -> DTO 변환
    public static ReviewDTO entityToDTO(ReviewEntity review, MemberEntity member) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .memId(member.getMemId())
                .reviewTag(review.getReviewTag())
                .createdDate(review.getCreatedDate())
                .reviewViews(review.getReviewViews())
                .reviewLikes(review.getReviewLikes())
                .build();
    }

    // DTO -> Entity 변환
    public static ReviewEntity dtoToEntity(ReviewDTO reviewDto) {
        MemberEntity member = MemberEntity.builder().memId(reviewDto.getMemId()).build();
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