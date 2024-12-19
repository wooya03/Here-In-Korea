package kr.kro.hereinkorea.domain.course.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private String courseId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "course_content", nullable = false)
    private String courseContent;

    @Column(name = "course_tag")
    private String courseTag;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "course_views", nullable = false)
    @ColumnDefault("0")
    private int courseViews;

    @Column(name = "course_likes", nullable = false)
    @ColumnDefault("0")
    private int courseLikes;

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
