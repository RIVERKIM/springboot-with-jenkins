package com.multimodule.service;

import com.multimodule.domain.Member;
import com.multimodule.domain.repository.MemberRepository;
import com.multimodule.service.dto.MemberCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @DisplayName("유저를 생성할 수 있다.")
    @Test
    void 유저_생성() {
        //given
        Member member = new Member(1L, "garam", "kgr4163@korea.ac.kr");
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto(member.getName(), member.getEmail());
        when(memberRepository.save(any())).thenReturn(member);

        //when
        Long id = memberService.createMember(requestDto);

        //then
        assertThat(id).isNotNull();
    }
}