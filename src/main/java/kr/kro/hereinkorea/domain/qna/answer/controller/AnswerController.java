package kr.kro.hereinkorea.domain.qna.answer.controller;

import kr.kro.hereinkorea.domain.qna.answer.dto.AnswerDTO;
import kr.kro.hereinkorea.domain.qna.answer.service.AnswerService;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAnswer(@RequestBody AnswerDTO answerDTO) {
        try {
            answerService.write(answerDTO);
            return "답변이 등록되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 에러 출력
            return "문제 발생: " + e.getMessage();
        }
    }

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity findAnswerById(@PathVariable("id") Long id){
//        AnswerDTO answerDTO = answerService.get(id);
//        return ResponseEntity.ok(answerDTO);
//    }
}
