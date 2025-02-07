package org.example.kotlindemo.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.example.kotlindemo.utility.logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController {

//    @GetMapping()
//    fun home(text: String): String {
//        text = "Hello, World!"
//        return text;
//    }


    @Operation(
        summary = "회원 생성",
        description = "회원을 생성하고 회원 id를 반환합니다.",
        responses = [
            ApiResponse(responseCode = "200", description = "성공")
        ]
    )
    @PostMapping()
    fun createMember(
        @RequestParam(value = "name", defaultValue = "Kim")
        @Schema(description = "사용자 이름", example = "Hong")
        name: String
    ): String {
        val memberId: Int = 1;
        logger.info { "Member created with id: $memberId" }
        return "Member created"
    }
}