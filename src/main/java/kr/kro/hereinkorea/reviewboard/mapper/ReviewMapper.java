package kr.kro.hereinkorea.reviewboard.mapper;

import org.springframework.stereotype.Component;
import kr.kro.hereinkorea.reviewboard.dto.ReviewDto;
import kr.kro.hereinkorea.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;

@Component
public class ReviewMapper {

    // Entity -> DTO 변환
    public ReviewDto toDto(ReviewEntity review) {
        return ReviewDto.builder()
<<<<<<< HEAD
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
=======
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
>>>>>>> origin/main
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
<<<<<<< HEAD
        review.setReviewId(reviewDto.getReviewId());
        review.setReviewTime(reviewDto.getReviewTime());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewTag(reviewDto.getReviewTag());
=======
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setHashtags(reviewDto.getHashtags());
>>>>>>> origin/main
        review.setMemId(member); // MemberEntity 설정
        return review;
    }
}
