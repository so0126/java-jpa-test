package com.example.demo.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class MemberIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("실제 DB에 회원이 저장되고 조회되어야 한다")
    void saveAndFindTest(){
        String name = "진짜 테스트";

        Member member = Member.builder().name(name).build();

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        System.out.println("찾은 이름: "+findMember.getName());
        assertThat(findMember.getName()).isEqualTo(name);

    }

}
