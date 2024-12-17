package kr.kro.hereinkorea.domain.festival.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FestivalDetailsDTO {
    private Long id;
    private Long contentid;
    private String sponsor1; // 주최자 정보
    private String sponsor1tel; // 주최자 연락처
    private String eventstartdate; // 이벤트 시작일
    private String eventenddate; // 이벤트 종료일
    private String playtime; // 공연 시간
    private String eventplace; // 행사 장소
    private String usetimefestival; // 이용 요금
}
