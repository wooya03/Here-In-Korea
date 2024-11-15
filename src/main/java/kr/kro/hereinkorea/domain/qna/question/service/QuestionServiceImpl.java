package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.qna.question.repository.QuestionRepository;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public void register(QuestionDTO dto) {
        QuestionEntity questionEntity = dtoToEntity(dto);
        questionRepository.save(questionEntity);
    }

    @Override
    public PageResultDTO<QuestionDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Page<Object[]> result = questionRepository.getQuestionCount(
                pageRequestDTO.getPageable(Sort.by("id").descending())
        );
        return new PageResultDTO<QuestionDTO, Object[]>(result,
                en -> entityToDTO((QuestionEntity) en[0], (MemberEntity) en[1])

        );
    }
}
