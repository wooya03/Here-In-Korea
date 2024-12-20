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
    private Long courseImgId; // 코스 이미지 아이디 (PK)

    @Column(nullable = false)
    private Long courseId; // String 타입으로 변경

    @Column(name = "course_ing_url", nullable = false)
    private String courseImageUrl; // 이미지 주소
}

