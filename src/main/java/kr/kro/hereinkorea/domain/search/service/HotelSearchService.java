package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;

import java.util.List;
import java.util.Objects;

public interface HotelSearchService {

    List<HotelsDTO> searchHotelsByTitle(String title);
    List<HotelsDTO> searchHotelsByAddr1(String addr1);

    default HotelsDTO entityToDTO(HotelsEntity hotels, HotelsImgEntity img) {
        return HotelsDTO.builder()
                .contentid(hotels.getContentid())
                .title(hotels.getTitle())
                .addr1(hotels.getAddr1())
                .addr2(hotels.getAddr2())
                .areacode(hotels.getAreacode())
                .mapx(hotels.getMapx())
                .mapy(hotels.getMapy())
                .tel(hotels.getTel())
                .createDate(hotels.getCreatedDate())
                .modifiedDate(hotels.getModifiedDate())
                .firstimage2(img != null ? img.getFirstimage() : null)
                .build();
    }
    //필요하다면 사용
    default HotelsEntity dtoToEntity(HotelsDTO dto) {
        return HotelsEntity.builder()
                .contentid(dto.getContentid())
                .title(dto.getTitle())
                .addr1(dto.getAddr1())
                .addr2(dto.getAddr2())
                .areacode(dto.getAreacode())
                .mapx(dto.getMapx())
                .mapy(dto.getMapy())
                .tel(dto.getTel())
                .build();
    }



}
