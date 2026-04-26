package com.example.demo.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.dto.MemberSaveRequest;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequest requestDto){
        return memberRepository.save(requestDto.toEntity()).getId();
    }

    public MemberResponse findById(Long id){
        Member member = memberRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new MemberResponse(member);
    }
    
    @Transactional
    public Long update(Long id, MemberSaveRequest requestDto){
        Member member = memberRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        member.updateName(requestDto.name());
        return id;
    }
    
    @Transactional
    public void delete(Long id){
        Member member = memberRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        memberRepository.delete(member);
    }
}
