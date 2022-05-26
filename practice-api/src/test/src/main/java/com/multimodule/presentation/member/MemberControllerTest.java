package com.multimodule.presentation.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multimodule.service.MemberService;
import com.multimodule.service.dto.MemberCreateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @BeforeEach
    void init(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("utf-8", true))
                .build();

        objectMapper = new ObjectMapper();
    }

    @DisplayName("멤버 생성 요청을 처리한다.")
    @Test
    void 멤버_생성_요청_성공() throws Exception{
        //given
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto("garam", "kgr4163@korea.ac.kr");
        when(memberService.createMember(any())).thenReturn(1L);

        //when then
        mockMvc.perform(post("/api/members")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated());
    }

}