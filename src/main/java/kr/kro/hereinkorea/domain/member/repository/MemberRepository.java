package kr.kro.hereinkorea.domain.member.repository;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findByMemId(String memId);
    }

