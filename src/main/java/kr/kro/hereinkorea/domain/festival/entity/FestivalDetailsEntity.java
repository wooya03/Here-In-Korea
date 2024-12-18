package kr.kro.hereinkorea.domain.festival.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "festival_details")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FestivalDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    private FestivalEntity festival;

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
    @Lob
    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;    //개요(상세내용)
}
