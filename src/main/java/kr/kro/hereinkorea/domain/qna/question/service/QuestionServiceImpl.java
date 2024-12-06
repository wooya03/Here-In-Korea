package kr.kro.hereinkorea.domain.qna.question.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.answer.repository.AnswerRepository;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    @Override
    public void write(@RequestBody QuestionDTO dto) {
        if (dto.getContents() == null || dto.getContents().trim().isEmpty()) {
            throw new IllegalArgumentException("문의 내용을 입력해주세요.");
        }
        if (dto.getCategory() == null || dto.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("카테고리 내용을 입력해주세요.");
        }

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
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

    @Override
    public void delete(Long id) {
        Optional<AnswerEntity> optionalAnswer = answerRepository.findByQuestionId(id);

        optionalAnswer.ifPresent(answerRepository::delete);

        // 질문 삭제
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDTO get(Long id) {
        Object result = questionRepository.getQuestionById(id);
        Object[] arr = (Object[]) result;
        return entityToDTO((QuestionEntity) arr[0], (MemberEntity) arr[1]);
    }


}
