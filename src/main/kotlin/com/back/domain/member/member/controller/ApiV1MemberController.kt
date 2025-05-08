package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.MemberDto
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

    @GetMapping("/me")
    fun me(): MemberDto{

        val memberDto = MemberDto(1L, "John")

        return memberDto
    }
}
