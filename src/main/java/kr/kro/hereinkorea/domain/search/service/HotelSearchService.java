package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.search.repository.HotelSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelSearchService {

    @Autowired
    private HotelSearchRepository hotelSearchRepository;

    // 지역을 기준으로 호텔 검색
    public List<HotelsEntity> searchHotelsByTitle(String title) {
        return hotelSearchRepository.findByTitleLike("%"+title+"%");
    }
}
