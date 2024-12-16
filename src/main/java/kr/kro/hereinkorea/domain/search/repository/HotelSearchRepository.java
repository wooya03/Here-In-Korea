package kr.kro.hereinkorea.domain.search.repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface HotelSearchRepository extends JpaRepository<HotelsEntity, Long> {


    @Query(value = "SELECT h, i " +
            "FROM HotelsEntity h " +
            "LEFT JOIN HotelsImgEntity i ON i.hotels = h " +
            "WHERE h.title LIKE CONCAT('%', :title, '%') " +
            "ORDER BY h.contentid DESC " +
            "LIMIT 4 "
    )
    List<Object[]> findTop4ByTitleContaining(@Param("title")String title);

}
