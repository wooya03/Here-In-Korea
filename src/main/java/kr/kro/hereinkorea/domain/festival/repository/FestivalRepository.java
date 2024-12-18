package kr.kro.hereinkorea.domain.festival.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FestivalRepository extends JpaRepository<FestivalEntity,Long> {
    @Query("SELECT f, fi FROM FestivalEntity f " +
            "LEFT JOIN FestivalImgEntity fi ON f.contentId = fi.festival.contentId")
    Page<Object[]> getFestivalWithImages(Pageable pageable);

    Optional<FestivalEntity> findByContentId(Long contentId);
}
