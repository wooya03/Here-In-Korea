package kr.kro.hereinkorea.global.security;

import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.domain.member.exception.NotFoundMemberException;
import kr.kro.hereinkorea.domain.member.mapper.MemberMapper;
import kr.kro.hereinkorea.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberRepository.findByMemId(memId)
                .map(MemberMapper::createDTO)
                .orElseThrow(() -> NotFoundMemberException.EXCEPTION);
        return CustomUserDetails.create(memberDTO);
    }
}
