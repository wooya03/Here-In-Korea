package kr.kro.hereinkorea.domain.reviewboard.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.reviewboard.mapper.ReviewMapper;
import kr.kro.hereinkorea.domain.reviewboard.repository.ReviewRepository;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public PageResultDTO<ReviewDTO, Object[]> getReviews(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.getReviewCount(pageable);

        return new PageResultDTO<ReviewDTO, Object[]>(result,
                en -> ReviewMapper.entityToDTO((ReviewEntity) en[0], (MemberEntity) en[1])
        );
    }

    public ReviewDTO getReviewById(Long id) {
        Object result = reviewRepository.getReviewById(id);
        Object[] arr = (Object[]) result;
        return ReviewMapper.entityToDTO((ReviewEntity) arr[0], (MemberEntity) arr[1]);
    }

    public void createReview(ReviewDTO reviewDTO) {
        System.out.println("요청받은 ReviewDTO: " + reviewDTO); // 로그 추가
        String memId = reviewDTO.getMemId();
        if(memberRepository.findByMemId(memId).isEmpty()){
            new IllegalArgumentException("회원아이디가 없습니다.");
        }

        ReviewEntity review = ReviewMapper.dtoToEntity(reviewDTO);

        reviewRepository.save(review);

    }

    public ReviewDTO updateReview(Long id, ReviewDTO updatedReviewDTO) {
        ReviewEntity existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id));
        MemberEntity existingMember = existingReview.getMemId();

        updatedReviewDTO.setModifiedDate(LocalDateTime.now());
        ReviewEntity updatedReview = ReviewMapper.dtoToEntity(updatedReviewDTO);
        updatedReview.setReviewId(existingReview.getReviewId());
        updatedReview.setCreatedDate(existingReview.getCreatedDate());
        return ReviewMapper.entityToDTO(reviewRepository.save(updatedReview), existingMember);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id);
        }
        reviewRepository.deleteById(id);
    }
}
