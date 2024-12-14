package kr.kro.hereinkorea.domain.admin.repository;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminMemberRepository extends JpaRepository<MemberEntity, String> {

    Page<MemberEntity> findByMemNameContaining(String memName, Pageable pageable);

    @Query(
            value = "SELECT m " +
                    "FROM MemberEntity m ",
            countQuery = "SELECT COUNT(m) " +
                    "FROM MemberEntity m ")
    Page<MemberEntity> getMemberCount(Pageable pageable);

    long count();
}
