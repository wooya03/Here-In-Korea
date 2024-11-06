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
    private Long q_id;

    @Column(nullable = false, length = 20)
    private String q_title;

    @Column(nullable = false, length = 4)
    private String q_category;

    @Column(nullable = false, length = 255)
    private String q_contents;

    @Column(nullable = false)
    private Boolean q_status;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity user;

}
