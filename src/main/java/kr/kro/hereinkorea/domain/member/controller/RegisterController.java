package kr.kro.hereinkorea.domain.member.controller;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//경로 수정 예정
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class RegisterController {
    @Autowired
    private  MemberService memberService;

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

}
