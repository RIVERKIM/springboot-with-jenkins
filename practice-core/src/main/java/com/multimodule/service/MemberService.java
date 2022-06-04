package com.multimodule.service;

import com.multimodule.domain.Member;
import com.multimodule.domain.repository.MemberRepository;
import com.multimodule.service.dto.MemberCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(MemberCreateRequestDto requestDto) {
        Member member = memberRepository.save(requestDto.toEntity());
        return member.getId();
    }
}
