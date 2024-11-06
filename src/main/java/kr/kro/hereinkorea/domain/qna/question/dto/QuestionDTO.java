package kr.kro.hereinkorea.domain.qna.question.dto;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long q_id;
    private String title;
    private String q_category;
    private String q_contents;
    private Boolean q_status;
    private MemberEntity user;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
