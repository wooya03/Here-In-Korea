package kr.kro.hereinkorea.domain.member.Entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity {
    @Id
    @Column(length = 30)
    private String memId;

    @Column(nullable = false, length = 255)
    private String memName;

    @Column(nullable = false, length = 30)
    private String memPass;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false, length = 10)
    private String birth;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = true)
    private Date signDate;

    @Column(nullable = true)
    private Date loginDate;
}

