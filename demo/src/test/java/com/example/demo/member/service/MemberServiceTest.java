package com.example.demo.member.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.member.dto.MemberSaveRequest;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    // 객체 가짜로 생성
    @Mock
    private MemberRepository memberRepository;

    // 테스트 대상 객체 만들기
    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원 가입 서비스 로직이 정상 작동해야 한다")
    void saveMemberServiceTest(){
        MemberSaveRequest request = MemberSaveRequest.builder().name("서비스 테스트").build();
        Member member = request.toEntity();

        given(memberRepository.save(any())).willReturn(Member.builder().name("서비스 테스트").build());

        Long savedId = memberService.save(request);

        // 가짜 객체여서 DB 저장은 안되고 서비스 로직 흐름 검증 가능

    }
}
