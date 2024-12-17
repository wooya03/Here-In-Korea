package kr.kro.hereinkorea.domain.festival.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FestivalDTO {
    private Long contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private int areacode;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String firstimage;
    private String firstimage2;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
}
