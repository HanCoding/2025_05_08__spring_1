package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.MemberDto
import com.back.domain.member.member.service.AuthTokenService
import com.back.domain.member.member.service.MemberService
import com.back.global.exception.ServiceException
import com.back.global.rq.Rq
import com.back.global.rsData.RsData
import com.back.standard.extensions.getOrThrow
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class ApiV1MemberController(
    private val memberService: MemberService,
    private val authTokenService: AuthTokenService,
    private val rq: Rq
) {

    @GetMapping("/me-temp")
    fun meTemp(): String {
        return "temp-string"
    }

    @GetMapping("/me")
    fun me(req: HttpServletRequest): RsData<MemberDto> {
        val member = rq.fulfilledMember

        return RsData(
            resultCode = "200-1",
            msg = "OK",
            data = MemberDto(member)
        )
    }

    class MemberLoginResBody(
        val item: MemberDto,
        val apiKey: String,
        val accessToken: String
    )

    class MemberLoginReqBody(
        @field:NotBlank
        val username: String,
        @field:NotBlank
        val password: String
    )

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid reqBody: MemberLoginReqBody
    ): RsData<MemberLoginResBody> {
        val member = memberService.findByUsername(reqBody.username)
            ?: throw ServiceException("400-1", "존재하지 않는 회원입니다.")

        if (member.password != reqBody.password) {
            throw ServiceException("400-2", "비밀번호가 일치하지 않습니다.")
        }
        val accessToken = rq.makeAuthCookies(member)

        return RsData(
            resultCode = "200-1",
            msg = "${member.nickname}님 환영합니다.",
            data = MemberLoginResBody(
                item = MemberDto(member),
                apiKey = member.apiKey,
                accessToken = accessToken
            )
        )
    }
}
