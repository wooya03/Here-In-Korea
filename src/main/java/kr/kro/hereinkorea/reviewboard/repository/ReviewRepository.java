package kr.kro.hereinkorea.reviewboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kro.hereinkorea.reviewboard.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
