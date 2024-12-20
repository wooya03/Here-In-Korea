package kr.kro.hereinkorea.domain.festival.mapper;


import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.dto.FestivalDetailsDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalDetailsEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;

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

    public static FestivalDTO entityToDTO(FestivalEntity festivalEntity){
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

    public static FestivalDTO mapToFestivalDTO(FestivalEntity festivalEntity, FestivalImgEntity festivalImgEntity) {
        return FestivalDTO.builder()
                .contentId(festivalEntity.getContentId())
                .title(festivalEntity.getTitle())
                .firstimage2(festivalImgEntity != null ? festivalImgEntity.getFirstimage2() : null) // 썸네일 이미지
                .eventStartDate(festivalEntity.getEventStartDate())
                .eventEndDate(festivalEntity.getEventEndDate())
                .build();
    }

    public static FestivalDetailsEntity dtoToEntity(FestivalDetailsDTO dto, FestivalEntity festival){
        return FestivalDetailsEntity.builder()
                .festival(festival)
                .sponsor1(dto.getSponsor1())
                .sponsor1tel(dto.getSponsor1tel())
                .eventstartdate(dto.getEventstartdate())
                .eventenddate(dto.getEventenddate())
                .playtime(dto.getPlaytime())
                .eventplace(dto.getEventplace())
                .usetimefestival(dto.getUsetimefestival())
                .overview(dto.getOverview())
                .build();
    }

    public static FestivalDetailsDTO entityToDTO(FestivalDetailsEntity entity){
        return FestivalDetailsDTO.builder()
                .id(entity.getId())
                .contentid(entity.getFestival().getContentId())
                .sponsor1(entity.getSponsor1())
                .sponsor1tel(entity.getSponsor1tel())
                .eventstartdate(entity.getEventstartdate())
                .eventenddate(entity.getEventenddate())
                .playtime(entity.getPlaytime())
                .eventplace(entity.getEventplace())
                .usetimefestival(entity.getUsetimefestival())
                .overview(entity.getOverview())
                .build();
    }

}
