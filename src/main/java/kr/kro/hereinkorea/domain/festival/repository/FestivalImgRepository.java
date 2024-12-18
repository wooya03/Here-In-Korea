package kr.kro.hereinkorea.domain.festival.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalImgRepository extends JpaRepository<FestivalImgEntity, Long> {
    Optional<FestivalImgEntity> findByFestivalContentId(Long contentId);
}
