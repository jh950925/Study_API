package com.example.aloneDrinlk.controller.member;

import com.example.aloneDrinlk.domain.member.UserVO;
import com.example.aloneDrinlk.service.member.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final UserService userService;

    @GetMapping("/test")
    public String test(){
        log.info("MemberController.test");
        log.info("테스트");
        return "ok";
    }

    @GetMapping("/login")
    public UserVO login() throws Exception {

        return null;
    }

}
