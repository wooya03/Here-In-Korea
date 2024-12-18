package kr.kro.hereinkorea.domain.reviewboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {
    private Long reviewId;
    private String reviewTitle;
    private String memId; // 읽기 전용 (프론트엔드에서 memId는 전송되지 않음)
    private String reviewContent;
    private String reviewTag;
    private LocalDateTime createdDate;
    private int reviewViews;
    private int reviewLikes;
}
