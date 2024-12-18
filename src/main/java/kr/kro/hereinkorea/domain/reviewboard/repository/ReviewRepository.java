package kr.kro.hereinkorea.domain.reviewboard.repository;

import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
