package kr.kro.hereinkorea.domain.member.service;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.exception.NotFoundMemberException;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.Optional;

@Service
public class MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;
    
    public void joinMember(MemberEntity memberEntity){
        try {

            MemberEntity NewMembers = MemberMapper.joinMember(memberEntity);  //데이터 가공
            memberRepository.save(NewMembers);      //저장
        }catch(Exception e){
            //오류 발생시 처리를 위한 공간
            logger.error("오류 : "+ e.getMessage());  //오류 메세지 출력
        }

    }

    public MemberEntity findByMemId(String memId) {
        return memberRepository.findByMemId(memId).orElseThrow(() -> new NotFoundMemberException());
    }

    public String findMemberId(String memName, String email) {
        // 아이디 찾기
        return memberRepository.findMemberId(memName, email).orElse(null);  // 아이디가 존재하지 않으면 null 반환
    }
}
