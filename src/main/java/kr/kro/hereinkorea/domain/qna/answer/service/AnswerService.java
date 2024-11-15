package kr.kro.hereinkorea.domain.qna.answer.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;

public interface AnswerService {

    default AnswerDTO entityToDTO(AnswerEntity entity, MemberEntity user, QuestionEntity question, Long replyCount){
        return AnswerDTO.builder()
                .aId(entity.getAId())
                .aContents(entity.getAContents())
                .memId(user.getMemId())
                .qId(question.getQId())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }

    default AnswerEntity dtoToEntity(AnswerDTO dto){
        MemberEntity memberEntity = MemberEntity.builder().memId(dto.getMemId()).build();
        QuestionEntity questionEntity = QuestionEntity.builder().qId(dto.getQId()).build();

        return AnswerEntity.builder()
                .aId(dto.getAId())
                .aContents(dto.getAContents())
                .member(memberEntity)
                .question(questionEntity)
                .build();
    }
}
