package kr.kro.hereinkorea.domain.event.repository;

import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
