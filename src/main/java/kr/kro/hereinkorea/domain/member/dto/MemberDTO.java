package kr.kro.hereinkorea.domain.member.dto;

import kr.kro.hereinkorea.domain.member.Entity.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class MemberDTO {
    private String memId;
    private String memName;
    private String memPass;
    private String gender;
    private String birth;
    private String email;
    private Date loginDate;
    private MemberRole role;
}
