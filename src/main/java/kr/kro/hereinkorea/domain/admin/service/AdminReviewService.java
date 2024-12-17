package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import kr.kro.hereinkorea.reviewboard.dto.ReviewDto;
import kr.kro.hereinkorea.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.reviewboard.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    public PageResultDTO<ReviewDto, Object[]> getReview(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.getReviewCount(pageable);
        return new PageResultDTO<ReviewDto, Object[]>(result,
                                             en -> entityToDTO((ReviewEntity) en[0], (MemberEntity) en[1]));
    }

    private ReviewDto entityToDTO(ReviewEntity review, MemberEntity member){
        return ReviewDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .memId(member.getMemId())
                .views(review.getViews())
                .likes(review.getLikes())
                .createdDate(review.getCreatedDate())
                .content(review.getContent())
                .build();
    }
}
