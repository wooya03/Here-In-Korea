package kr.kro.hereinkorea.domain.festival.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;
import kr.kro.hereinkorea.domain.festival.mapper.FestivalMapper;
import kr.kro.hereinkorea.domain.festival.repository.FestivalImgRepository;
import kr.kro.hereinkorea.domain.festival.repository.FestivalRepository;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static kr.kro.hereinkorea.domain.festival.mapper.FestivalMapper.mapToFestivalDTO;

@Service
@Slf4j
@RequiredArgsConstructor
public class FestivalService {
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper
    private final FestivalRepository festivalRepository;
    private final FestivalImgRepository festivalImgRepository;



    private static final String AREA_BASED_LIST_URL = "http://apis.data.go.kr/B551011/KorService1/searchFestival1?" +
    "eventStartDate=20241201&eventEndDate=20241231" +
    "&areaCode=&sigunguCode=" +
    "&ServiceKey=6DNqQa8aepnBmiGcSEH7tkLBSEGDEaDTGEJQnNx6G%2Bf5U4Kqf%2FDNoQVC%2F%2BrWRNRLW9RaDY1qHUvGKF1hVzbrRA%3D%3D" +
    "&listYN=Y&MobileOS=ETC&MobileApp=AppTest" +
    "&arrange=A" +
    "&numOfRows=30" +
    "&pageNo=1";

    public void addContentFestival() {
        try {
            // API 호출
            String areaBasedListResponse = callApi(AREA_BASED_LIST_URL);
            log.info("areaBasedListResponse: {}", areaBasedListResponse);

            // XML 데이터를 ApiResponse 객체로 변환
            ApiResponse apiResponse = xmlMapper.readValue(areaBasedListResponse, ApiResponse.class);

            // API 응답 데이터 처리
            apiResponse.getBody().getItems().forEach(item -> {
                try {
                    // FestivalEntity 생성
                    Long contentId = item.getContentId();
                    FestivalEntity festivalEntity = FestivalEntity.builder()
                            .contentId(contentId)
                            .contentTypeId(item.getContentTypeId())
                            .title(item.getTitle())
                            .addr1(item.getAddr1())
                            .addr2(item.getAddr2())
                            .areacode(item.getAreacode())
                            .mapx(item.getMapx())
                            .mapy(item.getMapy())
                            .tel(item.getTel())
                            .eventStartDate(parseDate(item.getEventStartDate()))
                            .eventEndDate(parseDate(item.getEventEndDate()))
                            .build();

                    // FestivalEntity 저장
                    festivalRepository.save(festivalEntity);

                    // FestivalImgEntity 저장 (이미지가 있는 경우에만)
                    if (item.getFirstimage() != null && !item.getFirstimage().isEmpty()) {
                        FestivalImgEntity festivalImgEntity = FestivalImgEntity.builder()
                                .festival(festivalEntity)
                                .firstimage(item.getFirstimage())
                                .firstimage2(item.getFirstimage2())
                                .build();

                        festivalImgRepository.save(festivalImgEntity);
                    }

                } catch (Exception e) {
                    log.error("Error processing contentId {}: {}", item.getContentId(), e.getMessage());
                }
            });

        } catch (Exception e) {
            log.error("API 호출 및 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("API 처리 실패", e);
        }
    }


//     API 호출 메서드
//     @param apiUrl API 호출 URL
//     @return String 응답 데이터
//     @throws 예외처리

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


//     날짜 문자열을 LocalDate로 변환
//     @param dateStr 날짜 문자열 (yyyyMMdd 형식)
//     @return LocalDate

    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null; // 날짜가 null이거나 비어있으면 null 반환
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateStr, formatter);
    }


//    API 응답을 매핑하기 위한 클래스
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
            private String eventStartDate;

            @JsonProperty("eventenddate")
            private String eventEndDate;

            @JsonProperty("firstimage")
            private String firstimage;

            @JsonProperty("firstimage2")
            private String firstimage2;

        }
    }

    public PageResultDTO<FestivalDTO, Object[]> getList(PageRequestDTO requestDTO) {
        // 페이징 및 정렬 설정
        requestDTO.setSize(30); // 한 페이지에 30개 데이터 표시
        Page<Object[]> result = festivalRepository.getFestivalWithImages(
                requestDTO.getPageable(Sort.by("contentId").descending())
        );

        // PageResultDTO 생성
        return new PageResultDTO<>(result, en -> mapToFestivalDTO((FestivalEntity) en[0], (FestivalImgEntity) en[1]));
    }
}
