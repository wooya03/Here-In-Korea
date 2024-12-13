package kr.kro.hereinkorea.domain.hotels.repository;

import kr.kro.hereinkorea.domain.hotels.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
