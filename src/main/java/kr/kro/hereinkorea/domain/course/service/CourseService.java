package kr.kro.hereinkorea.domain.course.service;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.course.mapper.CourseMapper;
import kr.kro.hereinkorea.domain.course.repository.CourseRepository;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    // 코스 전체 조회
    public PageResultDTO getAllCourses(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = courseRepository.getCourseCount(pageable);

        return new PageResultDTO<CourseDTO, Object[]>(result,
                en -> CourseMapper.entityToDTO((CourseEntity) en[0], (MemberEntity) en[1])
        );
    }

    // 코스 단일 조회
    public CourseDTO getCourseById(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id));
        MemberEntity member = MemberEntity.builder().memId(course.getMemId().getMemId()).build();
        return courseMapper.entityToDTO(course,member);
    }

    // 코스 생성
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if(courseDTO.getMemId().isEmpty()){
            new IllegalArgumentException("회원 ID가 유효하지 않습니다.");
        }

        MemberEntity memberEntity = MemberEntity.builder().memId(courseDTO.getMemId()).build();
        CourseEntity courseEntity = courseMapper.dtoToEntity(courseDTO);
        CourseEntity savedEntity = courseRepository.save(courseEntity);
        return CourseMapper.entityToDTO(savedEntity, memberEntity);
    }

    // 코스 수정
    public CourseDTO updateCourse(Long id, CourseDTO updatedCourseDTO) {
        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id));
        MemberEntity existingMember = existingCourse.getMemId();
        CourseEntity updatedCourse = courseMapper.dtoToEntity(updatedCourseDTO);
        updatedCourse.setCourseId(existingCourse.getCourseId());
        return courseMapper.entityToDTO(courseRepository.save(updatedCourse), existingMember);
    }

    // 코스 삭제
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id);
        }
        courseRepository.deleteById(id);
    }
}
