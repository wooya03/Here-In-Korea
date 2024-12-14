package kr.kro.hereinkorea.domain.admin.repository;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMemberRepository extends JpaRepository<MemberEntity, String> {

    Page<MemberEntity> findByMemNameContaining(String memName, Pageable pageable);
    long count();
}
