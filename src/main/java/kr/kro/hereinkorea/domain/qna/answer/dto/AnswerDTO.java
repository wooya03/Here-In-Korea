package kr.kro.hereinkorea.domain.qna.answer.dto;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
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
    private MemberEntity user;
    private QuestionEntity question;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
