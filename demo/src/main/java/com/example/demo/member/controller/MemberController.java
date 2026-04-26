package com.example.demo.member.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.dto.MemberResponse;
import com.example.demo.member.dto.MemberSaveRequest;
import com.example.demo.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public Long save(@RequestBody MemberSaveRequest requestDto){
        return memberService.save(requestDto);
    }

    @GetMapping("/{id}")
    public MemberResponse findById(@PathVariable("id") Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody MemberSaveRequest requestDto) {
        return memberService.update(id,requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        memberService.delete(id);
    }
    

    
}
