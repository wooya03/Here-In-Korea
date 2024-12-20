package kr.kro.hereinkorea.domain.course.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "course")
@Builder
public class CourseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseTitle;

    @Column(nullable = false)
    private String courseContent;

    @Column()
    private String courseTag;

    @ColumnDefault("0")
    private int courseViews;

    @Column(name = "course_likes", nullable = false)
    @ColumnDefault("0")
    private int courseLikes;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String courseImageUrl; // 이미지 주소
}
