package kr.kro.hereinkorea.domain.festival.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
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
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FestivalDetailsService {

    private final FestivalRepository festivalRepository;
    private final FestivalDetailsRepository festivalDetailsRepository;
    private final XmlMapper xmlMapper;

    private static final String SERVICE_KEY = "Vzrka/MW4E5Dh3bkH2muvLYWT9BFjjgp1sKVHTbfHKb6Qvku+nS4e4UnV+MQgqlSZR1D00kCcMI5xqtvqwPQtg==";

    private static final String DETAIL_INTRO_URL = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?" +
            "ServiceKey=%s&contentTypeId=15&MobileOS=ETC&MobileApp=AppTest&contentId=%d";

    private static final String DETAIL_COMMON_URL = "http://apis.data.go.kr/B551011/KorService1/detailCommon1?" +
            "ServiceKey=%s&contentTypeId=15&contentId=%d&MobileOS=ETC&MobileApp=AppTest&defaultYN=Y&overviewYN=Y";

    @Transactional
    public void addFestivalDetails() {
        List<FestivalEntity> festivals = festivalRepository.findAll();

        for (FestivalEntity festival : festivals) {
            try {
                Long contentId = festival.getContentId();
                String introApiUrl = getEncodedUrl(DETAIL_INTRO_URL, contentId);
                String commonApiUrl = getEncodedUrl(DETAIL_COMMON_URL, contentId);

                log.info("Requesting Detail Intro API: {}", introApiUrl);
                String introResponse = callApi(introApiUrl);

                log.info("Requesting Detail Common API: {}", commonApiUrl);
                String commonResponse = callApi(commonApiUrl);

                xmlMapper.getFactory().setCharacterEscapes(new JsonpCharacterEscapes());

                // Parse responses
                ApiResponse introApiResponse = xmlMapper.readValue(introResponse, ApiResponse.class);
                ApiResponse commonApiResponse = xmlMapper.readValue(commonResponse, ApiResponse.class);

                if (introApiResponse != null && introApiResponse.getBody() != null && introApiResponse.getBody().getItems() != null) {
                    introApiResponse.getBody().getItems().forEach(item -> {
                        try {
                            FestivalDetailsDTO festivalDetailsDTO = FestivalDetailsDTO.builder()
                                    .contentid(item.getContentid())
                                    .sponsor1(item.getSponsor1())
                                    .sponsor1tel(item.getSponsor1tel())
                                    .eventstartdate(item.getEventstartdate())
                                    .eventenddate(item.getEventenddate())
                                    .playtime(cleanText(item.getPlaytime()))
                                    .eventplace(item.getEventplace())
                                    .usetimefestival(cleanText(item.getUsetimefestival()))
                                    .build();

                            // Add overview from common API response (no length restriction)
                            commonApiResponse.getBody().getItems().forEach(commonItem -> {
                                if (commonItem.getContentid() != null && commonItem.getContentid().equals(item.getContentid())) {
                                    // overview가 null일 경우 빈 문자열로 설정
                                    log.info("Overview for contentId {}: {}", commonItem.getContentid(), commonItem.getOverview());
                                    String overview = commonItem.getOverview() != null ? commonItem.getOverview() : "";
                                    festivalDetailsDTO.setOverview(overview);  // No length restriction
                                }
                            });

                            FestivalDetailsEntity festivalDetailsEntity = FestivalMapper.dtoToEntity(festivalDetailsDTO, festival);
                            festivalDetailsRepository.save(festivalDetailsEntity);
                            log.info("Saved FestivalDetailsEntity for contentId {}", item.getContentid());
                        } catch (Exception e) {
                            log.error("Error saving FestivalDetails for contentId {}: {}", item.getContentid(), e.getMessage());
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

    private String getEncodedUrl(String baseUrl, Long contentId) {
        try {
            String encodedServiceKey = URLEncoder.encode(SERVICE_KEY, StandardCharsets.UTF_8.toString());
            return String.format(baseUrl, encodedServiceKey, contentId);
        } catch (UnsupportedEncodingException e) {
            log.error("Error encoding ServiceKey: {}", e.getMessage());
            throw new RuntimeException("Error encoding ServiceKey", e);
        }
    }

    private String callApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        log.info("HTTP Response Code: {}", responseCode);

        if (responseCode != 200) {
            throw new RuntimeException("API call failed with response code: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            log.info("API Response: {}", result);
            return result.toString();
        } finally {
            connection.disconnect();
        }
    }

    private String cleanText(String input) {
        if (input == null) return null;
        String unescaped = StringEscapeUtils.unescapeHtml4(input);
        String cleaned = Jsoup.parse(unescaped).text();
        return cleaned.length() > 255 ? truncateToByteLength(cleaned, 255) : cleaned;
    }

    private String truncateToByteLength(String input, int maxLength) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > maxLength) {
            byte[] truncatedBytes = new byte[maxLength];
            System.arraycopy(bytes, 0, truncatedBytes, 0, maxLength);
            return new String(truncatedBytes, StandardCharsets.UTF_8);
        }
        return input;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApiResponse {
        private Header header;
        private Body body;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Header {
            private String resultCode;
            private String resultMsg;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Body {
            private List<FestivalItem> items;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class FestivalItem {
            private Long contentid;
            private String eventstartdate;
            private String eventenddate;
            private String playtime;
            private String eventplace;
            private String usetimefestival;
            private String sponsor1;
            private String sponsor1tel;
            private String overview;
        }
    }
}