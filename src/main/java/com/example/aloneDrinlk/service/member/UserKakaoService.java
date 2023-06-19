package com.example.aloneDrinlk.service.member;

import com.example.aloneDrinlk.domain.member.KakaoUserInfoVO;
import com.example.aloneDrinlk.domain.member.KakaoUserVO;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserKakaoService {

    // 카카오 로그인
    public KakaoUserVO kakaoLogin(String code, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 카카오 토큰 요청
    public String getKakaoToken(String code) throws Exception;

    // 카카오 사용자 정보 저장
    public KakaoUserInfoVO getKakaoUserInfo(String accessToken) throws Exception;

    // 릐프레시 토큰으로 Access-token 재발급
    public String getRefreshToken(String refreshToken) throws Exception;

    // 카카오 로그아웃
    public int kakaoLogout(KakaoUserVO kakaoUserVO) throws JsonProcessingException;
}
