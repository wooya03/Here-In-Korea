package kr.kro.hereinkorea.domain.hotels.dto;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelsDTO {
    private Long id;
    private String name;
    private String address;
    private int area_code;
    private Double hotelMapx;
    private Double hotelMapy;
    private String tel;
    private String detail;
    private String homepage;
    private Boolean hasParking;
    private String checkIn;
    private String checkOut;
    private String hotelTag;
}
