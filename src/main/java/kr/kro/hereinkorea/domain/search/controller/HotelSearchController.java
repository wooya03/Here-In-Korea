package kr.kro.hereinkorea.domain.search.controller;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.search.service.HotelSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelSearchController {
    private final HotelSearchService hotelService;

    // 검색 API 엔드포인트: /api/hotels/search?title=대전
    @GetMapping("/search")
    public List<HotelsEntity> searchHotels(@RequestParam("title") String title) {
        return hotelService.searchHotelsByTitle(title);
    }
}
