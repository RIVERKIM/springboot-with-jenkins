package com.multimodule.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("멤버는 이름과 email을 갖으며 생성될 수 있다.")
    @Test
    void 멤버_생성_성공() {
        //given
        String name = "garam";
        String email = "kgr4163@korea.ac.kr";

        //when
        Member member = new Member(name, email);

        //then
        assertAll(
                () ->assertThat(member.getName()).isEqualTo(name),
                () -> assertThat(member.getEmail()).isEqualTo(email)
        );
    }
}