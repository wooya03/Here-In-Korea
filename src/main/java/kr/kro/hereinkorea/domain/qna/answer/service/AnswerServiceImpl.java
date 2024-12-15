package kr.kro.hereinkorea.domain.qna.answer.service;

import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Override
    public void write(AnswerDTO dto) {
        if (dto.getContents() == null || dto.getContents().trim().isEmpty()) {
            throw new IllegalArgumentException("답변 내용을 입력해주세요.");
        }
        if (dto.getQuestionId() == null) {
            throw new IllegalArgumentException("질문 ID가 필요합니다.");
        }

        // AnswerEntity 생성 및 저장
        AnswerEntity answerEntity = dtoToEntity(dto);

        answerRepository.save(answerEntity);
        logger.info("Answer saved successfully.");
    }
}
