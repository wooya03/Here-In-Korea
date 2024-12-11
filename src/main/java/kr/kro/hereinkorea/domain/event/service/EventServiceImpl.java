package kr.kro.hereinkorea.domain.event.service;

import kr.kro.hereinkorea.domain.event.dto.EventDTO;
import kr.kro.hereinkorea.domain.event.entity.EventEntity;
import kr.kro.hereinkorea.domain.event.entity.EventImgEntity;
import kr.kro.hereinkorea.domain.event.repository.EventRepository;
import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    public PageResultDTO<EventDTO, Object[]> getList(PageRequestDTO requestDTO) {
        requestDTO.setSize(30);
        Page<Object[]> result = eventRepository.getEventCount(
                requestDTO.getPageable(Sort.by("contentid").descending())
        );
        return new PageResultDTO<EventDTO, Object[]>(result,
                en -> entityToDTO((EventEntity) en[0], (EventImgEntity) en[1])
        );
    }
}
