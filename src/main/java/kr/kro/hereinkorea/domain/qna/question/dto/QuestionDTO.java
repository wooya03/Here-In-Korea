package kr.kro.hereinkorea.domain.qna.question.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long qId;
    private String qTitle;
    private String qCategory;
    private String qContents;
    private Boolean qStatus;
    private String memId;
    private String memName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
