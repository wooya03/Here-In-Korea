package kr.kro.hereinkorea.domain.festival.mapper;


import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;

public class FestivalMapper {
    public static FestivalEntity dtoToEntity(FestivalDTO festivalDTO){
        return FestivalEntity.builder()
                .contentId(festivalDTO.getContentId())
                .contentTypeId(festivalDTO.getContentTypeId())
                .title(festivalDTO.getTitle())
                .addr1(festivalDTO.getAddr1())
                .addr2(festivalDTO.getAddr2())
                .areacode(festivalDTO.getAreacode())
                .mapx(festivalDTO.getMapx())
                .mapy(festivalDTO.getMapy())
                .tel(festivalDTO.getTel())
                .eventStartDate(festivalDTO.getEventStartDate())
                .eventEndDate(festivalDTO.getEventEndDate())
                .build();
    }

    public static FestivalDTO entityToDto(FestivalEntity festivalEntity){
        return FestivalDTO.builder()
                .contentId(festivalEntity.getContentId())
                .contentTypeId(festivalEntity.getContentTypeId())
                .title(festivalEntity.getTitle())
                .addr1(festivalEntity.getAddr1())
                .addr2(festivalEntity.getAddr2())
                .areacode(festivalEntity.getAreacode())
                .mapx(festivalEntity.getMapx())
                .mapy(festivalEntity.getMapy())
                .tel(festivalEntity.getTel())
                .eventStartDate(festivalEntity.getEventStartDate())
                .eventEndDate(festivalEntity.getEventEndDate())
                .build();
    }
}
