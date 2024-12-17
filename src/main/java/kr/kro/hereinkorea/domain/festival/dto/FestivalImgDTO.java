package kr.kro.hereinkorea.domain.festival.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FestivalImgDTO {
    private Long id;
    private Long contentid;
    private String firstimage;
    private String firstimage2;
}
