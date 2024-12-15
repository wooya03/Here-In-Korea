package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;

public interface QuestionService {

    void write(QuestionDTO dto);

    PageResultDTO<QuestionDTO, Object[]> getList(String category,PageRequestDTO pageRequestDTO);

    default QuestionDTO entityToDTO(QuestionEntity entity, MemberEntity user, AnswerEntity answer) {
        return QuestionDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .category(entity.getCategory())
                .contents(entity.getContents())
                .memId(user.getMemId())
                .memName(user.getMemName())
                .createdDate(entity.getCreatedDate())
                .modifiedDate(entity.getModifiedDate())
                .answerContents(answer != null ? answer.getContents() : null) // null 체크 추가
                .build();
    }

    default QuestionEntity dtoToEntity(QuestionDTO dto){
        MemberEntity memberEntity = MemberEntity.builder().memId(dto.getMemId()).build();

        return QuestionEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .contents(dto.getContents())
                .member(memberEntity)
                .build();
    }

    void delete(Long id);

    QuestionDTO get(Long id);
}
