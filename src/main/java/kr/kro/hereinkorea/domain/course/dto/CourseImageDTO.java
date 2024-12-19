package kr.kro.hereinkorea.domain.course.dto;

import kr.kro.hereinkorea.domain.course.entity.CourseImageEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseImageDTO {
    private Long courseImgId;
    private Long courseId;
    private String courseImageUrl;

    // Entity -> DTO 변환
    public static CourseImageDTO fromEntity(CourseImageEntity entity) {
        CourseImageDTO dto = new CourseImageDTO();
        dto.setCourseImgId(entity.getCourseImgId());
        dto.setCourseId(entity.getCourseId().getCourseId());  // CourseEntity의 courseId
        dto.setCourseImageUrl(entity.getCourseImageUrl());
        return dto;
    }
}
