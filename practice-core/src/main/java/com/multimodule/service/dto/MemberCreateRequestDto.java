package com.multimodule.service.dto;

import com.multimodule.domain.Member;
import lombok.Getter;

@Getter
public class MemberCreateRequestDto {

    private final String name;
    private final String email;

    public MemberCreateRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member toEntity() {
        return new Member(name, email);
    }
}
