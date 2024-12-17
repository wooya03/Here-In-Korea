package kr.kro.hereinkorea.domain.member.repository;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByMemId(String memId);

    @Query("SELECT m.memId FROM MemberEntity m WHERE m.memName = :memName AND m.email = :email")
    Optional<String> findMemberId(@Param("memName") String memName, @Param("email") String email);
}



