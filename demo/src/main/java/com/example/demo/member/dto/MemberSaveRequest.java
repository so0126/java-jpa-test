package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberSaveRequest {
    private String name;

    @Builder
    public MemberSaveRequest(String name){
        this.name = name;
    }
    public Member toEntity(){
        return Member.builder()
            .name(name)
            .build();
    }

}
