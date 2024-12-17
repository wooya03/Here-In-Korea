package kr.kro.hereinkorea.domain.festival.controller;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.repository.FestivalImgRepository;
import kr.kro.hereinkorea.domain.festival.service.FestivalService;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
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

//    @GetMapping
//    public List<FestivalDTO> getFestivals() {
//        return festivalService.getFestivalsContent();
//    }
//}

    @GetMapping("/contentadd")
    public String contentAdd() {
        try {
            festivalService.addContentFestival();
            return "정보 저장 완료!";
        } catch (Exception e) {
            log.error("정보 저장 실패: {}", e.getMessage());
            return "정보 저장 실패!";
        }
    }

    @GetMapping("/list")
    public PageResultDTO<FestivalDTO, Object[]> list(PageRequestDTO requestDTO){
        return festivalService.getList(requestDTO);
    }
}