package kr.kro.hereinkorea.domain.hotels.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDTO {

    private Long id;
    private Long contentid;
    private String roomtitle; // 객실 명칭
    private int roomsize1; // 객실 크기(평)
    private int roomcount; // 객실수
    private int roombasecount; // 기준인원
    private int roommaxcount; // 최대인원
    private int roomoffseasonminfee1; // 비수기주중최소
    private int roomoffseasonminfee2; // 비수기주말최소
    private String roomintro; //객실소개
    private String roombathfacility; //목욕시설여부
    private String roombath; // 욕조여부
    private String roomhometheater; // 홈시어터여부
    private String roomaircondition; // 에어컨여부
    private String roomtv; // TV여부
    private String roompc; //PC여부
    private String roomcable; // 케이블설치여부
    private String roominternet; // 인터넷여부
    private String roomrefrigerator; //냉장고여부
    private String roomtoiletries; //세면도구여부
    private String roomsofa; // 소파여부
    private String roomcook; // 취사용품여부
    private String roomtable; // 테이블여부
    private String roomhairdryer; // 드라이기여부
    private String roomimg1; // 객실사진1
    private String roomimg2; // 객실사진2
    private String roomimg3; // 객실사진3
    private String roomimg4; // 객실사진4
    private String roomimg5; // 객실사진5
}
