package kr.kro.hereinkorea.domain.course.repository;

import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseImageRepository extends JpaRepository<CourseImageEntity, Long> {
    // 기본적인 JPA 메서드 사용 가능
}
