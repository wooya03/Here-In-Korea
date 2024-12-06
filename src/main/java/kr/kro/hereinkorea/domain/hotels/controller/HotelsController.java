package kr.kro.hereinkorea.domain.hotels.controller;

import kr.kro.hereinkorea.domain.hotels.service.HotelsApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelsController {

    private final HotelsApiService hotelsApiService;

    public HotelsController(HotelsApiService hotelsApiService){
        this.hotelsApiService = hotelsApiService;
    }

    @GetMapping("/api")
    public String fetch(){
        return hotelsApiService.fetch();
    }
}
