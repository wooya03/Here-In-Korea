package kr.kro.hereinkorea.domain.hotels.repository;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelsRepository extends JpaRepository<HotelsEntity, Long> {

    @Query(
            value = "SELECT h, i, r " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h " +
                    "LEFT JOIN RoomEntity r ON r.hotels = h " +
                    "WHERE h.contentid = :id "
    )
    List<Object[]> getHotelsById(@Param("id") Long id);

    @Query(
            value = "SELECT h, i " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h "+
                    "GROUP BY h ORDER BY title asc ",
            countQuery = "SELECT COUNT(h) " +
                    "FROM HotelsEntity h ")
    Page<Object[]> getHotelsCountAsc(Pageable pageable);

    @Query(
            value = "SELECT h, i " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h "+
                    "GROUP BY h ORDER BY title desc ",
            countQuery = "SELECT COUNT(h) " +
                    "FROM HotelsEntity h ")
    Page<Object[]> getHotelsCountDesc(Pageable pageable);

    @Query(
            value = "SELECT h, i " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h "+
                    "WHERE areacode = :areaCode "+
                    "GROUP BY h ORDER BY title desc ",
            countQuery = "SELECT COUNT(h) " +
                    "FROM HotelsEntity h ")
    Page<Object[]> getHotelsByAreaDesc(@Param("areaCode") int areaCode, Pageable pageable);


    @Query(
            value = "SELECT h, i " +
                    "FROM HotelsEntity h " +
                    "LEFT JOIN HotelsImgEntity i ON i.hotels = h "+
                    "WHERE areacode = :areaCode "+
                    "GROUP BY h ORDER BY title asc ",
            countQuery = "SELECT COUNT(h) " +
                    "FROM HotelsEntity h ")
    Page<Object[]> getHotelsByAreaAsc(@Param("areaCode") int areaCode, Pageable pageable);
}
