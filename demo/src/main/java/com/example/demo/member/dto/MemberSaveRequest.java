package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberSaveRequest(
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min =2, message = "이름은 최소 2자 이상이어야 합니다.")
    String name
){
    public Member toEntity(){
        return Member.builder().name(name).build();
    }
}