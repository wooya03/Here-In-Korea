package kr.kro.hereinkorea.domain.festival.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FestivalDetailsRepository extends JpaRepository<FestivalDetailsEntity,Long> {
    Optional<FestivalDetailsEntity> findByFestivalContentId(Long contentId);

}
