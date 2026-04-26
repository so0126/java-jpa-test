package com.example.demo.member.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.dto.MemberSaveRequest;
import com.example.demo.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Test
    @DisplayName("회원 단건 조회가 정상적으로 호출 되어야 한다.")
    void getMemberTest() throws Exception {
        Long memberId = 1L;
        given(memberService.findById(memberId))
                .willReturn(new MemberResponse(memberId, "자바연습"));

        mockMvc.perform(get("/api/v1/members/" + memberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("자바연습"));
    }


    @Test
    @DisplayName("회원 수정 API가 정상 호출되어야 한다")
    void updateMemberTest() throws Exception{
        Long memberId = 1L;
        MemberSaveRequest requestDto = MemberSaveRequest.builder()
            .name("수정된이름")
            .build();

        given(memberService.update(any(), any())).willReturn(memberId);

        mockMvc.perform(put("/api/v1/members/"+memberId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());
        
    }
    
    @Test
    @DisplayName("회원 삭제 API가 정상 호출되어야 한다")
    void deleteMemberTest() throws Exception {
        // 1. Given
        Long memberId = 1L;

        // 2. When & Then: DELETE 요청을 보내고 성공 확인
        mockMvc.perform(delete("/api/v1/members/" + memberId)) // DELETE 메서드 사용
                .andExpect(status().isOk()); // 성공 시 200 OK 확인
    }

}
