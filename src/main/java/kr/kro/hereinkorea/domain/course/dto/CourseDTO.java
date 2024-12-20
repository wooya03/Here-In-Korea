package kr.kro.hereinkorea.domain.course.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    private Long courseId;          // 코스 ID
    private String memId;           // 작성자 회원 ID (String 타입)
    private String courseTitle;     // 코스 제목
    private String courseContent;   // 코스 내용
    private String courseTag;       // 코스 태그
    private LocalDateTime createdDate; // 생성 날짜
    private int courseViews;    // 조회수 기본값
    private int courseLikes;    // 좋아요 기본값
    private String courseName;      // 코스명
    private String courseImgUrl;
}
