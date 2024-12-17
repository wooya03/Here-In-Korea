package kr.kro.hereinkorea.domain.festival.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.transaction.Transactional;
import kr.kro.hereinkorea.domain.festival.entity.FestivalDetailsEntity;
import kr.kro.hereinkorea.domain.festival.repository.FestivalDetailsRepository;
import kr.kro.hereinkorea.domain.festival.repository.FestivalRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FestivalDetailsService {

    private final FestivalRepository festivalRepository;
    private final FestivalDetailsRepository festivalDetailsRepository;
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper

    private static final String FESTIVAL_DETAIL_URL = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?" +
            "ServiceKey=인증키&contentTypeId=15&contentId=%d&MobileOS=ETC&MobileApp=AppTest";

    // FestivalEntity의 contentId를 기반으로 FestivalDetailsEntity를 저장
    @Transactional
    public void addFestivalDetails() {
        // FestivalEntity 목록을 가져옵니다.
        festivalRepository.findAll().forEach(festivalEntity -> {
            try {
                // API 호출 URL 생성
                String apiUrl = String.format(FESTIVAL_DETAIL_URL, festivalEntity.getContentId());

                // API 호출
                String apiResponse = callApi(apiUrl);
                log.info("API 응답: {}", apiResponse);

                // XML 데이터를 ApiResponse 객체로 변환
                ApiResponse apiResponseObj = xmlMapper.readValue(apiResponse, ApiResponse.class);

                // API 응답 데이터 처리
                apiResponseObj.getBody().getItems().forEach(item -> {
                    try {
                        // FestivalDetailsEntity 생성
                        FestivalDetailsEntity festivalDetailsEntity = FestivalDetailsEntity.builder()
                                .festivalEntity(festivalEntity) // FestivalEntity와 연결
                                .sponsor1(item.getSponsor1()) // 주최자 정보
                                .sponsor1tel(item.getSponsor1tel()) // 주최자 연락처
                                .eventstartdate(item.getEventstartdate()) // 이벤트 시작일
                                .eventenddate(item.getEventenddate()) // 이벤트 종료일
                                .playtime(item.getPlaytime()) // 공연 시간
                                .eventplace(item.getEventplace()) // 행사 장소
                                .usetimefestival(item.getUsetimefestival()) // 이용 요금
                                .build();

                        // FestivalDetailsEntity 저장
                        festivalDetailsRepository.save(festivalDetailsEntity);
                    } catch (Exception e) {
                        log.error("Error processing FestivalDetails for contentId {}: {}", festivalEntity.getContentId(), e.getMessage());
                    }
                });

            } catch (Exception e) {
                log.error("Error processing FestivalDetails for contentId {}: {}", festivalEntity.getContentId(), e.getMessage());
            }
        });
    }

    // API 호출 메서드
    private String callApi(String apiUrl) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(apiUrl, String.class);

        if (response == null || response.isEmpty()) {
            throw new RuntimeException("API 응답이 비어 있습니다.");
        }

        return response;
    }

    // API 응답을 매핑하기 위한 클래스
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiResponse {
        private Header header;
        @JsonProperty("body")
        private Body body;

        @Getter
        @Setter
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
            private List<FestivalItem> items;

            @JsonProperty("numOfRows")
            private int numOfRows;

            @JsonProperty("pageNo")
            private int pageNo;

            @JsonProperty("totalCount")
            private int totalCount;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class FestivalItem {
            @JsonProperty("contentid")
            private Long contentId;

            @JsonProperty("contenttypeid")
            private int contentTypeId;

            @JsonProperty("title")
            private String title;

            @JsonProperty("addr1")
            private String addr1;

            @JsonProperty("addr2")
            private String addr2;

            @JsonProperty("areacode")
            private int areacode;

            @JsonProperty("mapx")
            private Double mapx;

            @JsonProperty("mapy")
            private Double mapy;

            @JsonProperty("tel")
            private String tel;

            @JsonProperty("eventstartdate")
            private String eventstartdate;

            @JsonProperty("eventenddate")
            private String eventenddate;

            @JsonProperty("playtime")
            private String playtime;

            @JsonProperty("eventplace")
            private String eventplace;

            @JsonProperty("usetimefestival")
            private String usetimefestival;

            @JsonProperty("sponsor1")
            private String sponsor1; // 주최자 정보

            @JsonProperty("sponsor1tel")
            private String sponsor1tel; // 주최자 연락처
        }
    }
}