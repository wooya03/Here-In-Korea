package kr.kro.hereinkorea.domain.hotels.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsImgDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.hotels.repository.HotelsImgRepository;
import kr.kro.hereinkorea.domain.hotels.repository.HotelsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelsApiService implements HotelsService {

    private final HotelsRepository hotelsRepository;
    private final HotelsImgRepository hotelsImgRepository;
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper

    private static final String AREA_BASED_LIST_URL = "http://apis.data.go.kr/B551011/KorService1/searchStay1"
            + "?serviceKey=Had%2BznoLVPTQg7iygM9Hb%2Ba64OjDqjcztb2seubysp1ivP0dbmpaLeOYyaYhEmdCBu0skkp%2FiZEdP%2BqL%2BrAyew%3D%3D"
            + "&MobileOS=ETC&MobileApp=AppTest&numOfRows=30";

    public void
    fetchAndSaveHotels() {
        try {
            String areaBasedListResponse = callApi(AREA_BASED_LIST_URL);
            log.info("areaBasedListResponse: {}", areaBasedListResponse);
            ApiResponse apiResponse = xmlMapper.readValue(areaBasedListResponse, ApiResponse.class);

            apiResponse.getBody().getItems().forEach(item -> {
                try {
                    Long contentId = item.getContentid();

                    HotelsDTO hotelsDTO = HotelsDTO.builder()
                            .contentid(contentId)
                            .title(item.getTitle())
                            .addr1(item.getAddr1())
                            .addr2(item.getAddr2())
                            .areacode(item.getAreacode())
                            .mapx(item.getMapx())
                            .mapy(item.getMapy())
                            .tel(item.getTel())
                            .build();
                    HotelsEntity hotelEntity = dtoToEntity(hotelsDTO);
                    hotelsRepository.save(hotelEntity);

                    if(!item.getFirstimage().isEmpty()){
                        HotelsImgDTO hotelsImgDTO = HotelsImgDTO.builder()
                                .contentid(contentId)
                                .firstimage(item.getFirstimage())
                                .firstimage2(item.getFirstimage2())
                                .build();
                        HotelsImgEntity hotelsImgEntity = dtoToEntity(hotelsImgDTO);
                        hotelsImgRepository.save(hotelsImgEntity);
                    }
                } catch (Exception e) {
                    log.error("Error processing contentId {}: {}", item.getContentid(), e.getMessage());
                }
            });

        } catch (Exception e) {
            log.error("API 호출 및 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("API 처리 실패", e);
        }
    }



    private String callApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        log.info("HTTP 응답 코드: {}", responseCode);

        if (responseCode != 200) {
            throw new RuntimeException("API 호출 실패, 응답 코드: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            log.info("API 응답: {}", result);
            return result.toString();
        } finally {
            connection.disconnect();
        }
    }

    // ApiResponse 클래스를 정의하여 XML 응답을 매핑
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiResponse {
        private Header header;
        @JsonProperty("body")
        private Body body;

        public static class Header {
            @JsonProperty("resultCode")
            private String resultCode;

            @JsonProperty("resultMsg")
            private String resultMsg;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Body {
            @JsonProperty("items")
            private List<HotelsDTO> items;

            @JsonProperty("numOfRows")
            private int numOfRows;

            @JsonProperty("pageNo")
            private int pageNo;

            @JsonProperty("totalCount")
            private int totalCount;
        }
    }
}
