package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.MemberDto
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adm/members")
public class ApiV1AdmMemberController {

    @GetMapping("/me")
    fun items(): List<MemberDto>{
        return listOf(
            MemberDto(1L, "John"),
            MemberDto(2L, "John"),
            MemberDto(3L, "John")
        )
    }
}
