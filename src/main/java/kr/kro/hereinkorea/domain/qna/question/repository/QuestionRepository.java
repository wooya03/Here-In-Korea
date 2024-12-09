package kr.kro.hereinkorea.domain.qna.question.repository;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(
            value = "SELECT q, m " +
                    "FROM QuestionEntity q " +
                    "LEFT JOIN q.member m " +
                    "GROUP BY q ",
            countQuery = "SELECT COUNT(q) " +
                    "FROM QuestionEntity q ")
    Page<Object[]> getQuestionCount(Pageable pageable);


    @Query("SELECT q, m " +
            "FROM QuestionEntity q " +
            "LEFT JOIN q.member m " +
            "WHERE q.id = :id " +
            "GROUP BY q ")
    Object getQuestionById(@Param("id") Long id);

}
