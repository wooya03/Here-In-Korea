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
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .memId(review.getMemId() != null ? review.getMemId().getMemId() : null) // Member에서 userId 추출
                .hashtags(review.getHashtags())
                .createdDate(review.getCreatedDate())
                .views(review.getViews())
                .likes(review.getLikes())
                .build();
    }

    // DTO -> Entity 변환
    public ReviewEntity toEntity(ReviewDto reviewDto, MemberEntity member) {
        ReviewEntity review = new ReviewEntity();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setHashtags(reviewDto.getHashtags());
        review.setMemId(member); // MemberEntity 설정
        return review;
    }
}
