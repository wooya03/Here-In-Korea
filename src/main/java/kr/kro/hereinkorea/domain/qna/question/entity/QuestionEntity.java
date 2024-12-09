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
@Setter
public class QuestionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 4)
    private String category;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    private MemberEntity member;

    public void okStatus(){this.status = true;}
}
