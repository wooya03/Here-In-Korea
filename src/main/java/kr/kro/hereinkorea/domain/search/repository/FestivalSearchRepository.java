package kr.kro.hereinkorea.domain.search.repository;

import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FestivalSearchRepository extends JpaRepository<FestivalEntity, Long> {

    @Query(value = "SELECT f, i " +
            "FROM FestivalEntity f " +
            "LEFT JOIN FestivalImgEntity i ON i.festival = f " +
            "WHERE f.title LIKE CONCAT('%', :title, '%') " +
            "ORDER BY f.contentId DESC " +
            "LIMIT 4 "
    )
    List<Object[]> findTop4ByTitleContaining(@Param("title")String title);


    @Query(value = "SELECT f, i " +
            "FROM FestivalEntity f " +
            "LEFT JOIN FestivalImgEntity i ON i.festival = f " +
            "WHERE f.addr1 LIKE CONCAT('%', :addr1, '%') " +
            "ORDER BY f.contentId DESC " +
            "LIMIT 4 "
    )
    List<Object[]> findTop4ByAddrContaining(@Param("addr1")String addr1);


}
