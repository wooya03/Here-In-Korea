package kr.kro.hereinkorea.domain.festival.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<FestivalEntity,Long> {
}
