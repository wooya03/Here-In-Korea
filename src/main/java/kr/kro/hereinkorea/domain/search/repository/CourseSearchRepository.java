//package kr.kro.hereinkorea.domain.search.repository;
//
//import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CourseSearchRepository extends JpaRepository<CourseEntity, Long> {
//
//    @Query(value = "SELECT c, i " +
//            "FROM CourseEntity c " +
//            "LEFT JOIN CourseImgEntity i ON i.course = c " +
//            "WHERE c.courseTitle LIKE CONCAT('%', :courseTitle, '%') " +
//            "ORDER BY c.contentId DESC " +
//            "LIMIT 4 "
//    )
//    List<Object[]> findTop4ByTitleContaining(@Param("courseTitle")String courseTitle);
//
//
//}
