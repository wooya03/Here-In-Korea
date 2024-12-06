package kr.kro.hereinkorea.domain.member;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerAuth")
public class MemberController {
    @Autowired
    private  MemberService memberService;

//    @PostMapping
//    public String registerMameber(@RequestBody MemberEntity memberEntity){
//
//    }

}
