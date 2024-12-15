package kr.kro.hereinkorea.domain.qna.question.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String title;
    private String category;
    private String contents;
    private String memId;
    private String memName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String answerContents;
}
