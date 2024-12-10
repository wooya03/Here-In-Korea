package kr.kro.hereinkorea.domain.event.repository;

import kr.kro.hereinkorea.domain.event.entity.EventImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventImgRepository extends JpaRepository<EventImgEntity, Long> {
}
