package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;

import java.util.List;

public interface FestivalSearchService {

    List<FestivalDTO> searchFestivalsByTitle(String title);
    List<FestivalDTO> searchFestivalsByAddr1(String addr1);


    default FestivalDTO entityToDTO(FestivalEntity festival, FestivalImgEntity img){
        return FestivalDTO.builder()
                .contentId(festival.getContentId())
                .title(festival.getTitle())
                .addr1(festival.getAddr1())
                .addr2(festival.getAddr2())
                .areacode(festival.getAreacode())
                .mapx(festival.getMapx())
                .mapy(festival.getMapy())
                .tel(festival.getTel())
                .eventStartDate(festival.getEventStartDate())
                .eventEndDate(festival.getEventEndDate())
                .firstimage2(img != null ? img.getFirstimage() : null)
                .build();
    }


}
