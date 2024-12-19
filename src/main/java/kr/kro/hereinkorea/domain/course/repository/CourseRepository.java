package kr.kro.hereinkorea.domain.course.repository;

import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    // 기본적인 JPA 메서드 사용 가능
}
