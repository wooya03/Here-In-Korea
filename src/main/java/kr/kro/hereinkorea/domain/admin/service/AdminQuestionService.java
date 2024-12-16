package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.qna.question.repository.QuestionRepository;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminQuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public PageResultDTO<QuestionDTO, Object[]> getQuestion(String title, String category, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result;

        if(category == null || category.trim().isEmpty()){
            if(title == null || title.trim().isEmpty() ){
                result = questionRepository.getQuestionCount(pageable);
            } else {
                result = questionRepository.getQuestionByTitle(title, pageable);
            }
        } else {
            if(title == null || title.trim().isEmpty()){
                result = questionRepository.getQuestionByCategory(category,pageable);
            } else {
                result = questionRepository.getQuestionByTitleAndCategory(title, category, pageable);
            }
        }

        return new PageResultDTO<QuestionDTO, Object[]>(result,
                en -> entityToDTO((QuestionEntity) en[0], (MemberEntity) en[1], (AnswerEntity) en[2])
        );
    }

    private QuestionDTO entityToDTO(QuestionEntity entity, MemberEntity user, AnswerEntity answer) {
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
}
