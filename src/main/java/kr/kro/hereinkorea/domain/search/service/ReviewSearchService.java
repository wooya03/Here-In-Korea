package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;

import java.util.List;

public interface ReviewSearchService {

    List<ReviewDTO> searchReviewsByTitle(String title);

    default ReviewDTO entityToDTO(ReviewEntity review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .reviewContent(review.getReviewContent())
                .reviewTag(review.getReviewTag())
                .createdDate(review.getCreatedDate())
                .createdDate(review.getCreatedDate())
                .reviewViews(review.getReviewViews())
                .build();
    }


}
