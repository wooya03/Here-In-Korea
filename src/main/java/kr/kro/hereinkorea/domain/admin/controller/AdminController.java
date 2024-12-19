package kr.kro.hereinkorea.domain.admin.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import kr.kro.hereinkorea.domain.admin.service.AdminCourseService;
import kr.kro.hereinkorea.domain.admin.service.AdminMemberService;
import kr.kro.hereinkorea.domain.admin.service.AdminQuestionService;
import kr.kro.hereinkorea.domain.admin.service.AdminReviewService;
import kr.kro.hereinkorea.domain.course.dto.CourseDTO;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.Entity.enums.MemberRole;
import kr.kro.hereinkorea.domain.member.controller.JwtResponse;
import kr.kro.hereinkorea.domain.member.controller.MemberController;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import kr.kro.hereinkorea.domain.qna.question.dto.QuestionDTO;
import kr.kro.hereinkorea.domain.reviewboard.dto.ReviewDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import kr.kro.hereinkorea.global.jwt.properties.JwtProperties;
import kr.kro.hereinkorea.global.jwt.properties.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    AdminMemberService adminMemberService;
    @Autowired
    AdminQuestionService adminQuestionService;
    @Autowired
    AdminReviewService adminReviewService;
    @Autowired
    AdminCourseService adminCourseService;
    @Autowired
    MemberService memberService;
    @Autowired
    private JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @DeleteMapping("course")
    public void deleteCourses(@RequestBody List<Long> courseIds, @RequestHeader("Authorization") String tokenHeader){
        adminCourseService.deleteCourse(courseIds);
    }
    @GetMapping("course")
    public PageResultDTO<CourseDTO, Object[]> getCourses(String courseTitle, String memId, PageRequestDTO pageRequestDTO, @RequestHeader("Authorization") String tokenHeader){
        return adminCourseService.getCourse(courseTitle, memId, pageRequestDTO);

    }

    @DeleteMapping("review")
    public void deleteReviews(@RequestBody List<Long> reviewIds, @RequestHeader("Authorization") String tokenHeader) {
        adminReviewService.deleteReview(reviewIds);
    }

    @GetMapping("review")
    public PageResultDTO<ReviewDTO, Object[]> getReviews(String reviewTitle, String memId, PageRequestDTO pageRequestDTO, @RequestHeader("Authorization") String tokenHeader){
        return adminReviewService.getReview(reviewTitle, memId, pageRequestDTO);
    }

    @DeleteMapping("question")
    public void deleteQuestions(@RequestBody List<Long> questionIds, @RequestHeader("Authorization") String tokenHeader){
        adminQuestionService.deleteQuestion(questionIds);
    }
    @GetMapping("question")
    public PageResultDTO<QuestionDTO, Object[]> getQuestions(String title, String category, PageRequestDTO pageRequestDTO, @RequestHeader("Authorization") String tokenHeader){
        return adminQuestionService.getQuestion(title, category, pageRequestDTO);
    }

    @GetMapping("/member")
    public ResponseEntity<PageResultDTO<MemberDTO, MemberEntity>> getMembers(
            @RequestParam(value = "memName", required = false) String memName,
            @RequestParam(value = "gender", required = false) String gender, // 성별 파라미터 추가
            PageRequestDTO pageRequestDTO,
            @RequestHeader("Authorization") String tokenHeader) {
        PageResultDTO<MemberDTO, MemberEntity> memberList = adminMemberService.getMember(memName, gender, pageRequestDTO);

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/main")
    public long getMemberCount(@RequestHeader("Authorization") String tokenHeader){
        return adminMemberService.getTotalMemberCount();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginAdmin(@RequestBody MemberDTO memberDTO){
        try {
            logger.info("로그인 요청: {}", memberDTO);
            MemberEntity memberEntity = memberService.findByMemId(memberDTO.getMemId());

            if (memberEntity != null && memberEntity.getMemPass().equals(memberDTO.getMemPass())) {
                if (memberEntity.getRole().equals(MemberRole.ADMIN)) {
                    String accessToken = jwtUtil.generateAccessToken(memberDTO.getMemId());
                    String refreshToken = jwtUtil.generateRefreshToken(memberDTO.getMemId());

                    String idFromToken = jwtUtil.getUserIdFromToken(accessToken);
                    logger.info("로그인 성공, 토큰 발급: {}", memberDTO.getMemId());

                    return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
                } else {
                    logger.warn("관리자 권한이 필요합니다: {}", memberDTO.getMemId());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(new JwtResponse("관리자 권한이 필요합니다.", ""));
                }
            } else {
                logger.warn("비밀번호 불일치: {}", memberDTO.getMemId());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new JwtResponse("아이디 또는 비밀번호가 일치하지 않습니다.", ""));
            }
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JwtResponse("로그인 중 오류가 발생했습니다: " + e.getMessage(), ""));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String tokenHeader) {
        System.out.println("받은 Authorization 헤더: " + tokenHeader);  // 헤더 출력

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 올바르지 않습니다.");
        }

        try {
            String token = tokenHeader.replace("Bearer ", "");
            System.out.println("토큰: " + token);  // 토큰 출력

            // JWT 검증 로직

            return ResponseEntity.ok("로그아웃 성공");
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }
    }


    public boolean isTokenBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }

    public Jws<Claims> getClaims(String token) {
        if (isTokenBlacklisted(token)) {
            throw new JwtException("블랙리스트에 등록된 토큰입니다.");
        }
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token);
    }
}
