package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.answer.repository.AnswerRepository;
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

import java.util.List;

@Service
public class AdminQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

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
                .answerContents(answer != null ? answer.getContents() : null)
                .build();
    }

    public void deleteQuestion(List<Long> questionIds) {
        try {
            for(Long id : questionIds){
                if(!answerRepository.findByQuestionId(id).isEmpty()){
                    Long aid = AnswerDTO.builder().questionId(id).build().getId();
                    answerRepository.deleteById(aid);
                }
                questionRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("문의 삭제 중 오류 발생", e);
        }
    }
}
