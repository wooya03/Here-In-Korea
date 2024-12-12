package kr.kro.hereinkorea.domain.hotels.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsImgDTO;
import kr.kro.hereinkorea.domain.hotels.dto.RoomDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.hotels.entity.RoomEntity;

import java.util.List;
import java.util.stream.Collectors;

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

    default HotelsDTO entityToDTO(HotelsEntity hotels, HotelsImgEntity img, List<RoomEntity> rooms) {
        List<RoomDTO> roomDTOs = rooms.stream()
                .map(room -> RoomDTO.builder()
                        .id(room.getId())
                        .roomtitle(room.getRoomtitle())
                        .roomsize1(room.getRoomsize1())
                        .roomcount(room.getRoomcount())
                        .roombasecount(room.getRoombasecount())
                        .roommaxcount(room.getRoommaxcount())
                        .roomoffseasonminfee1(room.getRoomoffseasonminfee1())
                        .roomoffseasonminfee2(room.getRoomoffseasonminfee2())
                        .roomintro(room.getRoomintro())
                        .roombathfacility(room.getRoombathfacility())
                        .roombath(room.getRoombath())
                        .roomhometheater(room.getRoomhometheater())
                        .roomaircondition(room.getRoomaircondition())
                        .roomtv(room.getRoomtv())
                        .roompc(room.getRoompc())
                        .roomcable(room.getRoomcable())
                        .roominternet(room.getRoominternet())
                        .roomrefrigerator(room.getRoomrefrigerator())
                        .roomtoiletries(room.getRoomtoiletries())
                        .roomsofa(room.getRoomsofa())
                        .roomcook(room.getRoomcook())
                        .roomtable(room.getRoomtable())
                        .roomhairdryer(room.getRoomhairdryer())
                        .roomimg1(room.getRoomimg1())
                        .roomimg2(room.getRoomimg2())
                        .roomimg3(room.getRoomimg3())
                        .roomimg4(room.getRoomimg4())
                        .roomimg5(room.getRoomimg5())
                        .build())
                .collect(Collectors.toList());

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
                .rooms(roomDTOs) // Room 정보 추가
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
