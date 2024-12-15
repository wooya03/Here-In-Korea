package kr.kro.hereinkorea.domain.qna.question.repository;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(
            value = "SELECT q, m, a " +
                    "FROM QuestionEntity q " +
                    "LEFT JOIN q.member m " +
                    "LEFT JOIN AnswerEntity a ON a.question = q",
            countQuery = "SELECT COUNT(q) " +
                    "FROM QuestionEntity q")
    Page<Object[]> getQuestionCount(Pageable pageable);

    @Query(
            value = "SELECT q, m, a " +
                    "FROM QuestionEntity q " +
                    "LEFT JOIN q.member m " +
                    "LEFT JOIN AnswerEntity a ON a.question = q " +
                    "WHERE (q.category = :category)",
            countQuery = "SELECT COUNT(q) " +
                    "FROM QuestionEntity q " +
                    "WHERE (q.category = :category)")
    Page<Object[]> getQuestionByCategory(
            @Param("category") String category,
            Pageable pageable);

    @Query("SELECT q, m, a " +
            "FROM QuestionEntity q " +
            "LEFT JOIN q.member m " +
            "LEFT JOIN AnswerEntity a ON a.question = q " +
            "WHERE q.id = :id ")
    Object getQuestionById(@Param("id") Long id);

    @Query(
            value = "SELECT q, m, a " +
                    "FROM QuestionEntity q " +
                    "LEFT JOIN q.member m " +
                    "LEFT JOIN AnswerEntity a ON a.question = q " +
                    "WHERE q.title LIKE CONCAT('%', :title, '%')",
            countQuery = "SELECT COUNT(q) " +
                    "FROM QuestionEntity q " +
                    "WHERE q.title LIKE CONCAT('%', :title, '%')")
    Page<Object[]> getQuestionByTitle(@Param("title") String title, Pageable pageable);


    @Query(
            value = "SELECT q, m, a " +
                    "FROM QuestionEntity q " +
                    "LEFT JOIN q.member m " +
                    "LEFT JOIN AnswerEntity a ON a.question = q " +
                    "WHERE q.title LIKE CONCAT('%', :title, '%') AND " +
                    "(q.category = :category) ",
            countQuery = "SELECT COUNT(q) " +
                    "FROM QuestionEntity q " +
                    "WHERE q.title LIKE CONCAT('%', :title, '%') AND " +
                    "(q.category = :category) ")
    Page<Object[]> getQuestionByTitleAndCategory(@Param("title") String title, @Param("category") String category, Pageable pageable);
}
