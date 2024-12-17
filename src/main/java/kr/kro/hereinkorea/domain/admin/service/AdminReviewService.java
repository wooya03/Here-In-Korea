package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.reviewboard.repository.ReviewRepository;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    public PageResultDTO<ReviewDTO, Object[]> getReview(String reviewTitle, String memId, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result;

        if(reviewTitle == null || reviewTitle.trim().isEmpty()){
            if(memId == null || memId.trim().isEmpty() ){
                result = reviewRepository.getReviewCount(pageable);
            } else {
                result = reviewRepository.getReviewById(memId, pageable);
            }
        } else {
            if(memId == null || memId.trim().isEmpty()){
                result = reviewRepository.getReviewByTitle(reviewTitle,pageable);
            } else {
                result = reviewRepository.getReviewByTitleAndId(reviewTitle, memId, pageable);
            }
        }

        return new PageResultDTO<>(result,
                en -> entityToDTO((ReviewEntity) en[0], (MemberEntity) en[1]));
    }

    private ReviewDTO entityToDTO(ReviewEntity review, MemberEntity member){
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .reviewTitle(review.getReviewTitle())
                .memId(member.getMemId())
                .reviewViews(review.getReviewViews())
                .reviewLikes(review.getReviewLikes())
                .reviewTime(review.getReviewTime())
                .reviewContent(review.getReviewContent())
                .build();
    }
}
