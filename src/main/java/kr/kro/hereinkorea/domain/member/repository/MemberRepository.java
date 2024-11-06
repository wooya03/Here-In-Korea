package kr.kro.hereinkorea.domain.member.repository;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
