package kr.kro.hereinkorea.domain.hotels.controller;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.service.HotelsApiService;
import kr.kro.hereinkorea.domain.hotels.service.HotelsServiceImpl;
import kr.kro.hereinkorea.domain.hotels.service.RoomApiService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@Slf4j
@RequiredArgsConstructor
public class HotelsController {

    private final HotelsApiService hotelsApiService;
    private final HotelsServiceImpl hotelsService;
    private final RoomApiService roomApiService;

    @GetMapping("/fetch")
    public String fetchHotels() {
        try {
            hotelsApiService.fetchAndSaveHotels();
            return "숙박정보 저장 완료!";
        } catch (Exception e) {
            log.error("숙박정보 저장 실패: {}", e.getMessage());
            return "숙박정보 저장 실패!";
        }
    }

    @GetMapping("/fetch2")
    public String fetchRoom() {
        try {
            roomApiService.fetchAndSaveRooms();
            return "객실정보 저장 완료!";
        } catch (Exception e) {
            log.error("객실정보 저장 실패: {}", e.getMessage());
            return "객실정보 저장 실패!";
        }
    }

    @GetMapping("/list")
    public PageResultDTO<HotelsDTO, Object[]> list(PageRequestDTO requestDTO){
        return hotelsService.getList(requestDTO);
    }
}