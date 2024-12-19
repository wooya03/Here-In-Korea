package kr.kro.hereinkorea.domain.search.service;

import kr.kro.hereinkorea.domain.hotels.dto.HotelsDTO;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsEntity;
import kr.kro.hereinkorea.domain.hotels.entity.HotelsImgEntity;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.domain.reviewboard.entity.ReviewEntity;
import kr.kro.hereinkorea.domain.search.repository.ReviewSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewSearchServiceImpl implements ReviewSearchService{

    @Autowired
    private ReviewSearchRepository reviewSearchRepository;

    @Override
    public List<ReviewDTO> searchReviewsByTitle(String title) {

        List<Object[]> result = reviewSearchRepository.findTop4ByTitleContaining(title);
        List<ReviewDTO> reviewDtoResult = new ArrayList<>();

        for (Object[] dto : result) {
            ReviewEntity reviewEntity = (ReviewEntity) dto[0];
            reviewDtoResult.add(entityToDTO(reviewEntity));
        }
        return reviewDtoResult;
    }
}
