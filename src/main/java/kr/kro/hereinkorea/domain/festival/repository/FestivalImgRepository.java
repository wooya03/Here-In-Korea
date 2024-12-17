package kr.kro.hereinkorea.domain.festival.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalImgRepository extends JpaRepository<FestivalImgEntity, Long> {
}
