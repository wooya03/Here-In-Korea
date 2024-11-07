package kr.kro.hereinkorea.domain.qna.question.repository;

import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
