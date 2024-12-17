package kr.kro.hereinkorea.domain.festival.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "festival")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FestivalEntity {
    @Id
    private Long contentId;

    @Column
    private int contentTypeId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false)
    private String addr1;

    @Column()
    private String addr2;

    @Column(nullable = false)
    private int areacode;

    @Column(nullable = false)
    private Double mapx;

    @Column(nullable = false)
    private Double mapy;

    @Column(length = 20)
    private String tel;

    @Column(nullable = false)
    private LocalDate eventStartDate;

    @Column(nullable = false)
    private LocalDate eventEndDate;

}