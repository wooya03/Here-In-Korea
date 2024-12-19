package kr.kro.hereinkorea.domain.course.service;

import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.course.entity.CourseEntity;
import kr.kro.hereinkorea.domain.course.mapper.CourseMapper;
import kr.kro.hereinkorea.domain.course.repository.CourseRepository;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final MemberRepository memberRepository;
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    // 코스 전체 조회
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 코스 단일 조회
    public CourseDTO getCourseById(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id));
        return courseMapper.toDTO(course);
    }

    // 코스 생성
    public CourseDTO createCourse(CourseDTO courseDTO) {
        MemberEntity member = memberRepository.findById(courseDTO.getMemId())
                .orElseThrow(() -> new IllegalArgumentException("회원 ID가 유효하지 않습니다."));

        CourseEntity courseEntity = courseMapper.toEntity(courseDTO, member);
        CourseEntity savedEntity = courseRepository.save(courseEntity);
        return courseMapper.toDTO(savedEntity);
    }

    // 코스 수정
    public CourseDTO updateCourse(Long id, CourseDTO updatedCourseDTO) {
        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id));
        MemberEntity existingMember = existingCourse.getMemId();
        CourseEntity updatedCourse = courseMapper.toEntity(updatedCourseDTO, existingMember);
        updatedCourse.setCourseId(existingCourse.getCourseId());
        updatedCourse.setCreatedDate(existingCourse.getCreatedDate());
        return courseMapper.toDTO(courseRepository.save(updatedCourse));
    }

    // 코스 삭제
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new IllegalArgumentException("코스를 찾을 수 없습니다: ID=" + id);
        }
        courseRepository.deleteById(id);
    }
}
