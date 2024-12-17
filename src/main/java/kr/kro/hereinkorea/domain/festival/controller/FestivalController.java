package kr.kro.hereinkorea.domain.festival.controller;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.repository.FestivalDetailsRepository;
import kr.kro.hereinkorea.domain.festival.repository.FestivalImgRepository;
import kr.kro.hereinkorea.domain.festival.service.FestivalDetailsService;
import kr.kro.hereinkorea.domain.festival.service.FestivalService;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final FestivalDetailsService festivalDetailsService;

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

    @GetMapping("/contentdetailsadd")
    public String contentDetailsAdd() {
        try {
            festivalDetailsService.addFestivalDetails();
            return "정보 저장 완료!";
        } catch (Exception e) {
            // 예외 메시지와 스택 트레이스를 로그에 출력
            log.error("정보 저장 실패: {}", e.getMessage(), e); // 전체 예외 정보 출력
            return "정보 저장 실패! " + e.getMessage(); // 클라이언트에게 더 구체적인 오류 메시지 제공
        }
    }

    @GetMapping("/list")
    public PageResultDTO<FestivalDTO, Object[]> list(PageRequestDTO requestDTO){
        return festivalService.getList(requestDTO);
    }

    @GetMapping("/{id}")
    public void getDetailsList(){}
}