package kr.kro.hereinkorea.reviewboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.kro.hereinkorea.reviewboard.entity.ReviewEntity;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(
            value = "SELECT r, m " +
                    "FROM ReviewEntity r " +
                    "LEFT JOIN r.memId m ",
            countQuery = "SELECT COUNT(r) " +
                    "FROM ReviewEntity r")
    Page<Object[]> getReviewCount(Pageable pageable);
}
