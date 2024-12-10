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
    private String addr1;

    @Column(nullable = false)
    private String addr2;

    @Column(nullable = false)
    private Double mapx;

    @Column(nullable = false)
    private Double mapy;

    @Column(nullable = false)
    private int areacode;

    @Column()
    private Date eventstartdate;

    @Column()
    private Date eventenddate;
}
