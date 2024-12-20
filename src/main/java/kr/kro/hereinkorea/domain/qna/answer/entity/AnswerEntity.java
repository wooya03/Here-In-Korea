package kr.kro.hereinkorea.domain.qna.answer.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "Answer")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class AnswerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @OneToOne(fetch = FetchType.EAGER) // 지연
    private QuestionEntity question; // 1 : 1

    public void changeQuestion(QuestionEntity question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        this.question = question;
    }
}
