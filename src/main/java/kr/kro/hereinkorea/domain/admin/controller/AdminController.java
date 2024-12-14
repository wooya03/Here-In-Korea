package kr.kro.hereinkorea.domain.admin.controller;

import kr.kro.hereinkorea.domain.admin.service.AdminMemberService;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminMemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<PageResultDTO<MemberDTO, MemberEntity>> getMembers(@RequestParam(value = "memName", required = false) String memName, PageRequestDTO pageRequestDTO){
        PageResultDTO<MemberDTO, MemberEntity> memberList = memberService.getMember(memName, pageRequestDTO);

        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/main")
    public long getMemberCount(){
        return memberService.getTotalMemberCount();
    }
}
