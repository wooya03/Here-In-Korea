package kr.kro.hereinkorea.domain.hotels.repository;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelsRepository extends JpaRepository<HotelsEntity, Long> {

    @Query(
            value = "SELECT h, i " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h "+
                    "GROUP BY h ",
            countQuery = "SELECT COUNT(h) " +
                    "FROM HotelsEntity h ")
    Page<Object[]> getHotelsCount(Pageable pageable);
}
