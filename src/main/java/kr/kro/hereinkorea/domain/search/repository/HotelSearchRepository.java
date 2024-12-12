package kr.kro.hereinkorea.domain.search.repository;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HotelSearchRepository extends JpaRepository<HotelsEntity, Long> {


// @Query("SELECT h FROM HotelsEntity h WHERE h.title LIKE %:title%")
    List<HotelsEntity> findByTitleLike(String title);
}
