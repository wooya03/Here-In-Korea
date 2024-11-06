package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.user.UserEntity;

public interface QuestionService {

    QuestionDTO get(Long id);

    default QuestionDTO entityToDTO(QuestionEntity entity, UserEntity user, Long replyCount){
        return QuestionDTO.builder().build();
    }

    default QuestionEntity dtoToEntity(QuestionDTO dto){
        return null;
    }
}
