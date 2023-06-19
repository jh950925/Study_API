package com.example.aloneDrinlk.domain.member;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class KakaoUserVO {

    private String          accessToken;        // access 토큰
    private String          refresh_token;      // refresh 토큰
    private String          expires_in;         // 토큰 발급 시간
    private KakaoUserInfoVO kakaoUserInfoVO;    // 카카오 유저정보

}
