package kr.kro.hereinkorea.domain.search.repository;

import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewSearchRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "SELECT r " +
            "FROM ReviewEntity r " +
            "WHERE r.reviewTitle LIKE CONCAT('%', :reviewTitle, '%') " +
            "ORDER BY r.reviewId " +
            "LIMIT 4 "
    )
<<<<<<< HEAD
    List<Object[]> findTop4ByReviewTitleContaining(@Param("reviewTitle")String reviewTitle);
=======
    List<Object[]> findTop4ByTitleContaining(@Param("reviewTitle")String reviewTitle);
>>>>>>> f41fef4681a6035791660c2a7acf2545aefa1c6a

}
