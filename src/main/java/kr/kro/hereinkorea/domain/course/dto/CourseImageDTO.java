package kr.kro.hereinkorea.domain.course.dto;

import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseImageDTO {
    private Long courseImgId;
    private Long courseId; // String 타입으로 변경
    private String courseImageUrl;

    public static CourseImageDTO fromEntity(CourseImageEntity entity) {
        CourseImageDTO dto = new CourseImageDTO();
        dto.setCourseImgId(entity.getCourseImgId());
        dto.setCourseId(entity.getCourseId());
        dto.setCourseImageUrl(entity.getCourseImageUrl());
        return dto;
    }

}

