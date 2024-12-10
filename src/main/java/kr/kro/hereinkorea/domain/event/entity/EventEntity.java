package kr.kro.hereinkorea.domain.event.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "event")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EventEntity extends BaseEntity {
    @Id
    private long contentid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String arr1;

    @Column(nullable = false)
    private String arr2;

    @Column(nullable = false)
    private Double mapx;

    @Column(nullable = false)
    private Double mapy;

    @Column(nullable = false)
    private int areacode;

    @Column(length = 20, nullable = false)
    private String tel;

    @Column(nullable = false)
    private Date eventstartdate;

    @Column(nullable = false)
    private Date eventenddate;
}
