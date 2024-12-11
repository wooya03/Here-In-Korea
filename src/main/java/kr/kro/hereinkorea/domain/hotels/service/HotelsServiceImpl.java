package kr.kro.hereinkorea.domain.hotels.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.hotels.repository.HotelsRepository;
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
public class HotelsServiceImpl implements HotelsService{

    private final HotelsRepository hotelsRepository;

    public PageResultDTO<HotelsDTO, Object[]> getList(PageRequestDTO requestDTO) {
        requestDTO.setSize(30);
        Page<Object[]> result = hotelsRepository.getHotelsCount(
                requestDTO.getPageable(Sort.by("contentid").descending())
        );
        return new PageResultDTO<HotelsDTO, Object[]>(result,
                en -> entityToDTO((HotelsEntity) en[0], (HotelsImgEntity)en[1])
        );
    }
}
