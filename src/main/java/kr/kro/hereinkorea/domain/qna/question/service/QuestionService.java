package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;

public interface QuestionService {

    default QuestionDTO entityToDTO(QuestionEntity entity, MemberEntity user, Long replyCount){
        return QuestionDTO.builder()
                .qId(entity.getQId())
                .qTitle(entity.getQTitle())
                .qCategory(entity.getQCategory())
                .qContents(entity.getQContents())
                .qStatus(entity.getQStatus())
                .memId(user.getMemId())
                .memName(user.getMemName())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .build();
    }

    default QuestionEntity dtoToEntity(QuestionDTO dto){
        MemberEntity memberEntity = MemberEntity.builder().memId(dto.getMemId()).build();

        return QuestionEntity.builder()
                .qId(dto.getQId())
                .qTitle(dto.getQTitle())
                .qCategory(dto.getQCategory())
                .qContents(dto.getQContents())
                .qStatus(dto.getQStatus())
                .member(memberEntity)
                .build();
    }
}
