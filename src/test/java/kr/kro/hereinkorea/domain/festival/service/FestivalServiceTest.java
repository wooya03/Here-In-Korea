package kr.kro.hereinkorea.domain.festival.service;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FestivalServiceTest {
    @Autowired
    FestivalService festivalService;

    @Test
    void FestivalServiceIntegrationTest() {
        // 실제 API 호출
        List<FestivalDTO> festivals = festivalService.getFestivalsContent();

        // 결과 검증
        assertNotNull(festivals, "Festival list should not be null");
        assertFalse(festivals.isEmpty(), "Festival list should not be empty");

        // 첫 번째 항목 검증 (예시)
        FestivalDTO firstFestival = festivals.get(0);
        assertNotNull(firstFestival.getContentId(), "Content ID should not be null");
        assertNotNull(firstFestival.getTitle(), "Title should not be null");
        assertNotNull(firstFestival.getEventStartDate(), "Event Start Date should not be null");
        assertNotNull(firstFestival.getEventEndDate(), "Event End Date should not be null");

        // 출력
        festivals.forEach(festival -> {
            System.out.println("Festival: " + festival.getTitle() + ", Start Date: " + festival.getEventStartDate());
        });
    }
}