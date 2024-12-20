package kr.kro.hereinkorea.domain.admin.service;

import kr.kro.hereinkorea.domain.admin.repository.AdminMemberRepository;
import kr.kro.hereinkorea.domain.member.Entity.MemberEntity;
import kr.kro.hereinkorea.domain.member.dto.MemberDTO;
import kr.kro.hereinkorea.global.common.dto.PageRequestDTO;
import kr.kro.hereinkorea.global.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    private final AdminMemberRepository memberRepository;

    public PageResultDTO<MemberDTO, MemberEntity> getMember(String memName, String gender, PageRequestDTO pageRequestDTO) {
        Sort sort = Sort.by("memId").descending();
        Pageable pageable = pageRequestDTO.getPageable(sort);

        Page<MemberEntity> result;

        if (memName == null || memName.trim().isEmpty()) {
            if (gender == null || gender.isEmpty()) {
                result = memberRepository.findAll(pageable);
            } else {
                result = memberRepository.findByGender(gender, pageable);
            }
        } else {
            if (gender == null || gender.isEmpty()) {
                result = memberRepository.findByMemNameContaining(memName, pageable);
            } else {
                result = memberRepository.findByMemNameContainingAndGender(memName, gender, pageable);
            }
        }

        Function<MemberEntity, MemberDTO> fn = this::entityToDTO;
        return new PageResultDTO<>(result, fn);
    }

    private MemberDTO entityToDTO(MemberEntity entity) {
        return MemberDTO.builder()
                .memId(entity.getMemId())
                .memName(entity.getMemName())
                .email(entity.getEmail())
                .gender(entity.getGender())
                .birth(entity.getBirth())
                .loginDate(entity.getLoginDate())
                .signDate(entity.getSignDate())
                .build();
    }

    public long getTotalMemberCount() {
        return memberRepository.count();
    }
}

