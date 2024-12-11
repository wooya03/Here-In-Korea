package kr.kro.hereinkorea.domain.event.repository;

import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query(
            value = "SELECT e, i " +
                    "FROM EventEntity e " +
                    "LEFT JOIN EventImgEntity i ON i.event = e "+
                    "GROUP BY e ",
            countQuery = "SELECT COUNT(e) " +
                    "FROM EventEntity e ")
    Page<Object[]> getEventCount(Pageable contentid);
}
