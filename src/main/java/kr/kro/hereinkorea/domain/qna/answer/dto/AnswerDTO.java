package kr.kro.hereinkorea.domain.qna.answer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private Long id;
    private String contents;
    private String memId;
    private Long questionId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}