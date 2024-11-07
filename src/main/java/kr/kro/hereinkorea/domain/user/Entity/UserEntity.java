package kr.kro.hereinkorea.domain.user.Entity;

import jakarta.persistence.*;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity extends BaseEntity {
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

    @Column(nullable = false, length = 20)
    private String memTel;

    @Column(nullable = true)
    private Date signDate;

    @Column(nullable = true)
    private Date loginDate;
}

