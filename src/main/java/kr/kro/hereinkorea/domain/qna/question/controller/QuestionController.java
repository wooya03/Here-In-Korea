package kr.kro.hereinkorea.domain.qna.question.controller;

import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.qna.question.service.QuestionService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public PageResultDTO<QuestionDTO, Object[]> list(PageRequestDTO requestDTO){
        return questionService.getList(requestDTO);
    }

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.CREATED)
    public String addQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            questionService.write(questionDTO);
            return "질문이 등록되었습니다.";
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 에러 출력
            return "문제 발생: " + e.getMessage();
        }
    }

}
