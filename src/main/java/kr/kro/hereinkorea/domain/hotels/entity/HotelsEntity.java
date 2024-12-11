package kr.kro.hereinkorea.domain.hotels.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "hotels")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HotelsEntity extends BaseEntity {
    @Id
    private Long contentid;

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
}
