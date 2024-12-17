package kr.kro.hereinkorea.reviewboard.dto;

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

public class ReviewDto {
<<<<<<< HEAD
    private Long reviewId;
    private String reviewTitle;
=======
    private Long id;
    private String title;
>>>>>>> origin/main
    private String memId;
    private String reviewContent;
    private String reviewTag;
    private LocalDateTime reviewTime;
    private int reviewViews;
    private int reviewLikes;
}
