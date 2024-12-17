package kr.kro.hereinkorea.reviewboard.service;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.reviewboard.dto.ReviewDto;
import kr.kro.hereinkorea.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.reviewboard.mapper.ReviewMapper;
import kr.kro.hereinkorea.reviewboard.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);


    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }


    public Page<ReviewDto> getReviews(int page, int size, String sortBy) {
    if (!isValidSortBy(sortBy)) {
        throw new IllegalArgumentException("유효하지 않은 정렬 필드입니다: " + sortBy);
    }

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
    Page<ReviewEntity> reviewPage = reviewRepository.findAll(pageable);

    logger.debug("Fetched reviewPage: {}", reviewPage); // 적절한 로깅
    return reviewPage.map(reviewMapper::toDto);
    }

    public Optional<ReviewDto> getReviewById(Long id) {
        return reviewRepository.findById(id) // 특정 ID로 리뷰 조회
                .map(reviewMapper::toDto);  // Entity -> DTO 변환
<<<<<<< HEAD
    } 
=======
    }    
>>>>>>> origin/main

    public ReviewDto createReview(ReviewDto reviewDto, MemberEntity member) {
        validateReviewDto(reviewDto); // 유효성 검사 추가
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDto, member);
        reviewEntity.setReviewTime(LocalDateTime.now());
        return reviewMapper.toDto(reviewRepository.save(reviewEntity));
    }
    

    public ReviewDto updateReview(Long id, ReviewDto updatedReviewDto) {
        ReviewEntity existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id));
    
        ReviewEntity updatedReview = reviewMapper.toEntity(updatedReviewDto, existingReview.getMemId()); // 기존 Member 유지
<<<<<<< HEAD
        updatedReview.setReviewId(existingReview.getReviewId());
        updatedReview.setReviewTime(existingReview.getReviewTime()); // 기존 생성일자 유지
=======
        updatedReview.setId(existingReview.getId());
        updatedReview.setCreatedDate(existingReview.getCreatedDate()); // 기존 생성일자 유지
>>>>>>> origin/main
    
        return reviewMapper.toDto(reviewRepository.save(updatedReview));
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


    private void validateReviewDto(ReviewDto reviewDto) {
        if (reviewDto.getReviewTitle() == null || reviewDto.getReviewTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("리뷰 제목은 필수 입력 항목입니다.");
        }
        if (reviewDto.getReviewContent() == null || reviewDto.getReviewContent().trim().isEmpty()) {
            throw new IllegalArgumentException("리뷰 내용은 필수 입력 항목입니다.");
        }
    }
    
}
