package kr.kro.hereinkorea.domain.hotels.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.kro.hereinkorea.domain.hotels.dto.RoomDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.RoomEntity;
import kr.kro.hereinkorea.domain.hotels.repository.HotelsRepository;
import kr.kro.hereinkorea.domain.hotels.repository.RoomRepository;
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
public class RoomApiService implements HotelsService {
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper
    private final RoomRepository roomRepository;
    private final HotelsRepository hotelsRepository; // HotelsRepository 추가

    private static final String BASE_URL = "http://apis.data.go.kr/B551011/KorService1/detailInfo1"
            + "?serviceKey=Had%2BznoLVPTQg7iygM9Hb%2Ba64OjDqjcztb2seubysp1ivP0dbmpaLeOYyaYhEmdCBu0skkp%2FiZEdP%2BqL%2BrAyew%3D%3D"
            + "&MobileOS=ETC&MobileApp=AppTest&numOfRows=30&contentTypeId=32";

    // 모든 호텔의 contentId를 사용하여 Room 데이터를 fetch
    public void fetchAndSaveRooms() {
        try {
            // HotelsEntity에서 모든 contentId를 가져옴
            List<HotelsEntity> hotels = hotelsRepository.findAll();

            for (HotelsEntity hotel : hotels) {
                Long contentId = hotel.getContentid();  // HotelsEntity의 contentId 사용
                String areaBasedListUrl = BASE_URL + "&contentId=" + contentId;

                // API 호출 및 데이터 저장
                String areaBasedListResponse = callApi(areaBasedListUrl);
                log.info("areaBasedListResponse for contentId {}: {}", contentId, areaBasedListResponse);
                ApiResponse apiResponse = xmlMapper.readValue(areaBasedListResponse, ApiResponse.class);

                apiResponse.getBody().getItems().forEach(item -> {
                    try {
                        RoomDTO roomDTO = RoomDTO.builder()
                                .contentid(item.getContentid())
                                .roomtitle(item.getRoomtitle())
                                .roomsize1(item.getRoomsize1())
                                .roomcount(item.getRoomcount())
                                .roombasecount(item.getRoombasecount())
                                .roommaxcount(item.getRoommaxcount())
                                .roomoffseasonminfee1(item.getRoomoffseasonminfee1())
                                .roomoffseasonminfee2(item.getRoomoffseasonminfee2())
                                .roomintro(item.getRoomintro())
                                .roombathfacility(item.getRoombathfacility() != "" ? item.getRoombathfacility() : "N")
                                .roombath(item.getRoombath() != "" ? item.getRoombath() : "N")
                                .roomhometheater(item.getRoomhometheater() != "" ? item.getRoomhometheater() : "N")
                                .roomaircondition(item.getRoomaircondition() != "" ? item.getRoomaircondition() : "N")
                                .roomtv(item.getRoomtv() != "" ? item.getRoomtv() : "N")
                                .roompc(item.getRoompc() != "" ? item.getRoompc() : "N")
                                .roomcable(item.getRoomcable() != "" ? item.getRoomcable() : "N")
                                .roominternet(item.getRoominternet() != "" ? item.getRoominternet() : "N")
                                .roomrefrigerator(item.getRoomrefrigerator() != "" ? item.getRoomrefrigerator() : "N")
                                .roomtoiletries(item.getRoomtoiletries() != "" ? item.getRoomtoiletries() : "N")
                                .roomsofa(item.getRoomsofa() != "" ? item.getRoomsofa() : "N")
                                .roomcook(item.getRoomcook() != "" ? item.getRoomcook() : "N")
                                .roomtable(item.getRoomtable() != "" ? item.getRoomtable() : "N")
                                .roomhairdryer(item.getRoomhairdryer() != "" ? item.getRoomhairdryer() : "N")
                                .roomimg1(item.getRoomimg1())
                                .roomimg2(item.getRoomimg2())
                                .roomimg3(item.getRoomimg3())
                                .roomimg4(item.getRoomimg4())
                                .roomimg5(item.getRoomimg5())
                                .build();

                        RoomEntity roomEntity = dtoToEntity(roomDTO);
                        roomRepository.save(roomEntity);
                    } catch (Exception e) {
                        log.error("Error processing contentId {}: {}", item.getContentid(), e.getMessage());
                    }
                });
            }

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
    public static class ApiResponse {
        private RoomApiService.ApiResponse.Header header;

        @JsonProperty("body")
        private RoomApiService.ApiResponse.Body body;


        @JsonIgnoreProperties(ignoreUnknown = true)
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
            private List<RoomDTO> items;

            @JsonProperty("numOfRows")
            private int numOfRows;

            @JsonProperty("pageNo")
            private int pageNo;

            @JsonProperty("totalCount")
            private int totalCount;
        }
    }
}
