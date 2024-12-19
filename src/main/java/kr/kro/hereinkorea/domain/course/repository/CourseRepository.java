package kr.kro.hereinkorea.domain.course.repository;

import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    @Query(value = "SELECT c, m " +
            "FROM CourseEntity c " +
            "LEFT JOIN c.memId m ",
            countQuery = "SELECT COUNT(c) " +
                    "FROM CourseEntity c")
    Page<Object[]> getCourseCount(Pageable pageable);

    @Query(
            value = "SELECT c, m " +
                    "FROM CourseEntity c " +
                    "LEFT JOIN c.memId m " +
                    "WHERE c.memId.memId = :memId",
            countQuery = "SELECT COUNT(c) " +
                    "FROM CourseEntity c " +
                    "WHERE c.memId.memId = :memId")
    Page<Object[]> getCourseByMemId(@Param("memId") String memId, Pageable pageable);

    @Query(
            value = "SELECT c, m " +
                    "FROM CourseEntity c " +
                    "LEFT JOIN c.memId m " +
                    "WHERE c.courseTitle LIKE CONCAT('%', :courseTitle, '%')",
            countQuery = "SELECT COUNT(c) " +
                    "FROM CourseEntity c " +
                    "WHERE c.courseTitle LIKE CONCAT('%', :courseTitle, '%')")
    Page<Object[]> getCourseByTitle(@Param("courseTitle") String courseTitle, Pageable pageable);

    @Query(
            value = "SELECT c, m " +
                    "FROM CourseEntity c " +
                    "LEFT JOIN c.memId m " +
                    "WHERE c.memId.memId = :memId AND " +
                    "c.courseTitle LIKE CONCAT('%', :courseTitle, '%')",
            countQuery = "SELECT COUNT(c) " +
                    "FROM CourseEntity c " +
                    "WHERE c.memId.memId = :memId AND " +
                    "c.courseTitle LIKE CONCAT('%', :courseTitle, '%')")
    Page<Object[]> getCourseByTitleAndMemId(@Param("memId") String memId, @Param("courseTitle") String courseTitle, Pageable pageable);
}
