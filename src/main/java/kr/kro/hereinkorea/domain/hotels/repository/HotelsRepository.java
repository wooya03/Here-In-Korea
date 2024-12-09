package kr.kro.hereinkorea.domain.hotels.repository;

import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<AnswerEntity, Long> {
}
