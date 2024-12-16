package kr.kro.hereinkorea.domain.search.controller;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.search.service.HotelSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React 서버 주소
public class HotelSearchController {
    private final HotelSearchServiceImpl hotelService;


    // 검색 API 엔드포인트(예시): /api/hotels/search?title=대전
    @GetMapping("/search")
    public List<HotelsDTO> searchHotels(@RequestParam("title") String title) {
        return hotelService.searchHotelsByTitle(title);
    }

    // 검색 API 엔드포인트(예시): /api/hotels/search?areacode=경북
    @GetMapping("/search2")
    public  List<HotelsDTO> searchAddr1(@RequestParam("addr1") String addr1){
        return hotelService.searchHotelsByAddr1(addr1);
    }
}
