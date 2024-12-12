package kr.kro.hereinkorea.domain.hotels.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelsDTO {
    private Long contentid;
    private String title;
    private String addr1;
    private String addr2;
    private int areacode;
    private Double mapx;
    private Double mapy;
    private String tel;
    private String firstimage;
    private String firstimage2;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}

