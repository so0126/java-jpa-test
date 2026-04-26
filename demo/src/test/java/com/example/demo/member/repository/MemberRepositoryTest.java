package com.example.demo.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.member.entity.Member;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 정보가 성공적으로 저장되어야 한다")
    void saveMemberTest(){
        // given (준비)
        Member member = Member.builder()
            .name("자바연습")
            .build();
        // when (실행)
        Member savedMember = memberRepository.save(member);

        // then (검증)
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getName()).isEqualTo("자바연습");
        assertThat(savedMember.getId()).isGreaterThan(0L);
    }

    @Test
    @DisplayName("ID로 특정 회원을 조회할 수 있어야 한다")
    void fineMemberByIdTest(){
        // 준비
        Member member = Member.builder().name("조회테스트").build();
        Member savedMember = memberRepository.save(member);

        // 실행
        Member foundMember= memberRepository.findById(savedMember.getId())
            .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다."));
        
        // 검증
        assertThat(foundMember.getId()).isEqualTo(savedMember.getId());
        assertThat(foundMember.getName()).isEqualTo("조회테스트");

    }

    @Test
    @DisplayName("전체 회원 목록을 조회할 수 있어야 한다")
    void findAllMemberTest(){
        // 준비
        memberRepository.save(Member.builder().name("사용자1").build());
        memberRepository.save(Member.builder().name("사용자2").build());

        // 실행
        java.util.List<Member> members = memberRepository.findAll();

        // 검증
        assertThat(members).hasSize(2);
        assertThat(members).extracting("name")
            .containsExactly("사용자1","사용자2");
    }

    @Test
    @DisplayName("")
    void updateMemberByIdTest(){
        // 준비, 수정 대상 먼저 저장
        Member member = Member.builder().name("옛날이름").build();
        Member savedMember = memberRepository.save(member);

        // 실행, 데이터를 조회해와서 값 변경
        Member foundMember = memberRepository.findById(savedMember.getId())
            .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다."));
    
        foundMember.updateName("새이름");

        // JPA는 트랜잭션이 끝날 때 변경을 감지해 자동으로 UPDATE 날림 (더티 체킹)
        // 테스트 환경에서 확실한 확인을 위해 강제로 DB에 반영(flush)
        memberRepository.saveAndFlush(foundMember);

        // 검증
        // .orElseThorw()는 찾으려는 데이터가 DB에 없을 때의 상황을 매듭지으려고
        Member updatedMember = memberRepository.findById(savedMember.getId()).orElseThrow();
        assertThat(updatedMember.getName()).isEqualTo("새이름");

    }

    @Test
    @DisplayName("회원 정보를 삭제하면 DB에서 더 이상 조회되지 않아야 한다")
    void deleteMemberByIdTest(){
        Member member = Member.builder().name("삭제예정멤버").build();
        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        memberRepository.deleteById(memberId);

        memberRepository.flush();

        // findById 결과가 비었는지 확인
        java.util.Optional<Member> foundMember = memberRepository.findById(memberId);
        assertThat(foundMember).isEmpty();

        // existsById가 false인지 확인
        boolean exists = memberRepository.existsById(memberId);
        assertThat(exists).isFalse();

    }
}
