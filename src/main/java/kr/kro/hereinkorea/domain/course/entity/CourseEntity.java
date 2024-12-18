package kr.kro.hereinkorea.domain.course.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId; // 코스 아이디 (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false)
    private MemberEntity memId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle; // 코스 제목

    @Column(name = "course_content", columnDefinition = "TEXT", nullable = false)
    private String courseContent; // 코스 내용

    @Column(name = "course_tag")
    private String courseTag; // 코스 태그

    @Column(name = "course_view", nullable = false)
    private int courseView = 0; // 조회수 (기본값 0)

    @Column(name = "course_like", nullable = false)
    private int courseLike = 0; // 좋아요 수 (기본값 0)

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // 작성일

    @Column(name = "courses_name", nullable = false)
    private String courseName; // 코스명

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseImageEntity> images = new ArrayList<>(); // 코스 이미지 리스트
}
