package kr.kro.hereinkorea.domain.search.repository;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HotelSearchRepository extends JpaRepository<HotelsEntity, Long> {

    List<HotelsEntity> findTop3ByTitleLike(String title);   // 결과값 -> 최대3개
}
