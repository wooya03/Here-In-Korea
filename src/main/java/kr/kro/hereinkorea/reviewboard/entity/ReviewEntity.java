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
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 변경
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String hashtags;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(columnDefinition = "int default 0")
    private int views;

    @Column(columnDefinition = "int default 0")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id", nullable = false)
    private MemberEntity memId;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    // 생성자
    public ReviewEntity(String title, MemberEntity member, String content, String hashtags) {
        this.title = title;
        this.memId= memId;
        this.content = content;
        this.hashtags = hashtags;
        this.views = 0;
        this.likes = 0;
    }
}

