package kr.kro.hereinkorea.domain.qna.answer.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.entity.AnswerEntity;
import kr.kro.hereinkorea.domain.qna.answer.repository.AnswerRepository;
import kr.kro.hereinkorea.domain.qna.question.entity.QuestionEntity;
import kr.kro.hereinkorea.domain.qna.question.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService {

//    private final AnswerRepository answerRepository;
//    private final QuestionRepository questionRepository;
//    private  final MemberRepository memberRepository;
//
//    @Override
//    public void write(@RequestBody AnswerDTO dto) {
//        // 1. 답변 내용 및 질문 ID 유효성 검사
//        if (dto.getContents() == null || dto.getContents().trim().isEmpty()) {
//            throw new IllegalArgumentException("답변 내용을 입력해주세요.");
//        }
//        if (dto.getQuestionId() == null) {
//            throw new IllegalArgumentException("질문 ID가 필요합니다.");
//        }
//
//        // 2. QuestionEntity 조회
//        QuestionEntity questionEntity = questionRepository.findById(dto.getQuestionId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 ID에 해당하는 질문이 존재하지 않습니다."));
//
//        // 3. QuestionEntity 상태 변경
//        questionEntity.okStatus();
//
//        // 4. MemberEntity 검증 및 저장
//        if (questionEntity.getMember() == null || questionEntity.getMember().getMemId() == null) {
//            MemberEntity memberEntity = memberRepository.save(questionEntity.getMember());
//            questionEntity.setMember(memberEntity);
//        }
//
//        // 5. AnswerEntity 생성 및 질문 연관 설정
//        AnswerEntity answerEntity = dtoToEntity(dto);
//        answerEntity.changeQuestion(questionEntity);
//
//        // 6. 변경된 QuestionEntity 저장
//        questionRepository.save(questionEntity);
//
//        // 7. AnswerEntity 저장
//        answerRepository.save(answerEntity);
}


