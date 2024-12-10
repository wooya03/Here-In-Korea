package kr.kro.hereinkorea.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {
    private long contentid;
    private String title;
    private String arr1;
    private String arr2;
    private Double mapx;
    private Double mapy;
    private int areacode;
    private String tel;
    private String firstimage;
    private String firstimage2;
    private Date eventstartdate;
    private Date eventenddate;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
