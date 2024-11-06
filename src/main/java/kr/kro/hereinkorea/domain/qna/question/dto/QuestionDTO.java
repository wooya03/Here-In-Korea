package kr.kro.hereinkorea.domain.qna.question.dto;

import kr.kro.hereinkorea.domain.user.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long qId;
    private String title;
    private String qCategory;
    private String qContents;
    private Boolean qStatus;
    private String userId;
    private String userName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
