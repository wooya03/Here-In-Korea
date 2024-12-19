package kr.kro.hereinkorea.domain.member.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import kr.kro.hereinkorea.global.jwt.enums.JwtType;
import kr.kro.hereinkorea.global.jwt.properties.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;


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

    @PostMapping("/find/pw/request")
    public ResponseEntity<?> findMemberPassCheck(@RequestBody MemberDTO memberDTO) {
        // 아이디와 이름이 모두 입력되었는지 확인
        if (memberDTO.getMemId() == null || memberDTO.getMemName() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("아이디와 이름을 모두 입력해 주세요.");
        }

        // 회원 정보를 조회
        Optional<MemberEntity> member = memberService.findByIdAndName(memberDTO.getMemId(), memberDTO.getMemName());

        if (member.isPresent()) {
            // 회원이 존재하는 경우, 인증 코드 발송 로직 추가
//            String verificationCode = generateVerificationCode(); // 인증 코드 생성
//            memberService.sendVerificationCode(member.get(), verificationCode); // 인증 코드 발송
//
            return ResponseEntity.ok("인증 코드가 발송되었습니다.");
        } else {
            // 아이디 또는 이름이 일치하지 않는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디 또는 이름이 잘못되었습니다.");
        }
    }

    @PostMapping("/loginck")
    public ResponseEntity<?> loginCheck(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Authorization 헤더에서 토큰 추출
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Authorization header is missing or invalid.");
            }

            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰

            try {
                // Access Token 검증
                Jws<Claims> claims = jwtUtil.getClaims(accessToken);

                // Access Token이 유효하면 사용자 정보 반환
                String memId = claims.getBody().getSubject();
                return ResponseEntity.ok(Collections.singletonMap("message", "Token is valid. User: " + memId));
            } catch (ExpiredJwtException e) {
                // Access Token이 만료된 경우, Refresh Token 검증 및 새로운 Access Token 발급
                String refreshToken = authorizationHeader.substring(7); // 클라이언트가 Refresh Token도 함께 제공한다고 가정

                try {
                    Jws<Claims> refreshClaims = jwtUtil.getClaims(refreshToken);

                    // Refresh Token 검증 및 새로운 Access Token 발급
                    if (jwtUtil.isWrongType(refreshClaims, JwtType.REFRESH)) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body("Invalid Refresh Token type.");
                    }

                    String memId = refreshClaims.getBody().getSubject();
                    String newAccessToken = jwtUtil.generateAccessToken(memId);

                    // 새로운 Access Token 반환
                    return ResponseEntity.ok(Collections.singletonMap("accessToken", newAccessToken));
                } catch (JwtException refreshEx) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("Refresh Token is invalid or expired.");
                }
            } catch (JwtException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Access Token is invalid or expired.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while checking login status.");
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Authorization 헤더에서 토큰 추출
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Authorization header is missing or invalid.");
            }

            String accessToken = authorizationHeader.substring(7); // "Bearer " 이후의 토큰

            try {
                // Access Token 검증
                Jws<Claims> claims = jwtUtil.getClaims(accessToken);

                // Access Token이 유효하면 사용자 정보 반환
                String memId = claims.getBody().getSubject();

                // 사용자 정보 조회
                Optional<MemberEntity> memberEntity = memberService.findForProfileMemId(memId);

                if (memberEntity.isPresent()) {
                    // 사용자 정보가 존재하면 반환
                    MemberDTO memberDTO = MemberMapper.toDTO(memberEntity.get());
                    return ResponseEntity.ok(memberDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
                }
            } catch (ExpiredJwtException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Access Token has expired.");
            } catch (JwtException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Access Token is invalid.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching user profile.");
        }
    }

}
