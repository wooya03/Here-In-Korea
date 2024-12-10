package kr.kro.hereinkorea.domain.event.service;

import kr.kro.hereinkorea.domain.event.dto.EventDTO;
import kr.kro.hereinkorea.domain.event.dto.EventImgDTO;
import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import kr.kro.hereinkorea.domain.event.entity.EventImgEntity;

public interface EventService {

    default EventDTO entityToDTO(EventEntity event, EventImgEntity img){
        return EventDTO.builder()
                .contentid(event.getContentid())
                .arr1(event.getArr1())
                .arr2(event.getArr2())
                .areacode(event.getAreacode())
                .mapx(event.getMapx())
                .mapy(event.getMapy())
                .title(event.getTitle())
                .tel(event.getTel())
                .firstimage(img != null ? img.getFirstimage() : null)
                .firstimage2(img != null ? img.getFirstimage2() : null)
                .eventstartdate(event.getEventstartdate())
                .eventenddate(event.getEventenddate())
                .createDate(event.getCreatedDate())
                .modifiedDate(event.getModifiedDate())
                .build();
    }

    default EventEntity dtoToEntity(EventDTO dto){
        return EventEntity.builder()
                .contentid(dto.getContentid())
                .arr1(dto.getArr1())
                .arr2(dto.getArr2())
                .areacode(dto.getAreacode())
                .mapx(dto.getMapx())
                .mapy(dto.getMapy())
                .title(dto.getTitle())
                .tel(dto.getTel())
                .eventstartdate(dto.getEventstartdate())
                .eventenddate(dto.getEventenddate())
                .build();
    }
    default EventImgEntity dtoToEntity(EventImgDTO dto){
        EventEntity eventEntity = EventEntity.builder().contentid(dto.getContentid()).build();

        return EventImgEntity.builder()
                .id(dto.getId())
                .event(eventEntity)
                .firstimage(dto.getFirstimage())
                .firstimage2(dto.getFirstimage2())
                .build();
    }
    default EventImgDTO EntityToDTO(EventImgEntity entity){
        return EventImgDTO.builder()
                .id(entity.getId())
                .contentid(entity.getEvent().getContentid())
                .firstimage(entity.getFirstimage())
                .firstimage2(entity.getFirstimage2())
                .build();
    }

}
