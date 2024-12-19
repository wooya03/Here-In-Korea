package kr.kro.hereinkorea.domain.reviewboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewDTO {
    private Long reviewId;         // 리뷰 ID
    private String reviewTitle;    // 제목
    private String memId;          // 작성자 회원 ID (String 타입)
    private String reviewContent;  // 내용
    private String reviewTag;      // 태그
    private LocalDateTime createdDate; // 생성 날짜
    private int reviewViews = 0;   // 조회수 기본값
    private int reviewLikes = 0;   // 좋아요 기본값
}

