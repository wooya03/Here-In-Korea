package kr.kro.hereinkorea.domain.reviewboard.service;

import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.reviewboard.mapper.ReviewMapper;
import kr.kro.hereinkorea.domain.reviewboard.repository.ReviewRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMapper reviewMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public Page<ReviewDTO> getReviews(int page, int size, String sortBy) {
        logger.debug("Fetching reviews with page: {}, size: {}, sortBy: {}", page, size, sortBy);
        try {
            // 유효한 정렬 필드 확인
            if (!isValidSortBy(sortBy)) {
                throw new IllegalArgumentException("유효하지 않은 정렬 필드입니다: " + sortBy);
            }

            // 페이지 및 정렬 기준 설정
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

            // 리뷰 데이터 조회
            Page<ReviewEntity> reviewPage = reviewRepository.findAll(pageable);

            // 결과 반환
            logger.debug("Fetched reviewPage: {}", reviewPage);
            return reviewPage.map(reviewMapper::toDTO);
        } catch (IllegalArgumentException e) {
            logger.error("잘못된 정렬 기준이 전달되었습니다: {}", sortBy);
            throw new RuntimeException("리뷰 목록을 가져오는 데 실패했습니다. 잘못된 정렬 기준: " + sortBy, e);
        } catch (Exception e) {
            logger.error("리뷰 목록을 가져오는 데 실패했습니다.", e);
            throw new RuntimeException("리뷰 목록을 가져오는 데 실패했습니다.", e);
        }
    }


    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id) // 특정 ID로 리뷰 조회
                .map(reviewMapper::toDTO);  // Entity -> DTO 변환
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO, MemberEntity member) {
        validateReviewDto(reviewDTO); // 유효성 검사 추가
        // 수정: DTO의 memId를 사용하지 않고 currentMember를 그대로 전달
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO, member);
        reviewEntity.setCreatedDate(LocalDateTime.now());
        return reviewMapper.toDTO(reviewRepository.save(reviewEntity));
    }

    public ReviewDTO updateReview(Long id, ReviewDTO updatedReviewDTO) {
        ReviewEntity existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id));

        ReviewEntity updatedReview = reviewMapper.toEntity(updatedReviewDTO, existingReview.getMemId()); // 기존 Member 유지
        updatedReview.setReviewId(existingReview.getReviewId()); // 기존 reviewId 유지
        updatedReview.setCreatedDate(existingReview.getCreatedDate()); // 기존 생성일자 유지
        return reviewMapper.toDTO(reviewRepository.save(updatedReview));
    }


    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id);
        }
        reviewRepository.deleteById(id);
    }

    // 유효한 정렬 필드를 확인하는 메서드
    private boolean isValidSortBy(String sortBy) {
        return sortBy.equals("createdDate") || sortBy.equals("views") || sortBy.equals("likes");
    }

    private void validateReviewDto(ReviewDTO reviewDTO) {
        if (reviewDTO.getReviewTitle() == null || reviewDTO.getReviewTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("리뷰 제목은 필수 입력 항목입니다.");
        }
        if (reviewDTO.getReviewContent() == null || reviewDTO.getReviewContent().trim().isEmpty()) {
            throw new IllegalArgumentException("리뷰 내용은 필수 입력 항목입니다.");
        }
    }
}