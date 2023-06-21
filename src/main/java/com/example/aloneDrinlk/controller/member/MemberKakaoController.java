package com.example.aloneDrinlk.controller.member;

import com.example.aloneDrinlk.domain.member.KakaoUserVO;
import com.example.aloneDrinlk.service.member.UserKakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/kakao")
public class MemberKakaoController {

    @Autowired
    private Environment env;

    private final UserKakaoService userKakaoService;
    private KakaoUserVO kakaoUserVO;

    /**
     * 카카오 로그인
     */
    @GetMapping("/login")
    public RedirectView kakaoLogin() {

        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        log.info(methodName);

        String kakaoLoginUrl = env.getProperty("spring.security.oauth2.client.provider.kakao.authorization-uri")
                + "?response_type=code"
                + "&client_id=" + env.getProperty("spring.security.oauth2.client.registration.kakao.client-id")
                + "&redirect_uri=" + env.getProperty("spring.security.oauth2.client.registration.kakao.redirect-uri");

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(kakaoLoginUrl);

        log.info(redirectView.getUrl());

        return redirectView;
    }

    /**
     *  카카오 로그인 CODE 발급
     */
    @GetMapping("/callback")
    public ModelAndView kakaoLoginCallback(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        log.info(methodName);

        userKakaoService.kakaoLogin(code,request,response);

        ModelAndView modelAndView = new ModelAndView("main");

        return modelAndView;
    }

    //TODO 포스트맨 body값 확인 해서 재 실행
    @GetMapping("/messageMe")
    public String kakaoMessageMe() throws Exception {
        log.info("kakaoAccessToken : " + kakaoUserVO.getAccessToken());

        userKakaoService.kakaoMessageMe(kakaoUserVO.getAccessToken());

        return "ok";
    }

}
