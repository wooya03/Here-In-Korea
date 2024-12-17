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
    private Long id;
    private String title;
    private String memId;
    private String content;
    private String hashtags;
    private LocalDateTime createdDate;
    private int views;
    private int likes;
}
