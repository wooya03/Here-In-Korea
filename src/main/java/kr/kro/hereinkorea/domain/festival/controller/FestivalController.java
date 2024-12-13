package kr.kro.hereinkorea.domain.festival.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
@Slf4j
public class FestivalController {
    @PostMapping("/contentadd")
    public String contentAdd(){

        return "";
    }
}
