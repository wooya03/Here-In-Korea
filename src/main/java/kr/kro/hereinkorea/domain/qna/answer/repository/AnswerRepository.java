package kr.kro.hereinkorea.domain.qna.answer.repository;

import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

   Optional<AnswerEntity> findByQuestionId(long id);

}
