package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.search.repository.HotelSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelSearchServiceImpl implements HotelSearchService {

    @Autowired
    private HotelSearchRepository hotelSearchRepository;


    @Override
    public List<HotelsDTO> searchHotelsByTitle(String title) {

        List<Object[]> result = hotelSearchRepository.findTop4ByTitleContaining(title);
        List<HotelsDTO> hotelDtoResult = new ArrayList<>();


        for (Object[] dto : result) {
            HotelsEntity hotelsEntity = (HotelsEntity) dto[0];
            HotelsImgEntity hotelsImgEntity = (HotelsImgEntity) dto[1];

            hotelDtoResult.add(entityToDTO(hotelsEntity, hotelsImgEntity));
        }
        return hotelDtoResult;
    }

    @Override
    public List<HotelsDTO> searchHotelsByAddr1(String addr1) {

        List<Object[]> result = hotelSearchRepository.findTop4ByAddrContaining(addr1);
        List<HotelsDTO> hotelsDtoResult = new ArrayList<>();

        for (Object[] dto : result) {
            HotelsEntity hotelsEntity = (HotelsEntity) dto[0];
            HotelsImgEntity hotelsImgEntity = (HotelsImgEntity) dto[1];

            hotelsDtoResult.add(entityToDTO(hotelsEntity, hotelsImgEntity));
        }
        return hotelsDtoResult;
    }

}







