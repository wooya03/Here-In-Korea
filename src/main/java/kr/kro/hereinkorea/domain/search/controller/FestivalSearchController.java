package kr.kro.hereinkorea.domain.search.controller;


import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.search.service.FestivalSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivals")
@RequiredArgsConstructor
public class FestivalSearchController {
    private final FestivalSearchServiceImpl festivalService;


    @GetMapping("/search3")
    public List<FestivalDTO> searchFestival(@RequestParam("title")String title){
        return festivalService.searchFestivalsByTitle(title);
    }

    @GetMapping("/search4")
    public List<FestivalDTO> searchAddr1(@RequestParam("addr1")String addr1){
        return festivalService.searchFestivalsByAddr1(addr1);
    }
}
