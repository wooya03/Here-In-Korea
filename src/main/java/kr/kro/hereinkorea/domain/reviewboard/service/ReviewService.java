package kr.kro.hereinkorea.domain.reviewboard.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.reviewboard.mapper.ReviewMapper;
import kr.kro.hereinkorea.domain.reviewboard.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public Page<ReviewDTO> getReviews(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortBy)));
        return reviewRepository.findAll(pageable).map(reviewMapper::toDTO);
    }

    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(reviewMapper::toDTO);
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        System.out.println("요청받은 ReviewDTO: " + reviewDTO); // 로그 추가
        var member = memberRepository.findById(reviewDTO.getMemId())
                .orElseThrow(() -> new IllegalArgumentException("회원 ID가 유효하지 않습니다."));

        // DTO를 엔티티로 변환
        var reviewEntity = reviewMapper.toEntity(reviewDTO, member);

        // 엔티티 저장
        var savedEntity = reviewRepository.save(reviewEntity);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return reviewMapper.toDTO(savedEntity);
    }

    public ReviewDTO updateReview(Long id, ReviewDTO updatedReviewDTO) {
        ReviewEntity existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id));
        MemberEntity existingMember = existingReview.getMemId();
        ReviewEntity updatedReview = reviewMapper.toEntity(updatedReviewDTO, existingMember);
        updatedReview.setReviewId(existingReview.getReviewId());
        updatedReview.setCreatedDate(existingReview.getCreatedDate());
        return reviewMapper.toDTO(reviewRepository.save(updatedReview));
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new IllegalArgumentException("리뷰를 찾을 수 없습니다: ID=" + id);
        }
        reviewRepository.deleteById(id);
    }
}
