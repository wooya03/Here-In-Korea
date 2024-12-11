package kr.kro.hereinkorea.domain.event.controller;

import kr.kro.hereinkorea.domain.event.dto.EventDTO;
import kr.kro.hereinkorea.domain.event.service.EventApiService;
import kr.kro.hereinkorea.domain.event.service.EventServiceImpl;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Slf4j
@RequiredArgsConstructor
public class EventController {
    private final EventApiService eventApiService;
    private final EventServiceImpl eventService;

    @GetMapping("/fetch")
    public String fetchEvent() {
        try {
            eventApiService.fetchAndSaveEvent();
            return "행사정보 저장 완료!";
        } catch (Exception e) {
            log.error("행사정보 저장 실패: {}", e.getMessage());
            return "행사정보 저장 실패!";
        }
    }

    @GetMapping("/list")
    public PageResultDTO<EventDTO, Object[]> list(PageRequestDTO requestDTO){
        return eventService.getList(requestDTO);
    }
}
