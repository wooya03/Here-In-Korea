package kr.kro.hereinkorea.domain.qna.answer.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;

public interface AnswerService {

    void write(AnswerDTO dto);

//    AnswerDTO get(Long id);
    default AnswerDTO entityToDTO(AnswerEntity entity, MemberEntity user, QuestionEntity question){
        return AnswerDTO.builder()
                .id(entity.getId())
                .contents(entity.getContents())
                .memId(user.getMemId())
                .questionId(question.getId())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }

    default AnswerEntity dtoToEntity(AnswerDTO dto){
        QuestionEntity questionEntity = QuestionEntity.builder().id(dto.getQuestionId()).build();
        MemberEntity memberEntity = MemberEntity.builder().memId(dto.getMemId()).build();

        return AnswerEntity.builder()
                .id(dto.getId())
                .contents(dto.getContents())
                .member(memberEntity)
                .question(questionEntity)
                .build();
    }
}
