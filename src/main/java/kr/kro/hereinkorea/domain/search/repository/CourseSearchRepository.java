package kr.kro.hereinkorea.domain.search.repository;

import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseSearchRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "SELECT c " +
            "FROM CourseEntity c " +
            "WHERE c.courseTitle LIKE CONCAT('%', :courseTitle, '%') " +
            "ORDER BY c.courseId DESC " +
            "LIMIT 4 "
    )
    List<Object[]> findTop4ByTitleContaining(@Param("courseTitle")String courseTitle);


}
