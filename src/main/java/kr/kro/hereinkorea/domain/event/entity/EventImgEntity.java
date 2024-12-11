package kr.kro.hereinkorea.domain.event.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_img")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EventImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 지연
    private EventEntity event;

    @Column
    private String firstimage; // 대표이미지(원본)

    @Column
    private String firstimage2; //대표이미지(썸네일)
}
