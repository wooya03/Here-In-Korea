package kr.kro.hereinkorea.domain.reviewboard.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "review")
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id") // 언더스코어 방식으로 매핑
    private Long reviewId;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "review_tag") // 태그는 필수 항목이 아니므로 nullable 설정 없음
    private String reviewTag;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "review_views", nullable = false)
    private int reviewViews = 0;

    @Column(name = "review_likes", nullable = false)
    private int reviewLikes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false)
    private MemberEntity memId;

    @PrePersist
    public void prePersist() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

    // 생성자
    public ReviewEntity(String title, MemberEntity member, String content, String hashtags) {
        this.reviewTitle = title;
        this.memId = member;
        this.reviewContent = content;
        this.reviewTag = hashtags;
        this.reviewViews = 0;
        this.reviewLikes = 0;
    }
}
