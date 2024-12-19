package kr.kro.hereinkorea.domain.festival.controller;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.dto.FestivalDetailsDTO;
import kr.kro.hereinkorea.domain.festival.repository.FestivalDetailsRepository;
import kr.kro.hereinkorea.domain.festival.service.FestivalDetailsService;
import kr.kro.hereinkorea.domain.festival.service.FestivalService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
@Slf4j
public class FestivalController {
    private final FestivalService festivalService;
    private final FestivalDetailsService festivalDetailsService;

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
    public ResponseEntity<FestivalDetailsDTO> getDetailsList(@PathVariable("id") Long contentId) {
        try {
            // 서비스에서 데이터를 가져옵니다.
            FestivalDetailsDTO festivalDetailsDTO = festivalDetailsService.getFestivalDetails(contentId);
            return ResponseEntity.ok(festivalDetailsDTO);
        } catch (RuntimeException e) {
            // RuntimeException이 발생한 경우 404 Not Found 응답을 반환합니다.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // 또는 적절한 메시지를 반환할 수 있습니다.
        } catch (Exception e) {
            // 기타 예외가 발생한 경우 500 Internal Server Error 응답을 반환합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // 또는 적절한 메시지를 반환할 수 있습니다.
        }
    }

}