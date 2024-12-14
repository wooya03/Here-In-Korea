package kr.kro.hereinkorea.domain.festival.controller;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
@Slf4j
public class FestivalController {
    private final FestivalService festivalService;

    @GetMapping
    public List<FestivalDTO> getFestivals() {
        return festivalService.getFestivalsContent();
    }
}

//    @PostMapping("/contentadd")
//    public String contentAdd(){
//
//        return "";
//    }
