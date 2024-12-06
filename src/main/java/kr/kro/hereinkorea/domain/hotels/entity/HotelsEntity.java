package kr.kro.hereinkorea.domain.hotels.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotels")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HotelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(nullable = false)
    private int area_code;

    @Column(nullable = false)
    private Double hotelMapx;

    @Column(nullable = false)
    private Double hotelMapy;

    @Column(length = 13, nullable = false)
    private String tel;

    @Column(length = 255, nullable = false)
    private String detail;

    @Column(length = 255, nullable = false)
    private String homepage;

    @Column(nullable = false)
    private Boolean hasParking;

    @Column(length = 5, nullable = false)
    private String checkIn;

    @Column(length = 5, nullable = false)
    private String checkOut;

    @Column(length = 100)
    private String hotelTag;
}
