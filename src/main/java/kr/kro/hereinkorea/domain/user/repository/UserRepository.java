package kr.kro.hereinkorea.domain.user.repository;

import kr.kro.hereinkorea.domain.user.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, String> {

}
