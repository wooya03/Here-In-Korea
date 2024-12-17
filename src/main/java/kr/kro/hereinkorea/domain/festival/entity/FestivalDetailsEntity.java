package kr.kro.hereinkorea.domain.festival.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FestivalDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FestivalEntity와 연결 (외래키)
    @ManyToOne(fetch = FetchType.LAZY)
    private FestivalEntity festivalEntity;

    @Column
    private String sponsor1; // 주최자 정보
    @Column
    private String sponsor1tel; // 주최자 연락처
    @Column
    private String eventstartdate; // 이벤트 시작일
    @Column
    private String eventenddate; // 이벤트 종료일
    @Column
    private String playtime; // 공연 시간
    @Column
    private String eventplace; // 행사 장소
    @Column
    private String usetimefestival; // 이용 요금
}
