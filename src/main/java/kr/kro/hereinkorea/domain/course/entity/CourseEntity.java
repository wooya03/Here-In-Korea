package kr.kro.hereinkorea.domain.course.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "course")
@Builder
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "course_content", nullable = false)
    private String courseContent;

    @Column(name = "course_tag")
    private String courseTag;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "course_views", nullable = false)
    private int courseViews = 0;

    @Column(name = "course_likes", nullable = false)
    private int courseLikes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false)
    private MemberEntity memId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @PrePersist
    public void prePersist() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }
}
