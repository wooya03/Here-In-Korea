package kr.kro.hereinkorea.domain.qna.answer.dto;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.user.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private Long a_id;
    private String q_contents;
    private UserEntity user;
    private QuestionEntity question;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
