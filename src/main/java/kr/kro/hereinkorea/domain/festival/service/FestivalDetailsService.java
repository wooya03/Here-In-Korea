package kr.kro.hereinkorea.domain.festival.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.transaction.Transactional;
import kr.kro.hereinkorea.domain.festival.dto.FestivalDetailsDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalDetailsEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.mapper.FestivalMapper;
import kr.kro.hereinkorea.domain.festival.repository.FestivalDetailsRepository;
import kr.kro.hereinkorea.domain.festival.repository.FestivalRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.jsoup.Jsoup;

@Service
@Slf4j
@RequiredArgsConstructor
public class FestivalDetailsService {

    private final FestivalRepository festivalRepository;
    private final FestivalDetailsRepository festivalDetailsRepository;
    private final XmlMapper xmlMapper; // XML 처리용 XmlMapper
    private final RestTemplate restTemplate; // RestTemplate 객체를 주입

    private static final String SERVICE_KEY = "Vzrka/MW4E5Dh3bkH2muvLYWT9BFjjgp1sKVHTbfHKb6Qvku+nS4e4UnV+MQgqlSZR1D00kCcMI5xqtvqwPQtg==";

    private static final String URL_BASE = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?" +
            "ServiceKey=%s" + // 인코딩된 서비스 키
            "&contentTypeId=15" +
            "&MobileOS=ETC&MobileApp=AppTest";
//    &contentId=%d

    private String getEncodedApiUrl(Long contentId) {
        try {
            // 서비스 키 인코딩
            String encodedServiceKey = URLEncoder.encode(SERVICE_KEY, StandardCharsets.UTF_8.toString()); // 서비스 키 인코딩

            // URL을 생성할 때 인코딩된 서비스 키를 삽입
            String url = String.format(URL_BASE, encodedServiceKey) + "&contentId=" + contentId;

            // URI 객체로 변환
            URI uri = new URI(url);
            return uri.toString(); // URI를 문자열로 반환
        } catch (UnsupportedEncodingException e) {
            log.error("Error encoding ServiceKey: {}", e.getMessage());
            throw new RuntimeException("Error encoding ServiceKey", e);
        } catch (Exception e) {
            log.error("Error creating URI for contentId {}: {}", contentId, e.getMessage());
            throw new RuntimeException("Error creating URI", e);
        }
    }

    @Transactional
    public void addFestivalDetails() {
        // FestivalEntity 목록을 가져옵니다.
        List<FestivalEntity> festivals = festivalRepository.findAll();

        for (FestivalEntity festival : festivals) {
            try {
                // contentId를 이용해 API URL을 생성
                Long contentId = festival.getContentId();
                String apiUrl = getEncodedApiUrl(contentId);
//                String apiUrl = URL_BASE + "&contentId=" + contentId;
                log.info("Requesting API URL: {}", apiUrl);

                // API 호출
                String response = callApi(apiUrl);

                // XML 데이터를 ApiResponse 객체로 변환
                ApiResponse apiResponse = xmlMapper.readValue(response, ApiResponse.class);
                log.info("API Response: {}", response);
                // API 응답이 정상적으로 왔는지 확인
                if (apiResponse != null && apiResponse.getBody() != null && apiResponse.getBody().getItems() != null) {
                    apiResponse.getBody().getItems().forEach(item -> {
                        try {
                            // FestivalDetailsDTO 생성
                            FestivalDetailsDTO festivalDetailsDTO = FestivalDetailsDTO.builder()
                                    .contentid(item.getContentId())
                                    .sponsor1(item.getSponsor1())
                                    .sponsor1tel(item.getSponsor1tel())
                                    .eventstartdate(item.getEventstartdate())
                                    .eventenddate(item.getEventenddate())
                                    .playtime(item.getPlaytime())
                                    .eventplace(item.getEventplace())
                                    .usetimefestival(item.getUsetimefestival())
                                    .build();

                            String playtime = festivalDetailsDTO.getPlaytime();
                            String usetimefestival = festivalDetailsDTO.getUsetimefestival();

                            if (usetimefestival != null) {
                                usetimefestival = unescapeHtml(usetimefestival);
                                usetimefestival = Jsoup.parse(usetimefestival).text(); // HTML 태그 제거
                                if (usetimefestival.getBytes(StandardCharsets.UTF_8).length > 255) {
                                    festivalDetailsDTO.setUsetimefestival(truncateToByteLength(usetimefestival, 255));  // 255자까지 자른다
                                }
                            }

                            if (playtime != null) {
                                playtime = unescapeHtml(playtime);
                                playtime = Jsoup.parse(playtime).text(); // HTML 태그 제거
                                if (playtime.getBytes(StandardCharsets.UTF_8).length > 255) {
                                    festivalDetailsDTO.setPlaytime(truncateToByteLength(playtime, 255));  // 255자까지 자른다
                                }
                            }


                            // DTO를 Entity로 변환
                            FestivalDetailsEntity festivalDetailsEntity = FestivalMapper.dtoToEntity(festivalDetailsDTO, festival);

                            // 저장
                            festivalDetailsRepository.save(festivalDetailsEntity);
                            log.info("Saved FestivalDetailsEntity for contentId {}", item.getContentId());
                        } catch (Exception e) {
                            log.error("Error saving FestivalDetails for contentId {}: {}", item.getContentId(), e.getMessage());
                        }
                    });
                } else {
                    log.warn("Empty or invalid API response for contentId: {}", contentId);
                }
            } catch (Exception e) {
                log.error("Error processing contentId {}: {}", festival.getContentId(), e.getMessage());
            }
        }
    }

    // HTML 엔티티를 실제 HTML 태그로 변환하는 메서드
    private String unescapeHtml(String input) {
        return StringEscapeUtils.unescapeHtml4(input);
    }


    // API 호출 메서드
    private String callApi(String apiUrl) {
        try {
            URI uri = new URI(apiUrl); // URL을 URI로 변환
            String response = restTemplate.getForObject(uri, String.class); // URI를 RestTemplate에 전달

            if (response == null || response.isEmpty()) {
                throw new RuntimeException("API response is empty for URL: " + apiUrl);
            }
            log.info("API Response: {}", response); // 응답 로그 출력
            return response;
        } catch (Exception e) {
            log.error("API call failed for URL {}: {}", apiUrl, e.getMessage());
            throw new RuntimeException("API call failed", e);
        }
    }

    // 날짜 문자열을 LocalDate로 변환
    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null; // 날짜가 null이거나 비어있으면 null 반환
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(dateStr, formatter);
    }

    private String truncateToByteLength(String input, int maxLength) {
        if (input == null) {
            return null;
        }

        // UTF-8로 인코딩한 바이트 배열을 얻습니다.
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

        // 최대 바이트 길이를 넘지 않도록 자릅니다.
        if (bytes.length > maxLength) {
            byte[] truncatedBytes = new byte[maxLength];
            System.arraycopy(bytes, 0, truncatedBytes, 0, maxLength);
            return new String(truncatedBytes, StandardCharsets.UTF_8);
        }

        return input; // 길이가 maxLength 이하인 경우 그대로 반환
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
            private String sponsor1;

            @JsonProperty("sponsor1tel")
            private String sponsor1tel;
        }
    }
}
