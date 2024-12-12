package kr.kro.hereinkorea.domain.hotels.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsImgDTO;
import kr.kro.hereinkorea.domain.hotels.dto.RoomDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.hotels.entity.RoomEntity;

public interface HotelsService {

    default RoomEntity dtoToEntity(RoomDTO dto){
        HotelsEntity hotelsEntity = HotelsEntity.builder().contentid(dto.getContentid()).build();

        return RoomEntity.builder()
                .id(dto.getId())
                .hotels(hotelsEntity)
                .roomtitle(dto.getRoomtitle())
                .roomsize1(dto.getRoomsize1())
                .roomcount(dto.getRoomcount())
                .roombasecount(dto.getRoombasecount())
                .roommaxcount(dto.getRoommaxcount())
                .roomoffseasonminfee1(dto.getRoomoffseasonminfee1())
                .roomoffseasonminfee2(dto.getRoomoffseasonminfee2())
                .roomintro(dto.getRoomintro())
                .roombathfacility(dto.getRoombathfacility())
                .roombath(dto.getRoombath())
                .roomhometheater(dto.getRoomhometheater())
                .roomaircondition(dto.getRoomaircondition())
                .roomtv(dto.getRoomtv())
                .roompc(dto.getRoompc())
                .roomcable(dto.getRoomcable())
                .roominternet(dto.getRoominternet())
                .roomrefrigerator(dto.getRoomrefrigerator())
                .roomtoiletries(dto.getRoomtoiletries())
                .roomsofa(dto.getRoomsofa())
                .roomcook(dto.getRoomcook())
                .roomtable(dto.getRoomtable())
                .roomhairdryer(dto.getRoomhairdryer())
                .roomimg1(dto.getRoomimg1())
                .roomimg2(dto.getRoomimg2())
                .roomimg3(dto.getRoomimg3())
                .roomimg4(dto.getRoomimg4())
                .roomimg5(dto.getRoomimg5())
                .build();
    }

    default RoomDTO entityToDTO(RoomEntity entity) {
        return RoomDTO.builder()
                .id(entity.getId())
                .contentid(entity.getHotels().getContentid())
                .roomtitle(entity.getRoomtitle())
                .roomsize1(entity.getRoomsize1())
                .roomcount(entity.getRoomcount())
                .roombasecount(entity.getRoombasecount())
                .roommaxcount(entity.getRoommaxcount())
                .roomoffseasonminfee1(entity.getRoomoffseasonminfee1())
                .roomoffseasonminfee2(entity.getRoomoffseasonminfee2())
                .roomintro(entity.getRoomintro())
                .roombathfacility(entity.getRoombathfacility())
                .roombath(entity.getRoombath())
                .roomhometheater(entity.getRoomhometheater())
                .roomaircondition(entity.getRoomaircondition())
                .roomtv(entity.getRoomtv())
                .roompc(entity.getRoompc())
                .roomcable(entity.getRoomcable())
                .roominternet(entity.getRoominternet())
                .roomrefrigerator(entity.getRoomrefrigerator())
                .roomtoiletries(entity.getRoomtoiletries())
                .roomsofa(entity.getRoomsofa())
                .roomcook(entity.getRoomcook())
                .roomtable(entity.getRoomtable())
                .roomhairdryer(entity.getRoomhairdryer())
                .roomimg1(entity.getRoomimg1())
                .roomimg2(entity.getRoomimg2())
                .roomimg3(entity.getRoomimg3())
                .roomimg4(entity.getRoomimg4())
                .roomimg5(entity.getRoomimg5())
                .build();
    }

    default HotelsDTO entityToDTO(HotelsEntity hotels, HotelsImgEntity img){
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

    default HotelsEntity dtoToEntity(HotelsDTO dto){
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

    default HotelsImgEntity dtoToEntity(HotelsImgDTO dto){
        HotelsEntity hotelsEntity = HotelsEntity.builder().contentid(dto.getContentid()).build();
        return HotelsImgEntity.builder()
                .id(dto.getId())
                .hotels(hotelsEntity)
                .firstimage(dto.getFirstimage())
                .firstimage2(dto.getFirstimage2())
                .build();
    }
}
