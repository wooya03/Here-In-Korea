package kr.kro.hereinkorea.domain.member.controller;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import kr.kro.hereinkorea.global.jwt.properties.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;


@RestController
@RequestMapping("/user")
public class MemberController {
    @Autowired
    private  MemberService memberService;

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registerauth")
    public ResponseEntity<String> registerMember(@RequestBody MemberDTO memberDTO) {
        try {
            MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

            // 서비스 로직을 통해 저장
            memberService.joinMember(memberEntity);

            // 성공 응답 반환
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            // 예외 발생 시
            // 다른 예외 처리 추후 처리 예정, 지금은 오류 메세지만 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginMember(@RequestBody MemberDTO memberDTO){
        try {
            logger.info("로그인 요청: {}", memberDTO);
            // 사용자 이름을 기반으로 데이터베이스에서 회원 조회
            MemberEntity memberEntity = memberService.findByMemId(memberDTO.getMemId());

            // 사용자 존재 여부와 비밀번호 일치 여부를 확인
            if (memberEntity != null && memberEntity.getMemPass().equals(memberDTO.getMemPass())) {
                // 로그인 성공 시 JWT 토큰 생성
                String accessToken = jwtUtil.generateAccessToken(memberDTO.getMemId());
                String refreshToken = jwtUtil.generateRefreshToken(memberDTO.getMemId());

                logger.info("로그인 성공, 토큰 발급: {}", memberDTO.getMemId());

                // 생성된 JWT 토큰을 클라이언트에 반환 (Json 형식)
                return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
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

    @PostMapping("/find/id/request")
    public ResponseEntity<?> findMemberId(@RequestBody MemberDTO memberDTO) {
        // 이름과 이메일로 아이디 찾기
        String memId = memberService.findMemberId(memberDTO.getMemName(), memberDTO.getEmail());

        if (memId != null) {
            // 아이디가 존재하면 아이디만 반환
            return ResponseEntity.ok(Collections.singletonMap("id", memId));
        } else {
            // 아이디가 존재하지 않으면 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 아이디입니다.");
        }
    }

}
