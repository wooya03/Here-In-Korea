package kr.kro.hereinkorea.domain.event.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.kro.hereinkorea.domain.event.dto.EventDTO;
import kr.kro.hereinkorea.domain.event.dto.EventImgDTO;
import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import kr.kro.hereinkorea.domain.event.entity.EventImgEntity;
import kr.kro.hereinkorea.domain.event.repository.EventImgRepository;
import kr.kro.hereinkorea.domain.event.repository.EventRepository;
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

@Service
@Slf4j
@RequiredArgsConstructor
public class EventApiService implements EventService {

    private final EventRepository eventRepository;
    private final EventImgRepository eventImgRepository;
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper

    private static final String AREA_BASED_LIST_URL = "http://apis.data.go.kr/B551011/KorService1/searchFestival1"
            + "?serviceKey=Had%2BznoLVPTQg7iygM9Hb%2Ba64OjDqjcztb2seubysp1ivP0dbmpaLeOYyaYhEmdCBu0skkp%2FiZEdP%2BqL%2BrAyew%3D%3D"
            + "&MobileOS=ETC&MobileApp=AppTest&numOfRows=30&eventStartDate=20241001";

    public void fetchAndSaveEvent() {
        try {
            String areaBasedListResponse = callApi(AREA_BASED_LIST_URL);
            log.info("areaBasedListResponse: {}", areaBasedListResponse);
            EventApiService.ApiResponse apiResponse = xmlMapper.readValue(areaBasedListResponse, EventApiService.ApiResponse.class);

            apiResponse.getBody().getItems().forEach(item -> {
                try {
                    Long contentId = item.getContentid();

                    EventDTO eventDTO = EventDTO.builder()
                            .contentid(contentId)
                            .title(item.getTitle())
                            .addr1(item.getAddr1())
                            .addr2(item.getAddr2())
                            .areacode(item.getAreacode())
                            .mapx(item.getMapx())
                            .mapy(item.getMapy())
                            .eventstartdate(item.getEventstartdate())
                            .eventenddate(item.getEventenddate())
                            .build();

                    EventEntity eventEntity = dtoToEntity(eventDTO);
                    eventRepository.save(eventEntity);

                    if(!item.getFirstimage().isEmpty()){
                        EventImgDTO eventImgDTO = EventImgDTO.builder()
                                .contentid(contentId)
                                .firstimage(item.getFirstimage())
                                .firstimage2(item.getFirstimage2())
                                .build();

                        EventImgEntity eventImgEntity = dtoToEntity(eventImgDTO);
                        eventImgRepository.save(eventImgEntity);
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
        private EventApiService.ApiResponse.Header header;
        @JsonProperty("body")
        private EventApiService.ApiResponse.Body body;

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
            private List<EventDTO> items;

            @JsonProperty("numOfRows")
            private int numOfRows;

            @JsonProperty("pageNo")
            private int pageNo;

            @JsonProperty("totalCount")
            private int totalCount;
        }
    }
}
