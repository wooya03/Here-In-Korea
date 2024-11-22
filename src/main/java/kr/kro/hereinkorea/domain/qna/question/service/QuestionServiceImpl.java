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

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public void write(QuestionDTO dto) {
        if (dto.getQContents() == null || dto.getQContents().trim().isEmpty()) {
            throw new IllegalArgumentException("문의 내용을 입력해주세요.");
        }
        if (dto.getQCategory() == null || dto.getQCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리 내용을 입력해주세요.");
        }

        if (dto.getQTitle() == null || dto.getQTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        }
        
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
