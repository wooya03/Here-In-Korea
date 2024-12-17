package kr.kro.hereinkorea.domain.reviewboard.repository;

import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
