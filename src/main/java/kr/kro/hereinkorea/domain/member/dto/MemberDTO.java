package kr.kro.hereinkorea.domain.member.dto;

import kr.kro.hereinkorea.domain.member.Entity.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

@Builder
@Getter
@Setter
@Jacksonized
public class MemberDTO {
    private String memId;
    private String memName;
    private String memPass;
    private String gender;
    private String birth;
    private String email;
    private Date loginDate;
    private Date signDate; // 어드민 페이지에 필요하여 추가
    private MemberRole role;
}
