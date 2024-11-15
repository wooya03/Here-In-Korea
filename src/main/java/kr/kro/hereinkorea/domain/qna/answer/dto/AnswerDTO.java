package kr.kro.hereinkorea.domain.qna.answer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private Long aId;
    private String aContents;
    private String memId;
    private Long qId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}