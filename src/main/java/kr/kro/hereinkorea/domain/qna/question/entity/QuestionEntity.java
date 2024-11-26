package kr.kro.hereinkorea.domain.qna.question.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "question")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qId;

    @Column(nullable = false, length = 20)
    private String qTitle;

    @Column(nullable = false, length = 4)
    private String qCategory;

    @Column(nullable = false)
    private String qContents;

    @Column(nullable = false)
    private Boolean qStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

}
