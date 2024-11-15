package kr.kro.hereinkorea.domain.qna.question.repository;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query()
    Page<Object[]> getQuestionWithReplyCount(Pageable id);

}
