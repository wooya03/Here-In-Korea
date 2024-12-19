package kr.kro.hereinkorea.domain.course.repository;

import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    // 추가적인 커스텀 메서드가 필요하면 작성
}
