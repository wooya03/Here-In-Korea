package kr.kro.hereinkorea.reviewboard.entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "review_id") // 언더스코어 방식으로 매핑
    private Long reviewId;
=======
    private Long id;
>>>>>>> origin/main

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "review_tag") // 태그는 필수 항목이 아니므로 nullable 설정 없음
    private String reviewTag;

    @Column(name = "review_time", nullable = false, updatable = false)
    private LocalDateTime reviewTime;

    @Column(name = "review_views", nullable = false)
    private int reviewViews = 0;

    @Column(name = "review_likes", nullable = false)
    private int reviewLikes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false) // 외래 키도 언더스코어로 매핑
    private MemberEntity memId;

    @PrePersist
    public void prePersist() {
        this.reviewTime = LocalDateTime.now();
    }

    // 생성자
    public ReviewEntity(String title, MemberEntity member, String content, String hashtags) {
<<<<<<< HEAD
        this.reviewTitle = title;
        this.memId = member;
        this.reviewContent = content;
        this.reviewTag = hashtags;
        this.reviewViews = 0;
        this.reviewLikes = 0;
=======
        this.title = title;
        this.memId= member;
        this.content = content;
        this.hashtags = hashtags;
        this.views = 0;
        this.likes = 0;
>>>>>>> origin/main
    }
}
