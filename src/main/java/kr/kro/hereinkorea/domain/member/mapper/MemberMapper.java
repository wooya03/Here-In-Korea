package kr.kro.hereinkorea.domain.member.mapper;

import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.Entity.enums.MemberRole;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;

import java.time.LocalDate;
import java.util.Date;

public class MemberMapper {
    public static MemberEntity createEntity(MemberDTO memberDTO){
        return   MemberEntity.builder()
                .memId(memberDTO.getMemId())
                .memPass(memberDTO.getMemPass())
                .memName(memberDTO.getMemName())
                .gender(memberDTO.getGender())
                .birth(memberDTO.getBirth())
                .email(memberDTO.getEmail())
                .role(memberDTO.getRole())
                .build();
    }


    public static MemberDTO createDTO(MemberEntity memberEntity){
        return   MemberDTO.builder()
                .memId(memberEntity.getMemId())
                .memPass(memberEntity.getMemPass())
                .memName(memberEntity.getMemName())
                .gender(memberEntity.getGender())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .role(memberEntity.getRole())
                .build();

    }

    public static void joinMember(MemberEntity memberEntity){
        memberEntity.setRole(MemberRole.USER);      //멤버 권한 : USER
        memberEntity.setSignDate(new Date());       //가입 날짜 현재 시간으로 설정
    }
}
