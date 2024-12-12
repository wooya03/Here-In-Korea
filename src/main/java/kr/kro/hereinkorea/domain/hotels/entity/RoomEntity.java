package kr.kro.hereinkorea.domain.hotels.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private HotelsEntity hotels;

    @Column(length = 50, nullable = false)
    private String roomtitle; // 객실 명칭

    @Column(nullable = false)
    private int roomsize1; // 객실 크기(평)

    @Column(nullable = false)
    private int roomcount; // 객실수

    @Column(nullable = false)
    private int roombasecount; // 기준인원

    @Column(nullable = false)
    private int roommaxcount; // 최대인원

    @Column(nullable = false)
    private int roomoffseasonminfee1; // 비수기주중최소

    @Column(nullable = false)
    private int roomoffseasonminfee2; // 비수기주말최소

    @Column(nullable = false)
    private String roomintro; //객실소개

    @Column(length = 1)
    private String roombathfacility; //목욕시설여부

    @Column(length = 1)
    private String roombath; // 욕조여부

    @Column(length = 1)
    private String roomhometheater; // 홈시어터여부

    @Column(length = 1)
    private String roomaircondition; // 에어컨여부

    @Column(length = 1)
    private String roomtv; // TV여부

    @Column(length = 1)
    private String roompc; //PC여부

    @Column(length = 1)
    private String roomcable; // 케이블설치여부

    @Column(length = 1)
    private String roominternet; // 인터넷여부

    @Column(length = 1)
    private String roomrefrigerator; //냉장고여부

    @Column(length = 1)
    private String roomtoiletries; //세면도구여부

    @Column(length = 1)
    private String roomsofa; // 소파여부

    @Column(length = 1)
    private String roomcook; // 취사용품여부

    @Column(length = 1)
    private String roomtable; // 테이블여부

    @Column(length = 1)
    private String roomhairdryer; // 드라이기여부

    @Column
    private String roomimg1; // 객실사진1

    @Column
    private String roomimg2; // 객실사진2

    @Column
    private String roomimg3; // 객실사진3

    @Column
    private String roomimg4; // 객실사진4

    @Column
    private String roomimg5; // 객실사진5
}
