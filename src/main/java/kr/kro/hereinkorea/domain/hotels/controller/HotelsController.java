package kr.kro.hereinkorea.domain.hotels.controller;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.service.HotelsApiService;
import kr.kro.hereinkorea.domain.hotels.service.HotelsServiceImpl;
import kr.kro.hereinkorea.domain.hotels.service.RoomApiService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PageResultDTO<HotelsDTO, Object[]> list(@RequestParam("order") String order, @RequestParam("areaCode") int areaCode, PageRequestDTO requestDTO){
        return hotelsService.getList(order, areaCode, requestDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findHotelsById(@PathVariable("id") Long id){
        HotelsDTO hotelsDTO = hotelsService.get(id);
        return ResponseEntity.ok(hotelsDTO);
    }
}