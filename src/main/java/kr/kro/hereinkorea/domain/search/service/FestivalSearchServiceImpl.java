package kr.kro.hereinkorea.domain.search.service;


import kr.kro.hereinkorea.domain.festival.dto.FestivalDTO;
import kr.kro.hereinkorea.domain.festival.entity.FestivalEntity;
import kr.kro.hereinkorea.domain.festival.entity.FestivalImgEntity;
import kr.kro.hereinkorea.domain.search.repository.FestivalSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FestivalSearchServiceImpl implements FestivalSearchService {

    @Autowired
    private FestivalSearchRepository festivalSearchRepository;

    @Override
    public List<FestivalDTO> searchFestivalsByTitle(String title){

        List<Object[]> result = festivalSearchRepository.findTop4ByTitleContaining(title);
        List<FestivalDTO> festivalDtoResult = new ArrayList<>();


        for (Object[] dto : result) {
            FestivalEntity festivalEntity = (FestivalEntity) dto[0];
            FestivalImgEntity festivalImgEntity = (FestivalImgEntity) dto[1];

            festivalDtoResult.add(entityToDTO(festivalEntity, festivalImgEntity));
        }
        return festivalDtoResult;

    }

    @Override
    public List<FestivalDTO> searchFestivalsByAddr1(String addr1) {
        List<Object[]> result = festivalSearchRepository.findTop4ByAddrContaining(addr1);
        List<FestivalDTO> festivalDtoResult = new ArrayList<>();


        for (Object[] dto : result) {
            FestivalEntity festivalEntity = (FestivalEntity) dto[0];
            FestivalImgEntity festivalImgEntity = (FestivalImgEntity) dto[1];

            festivalDtoResult.add(entityToDTO(festivalEntity, festivalImgEntity));
        }
        return festivalDtoResult;
    }


}
