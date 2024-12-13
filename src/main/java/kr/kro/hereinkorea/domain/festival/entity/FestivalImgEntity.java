package kr.kro.hereinkorea.domain.festival.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "festival_img")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FestivalImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private FestivalEntity festival;

    @Column
    private String firstimage; //원본 이미지

    @Column String firstimage2; //썸네일 이미지

}
