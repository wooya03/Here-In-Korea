package kr.kro.hereinkorea.domain.user;

import com.querydsl.core.types.PathMetadata;
import jakarta.persistence.*;
import kr.kro.hereinkorea.global.entity.BaseEntity;
import lombok.*;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mem_id;
}
