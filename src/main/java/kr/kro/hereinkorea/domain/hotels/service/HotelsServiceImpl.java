package kr.kro.hereinkorea.domain.hotels.service;

import kr.kro.hereinkorea.domain.hotels.repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HotelsServiceImpl implements HotelsService {

    private final HotelsApiService hotelsApiService;
    private final HotelsRepository hotelsRepository;

    @Autowired
    public HotelsServiceImpl(HotelsApiService hotelsApiService, HotelsRepository hotelsRepository){
        this.hotelsApiService = hotelsApiService;
        this.hotelsRepository = hotelsRepository;
    }

    public void saveHotelsData(){
        String response = hotelsApiService.fetch();

    }

}
