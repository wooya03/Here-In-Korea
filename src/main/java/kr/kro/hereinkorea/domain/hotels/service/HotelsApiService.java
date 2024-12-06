package kr.kro.hereinkorea.domain.hotels.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional(rollbackFor = Exception.class)
public class HotelsApiService {

    private String apiKey = "${Had%2BznoLVPTQg7iygM9Hb%2Ba64OjDqjcztb2seubysp1ivP0dbmpaLeOYyaYhEmdCBu0skkp%2FiZEdP%2BqL%2BrAyew%3D%3D}";

    private final RestTemplate restTemplate;

    public HotelsApiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String fetch() {
        String url = UriComponentsBuilder.fromHttpUrl("https://apis.data.go.kr/B551011/KorService1/areaBasedList1")
                .queryParam("serviceKey", apiKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "Test")
                .queryParam("_type", "json")
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 20)
                .queryParam("contentTypeId", 32)
                .build().toString();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
