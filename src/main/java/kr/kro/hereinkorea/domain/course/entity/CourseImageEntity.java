package kr.kro.hereinkorea.domain.course.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Course_img")
public class CourseImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_img_id")
    private Long courseImgId; // 코스 이미지 아이디 (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity courseId; // 코스 아이디 (FK)

    @Column(name="course_ing_url", nullable = false)
    private String courseImageUrl; // 이미지 주소
}
