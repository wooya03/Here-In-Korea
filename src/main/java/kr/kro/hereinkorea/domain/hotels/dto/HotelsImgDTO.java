package kr.kro.hereinkorea.domain.hotels.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelsImgDTO {
    private Long id;
    private Long contentid;
    private String firstimage; // 대표이미지(원본)
    private String firstimage2; //대표이미지(썸네일)
}
