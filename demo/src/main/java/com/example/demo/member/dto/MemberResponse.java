
package com.example.demo.member.dto;

import com.example.demo.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberResponse {
    private Long id;
    private String name;

    // 엔티티를 DTO로 변환하는 생성자
    public MemberResponse(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}