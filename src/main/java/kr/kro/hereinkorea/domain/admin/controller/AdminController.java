package kr.kro.hereinkorea.domain.admin.controller;

import kr.kro.hereinkorea.domain.admin.service.AdminMemberService;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.Entity.enums.MemberRole;
import kr.kro.hereinkorea.domain.member.controller.JwtResponse;
import kr.kro.hereinkorea.domain.member.controller.RegisterController;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import kr.kro.hereinkorea.global.jwt.properties.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminMemberService adminMemberService;

    @Autowired
    MemberService memberService;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/member")
    public ResponseEntity<PageResultDTO<MemberDTO, MemberEntity>> getMembers(@RequestParam(value = "memName", required = false) String memName, PageRequestDTO pageRequestDTO){
        PageResultDTO<MemberDTO, MemberEntity> memberList = adminMemberService.getMember(memName, pageRequestDTO);

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/main")
    public long getMemberCount(){
        return adminMemberService.getTotalMemberCount();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginAdmin(@RequestBody MemberDTO memberDTO){
        try {
            logger.info("로그인 요청: {}", memberDTO);
            // 사용자 이름을 기반으로 데이터베이스에서 회원 조회
            MemberEntity memberEntity = memberService.findByMemId(memberDTO.getMemId());

            // 사용자 존재 여부와 비밀번호 일치 여부, 그리고 역할 확인
            if (memberEntity != null && memberEntity.getMemPass().equals(memberDTO.getMemPass())) {
                if (memberEntity.getRole().equals(MemberRole.ADMIN)) {
                    // 로그인 성공 시 JWT 토큰 생성
                    String accessToken = jwtUtil.generateAccessToken(memberDTO.getMemId());
                    String refreshToken = jwtUtil.generateRefreshToken(memberDTO.getMemId());

                    logger.info("로그인 성공, 토큰 발급: {}", memberDTO.getMemId());

                    // 생성된 JWT 토큰을 클라이언트에 반환 (Json 형식)
                    return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
                } else {
                    // ADMIN 역할이 아닌 경우 403 Forbidden 반환
                    logger.warn("관리자 권한이 필요합니다: {}", memberDTO.getMemId());
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(new JwtResponse("관리자 권한이 필요합니다.", ""));
                }
            } else {
                // 로그인 실패 시 401 Unauthorized 반환
                logger.warn("비밀번호 불일치: {}", memberDTO.getMemId());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new JwtResponse("아이디 또는 비밀번호가 일치하지 않습니다.", ""));
            }
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생: {}", e.getMessage(), e);
            // 예외 발생 시 500 Internal Server Error 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new JwtResponse("로그인 중 오류가 발생했습니다: " + e.getMessage(), ""));
        }
    }
}
