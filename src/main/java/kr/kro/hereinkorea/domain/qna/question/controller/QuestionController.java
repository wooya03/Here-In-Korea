package kr.kro.hereinkorea.domain.qna.question.controller;

import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QuestionController {

    @GetMapping("/list")
    public PageResultDTO<QuestionDTO, Object[]> list(PageRequestDTO requestDTO){
        return null;
    }
}
