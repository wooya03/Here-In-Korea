package kr.kro.hereinkorea.domain.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventImgDTO {
    private Long id;
    private long contentid;
    private String firstimage;
    private String firstimage2;
}
