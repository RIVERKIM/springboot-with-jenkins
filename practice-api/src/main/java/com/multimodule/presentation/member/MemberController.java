package com.multimodule.presentation.member;

import com.multimodule.service.MemberService;
import com.multimodule.service.dto.MemberCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    ResponseEntity<Void> createMember(MemberCreateRequestDto requestDto) {
        Long createdMemberId = memberService.createMember(requestDto);
        return ResponseEntity.created(URI.create("/api/members" + "/" + createdMemberId)).build();
    }
}
