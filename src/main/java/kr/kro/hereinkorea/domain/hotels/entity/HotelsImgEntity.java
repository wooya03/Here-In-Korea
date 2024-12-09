package kr.kro.hereinkorea.domain.hotels.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotels_img")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HotelsImgEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 지연
    private HotelsEntity hotels;

    @Column
    private String firstimage; // 대표이미지(원본)

    @Column
    private String firstimage2; //대표이미지(썸네일)


}
