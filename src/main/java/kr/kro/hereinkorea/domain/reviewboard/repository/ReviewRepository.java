package kr.kro.hereinkorea.domain.reviewboard.repository;

import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
        
    @Query(
            value = "SELECT r, m " +
                    "FROM ReviewEntity r " +
                    "LEFT JOIN r.memId m ",
            countQuery = "SELECT COUNT(r) " +
                    "FROM ReviewEntity r")
    Page<Object[]> getReviewCount(Pageable pageable);

    @Query(
            value = "SELECT r, m " +
                    "FROM ReviewEntity r " +
                    "LEFT JOIN r.memId m " +
                    "WHERE r.memId.memId = :memId",
            countQuery = "SELECT COUNT(r) " +
                    "FROM ReviewEntity r " +
                    "WHERE r.memId.memId = :memId")
    Page<Object[]> getReviewById(@Param("memId") String memId, Pageable pageable);

    @Query(
            value = "SELECT r, m " +
                    "FROM ReviewEntity r " +
                    "LEFT JOIN r.memId m " +
                    "WHERE r.reviewTitle LIKE CONCAT('%', :reviewTitle, '%')",
            countQuery = "SELECT COUNT(r) " +
                    "FROM ReviewEntity r " +
                    "WHERE r.reviewTitle LIKE CONCAT('%', :reviewTitle, '%')")
    Page<Object[]> getReviewByTitle(@Param("reviewTitle") String reviewTitle, Pageable pageable);

    @Query(
            value = "SELECT r, m " +
                    "FROM ReviewEntity r " +
                    "LEFT JOIN r.memId m " +
                    "WHERE r.memId.memId = :memId AND " +
                    "r.reviewTitle LIKE CONCAT('%', :reviewTitle, '%') ",
            countQuery = "SELECT COUNT(r) " +
                    "FROM ReviewEntity r " +
                    "WHERE r.memId.memId = :memId AND " +
                    "r.reviewTitle LIKE CONCAT('%', :reviewTitle, '%')")
    Page<Object[]> getReviewByTitleAndId(@Param("reviewTitle") String reviewTitle, @Param("memId") String memId, Pageable pageable);
}
