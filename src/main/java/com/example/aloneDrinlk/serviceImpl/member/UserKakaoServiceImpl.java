package com.example.aloneDrinlk.serviceImpl.member;

import com.example.aloneDrinlk.domain.member.KakaoUserInfoVO;
import com.example.aloneDrinlk.domain.member.KakaoUserVO;
import com.example.aloneDrinlk.service.member.UserKakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class UserKakaoServiceImpl implements UserKakaoService {

    @Autowired
    private Environment env;



    /**
     * 카카오 로그인 API
     * @param code
     * @param request
     * @param response
     * @return UserKakaoVO
     * @throws Exception
     */
    @Override
    public KakaoUserVO kakaoLogin(String code, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        log.info(methodName);

        KakaoUserInfoVO kakaoUserInfoVO = new KakaoUserInfoVO();
        KakaoUserVO kakaoUserVO = new KakaoUserVO();

        // 1. 코드로 토큰 받아오기
        log.info("받아온 code : " + code);
        String accessToken = this.getKakaoToken(code);

        // 2. 토큰으로 사용자 정보 요청
        log.info("받아온 accessToken : " + accessToken);
        kakaoUserInfoVO = this.getKakaoUserInfo(accessToken);
        kakaoUserVO.setKakaoUserInfoVO(kakaoUserInfoVO);
        kakaoUserVO.setAccessToken(accessToken);

        log.info("service 최종================================");
        log.info("받아왔던 code : " + code);
        log.info("받아왔던 accessToken : " + accessToken);
        log.info("받아왔던 kakaoUserInfoVO : " + kakaoUserInfoVO);
        log.info("받아왔던 kakaoUserVO : " + kakaoUserVO);

        return kakaoUserVO;
    }

    /**
     * 카카오 토큰 발급 API
     * @param code
     * @return String accessToken
     * @throws Exception
     */
    @Override
    public String getKakaoToken(String code) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String accessTokenUrl = env.getProperty("spring.security.oauth2.client.provider.kakao.token-uri")
                + "?grant_type="
                + env.getProperty("spring.security.oauth2.client.registration.kakao.authorization-grant-type")
                + "&client_id="
                + env.getProperty("spring.security.oauth2.client.registration.kakao.client-id")
                + "&redirect_url="
                + env.getProperty("spring.security.oauth2.client.registration.kakao.redirect-uri")
                + "&code=" + code;

        HttpEntity<String> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, httpEntity, String.class);

        String responseBody = response.getBody();

        //ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        //JSON 파싱
        Map<String, Object> parsedBody = objectMapper.readValue(responseBody, Map.class);
        log.info("parsedBody : " + parsedBody);

        // 발급받은 access_token
        String accessToken = parsedBody.get("access_token").toString();
        String refreshToken = parsedBody.get("refresh_token").toString();
        String exprires_in = parsedBody.get("expires_in").toString();

        return accessToken;
    }

    /**
     * 카카오 유저정보 API
     * @param accessToken
     * @return KakaoUserInfoVO
     * @throws Exception
     */
    @Override
    public KakaoUserInfoVO getKakaoUserInfo(String accessToken) throws Exception {
        KakaoUserInfoVO kakaoUserInfoVO = new KakaoUserInfoVO();
        KakaoUserVO kakaoUserVO = new KakaoUserVO();

        log.info("사용자 정보를 위해서 받아온 accessToken : " + accessToken);

        RestTemplate restTemplate = new RestTemplate();
        String getKakaoUserInfoUrl = env.getProperty("spring.security.oauth2.client.provider.kakao.user-info-uri");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> httpEntity = new HttpEntity<>(null,headers);

        ResponseEntity<Map> response = restTemplate.exchange(getKakaoUserInfoUrl, HttpMethod.POST, httpEntity, Map.class);
        Map<String,Object> kakaoAccount = (Map<String, Object>) response.getBody().get("kakao_account");

        log.info("받아온 account 정보 : " + kakaoAccount);
        Map<String,Object> kakaoProfile = (Map<String,Object>) kakaoAccount.get("profile");
        log.info("받아온 Profile 정보 : " + kakaoProfile);

        kakaoUserInfoVO.setCustNm(kakaoProfile.get("nickname").toString());
        kakaoUserInfoVO.setCustEmail(kakaoAccount.get("email").toString());
        kakaoUserInfoVO.setCustGnd(kakaoAccount.get("gender").toString());
        kakaoUserInfoVO.setCustNo(response.getBody().get("id").toString());

        kakaoUserVO.setKakaoUserInfoVO(kakaoUserInfoVO);

        return kakaoUserInfoVO;
    }

    /**
     * 카카오 리프레시 토큰 재발급 API
     * @param refreshToken
     * @return String refreshToken
     * @throws Exception
     */
    @Override
    public String getRefreshToken(String refreshToken) throws Exception {
        return null;
    }

    /**
     * 카카오 로그아웃 API
     * @param kakaoUserVO
     * @return
     */
    @Override
    public int kakaoLogout(KakaoUserVO kakaoUserVO) throws JsonProcessingException {

        String accessToken = kakaoUserVO.getAccessToken();

        String logoutKakaoUrl = "https://kapi.kakao.com/v1/user/logout";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Authorization","Bearer " + kakaoUserVO.getAccessToken());

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(logoutKakaoUrl, HttpMethod.POST, httpEntity, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedBody = objectMapper.readValue(responseBody, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("로그아웃 성공");
        } else {
            log.info("로그아웃 실패");
        }
        return 1;
    }

    /**
     * 카카오톡 나에게 메세지 보내기
     * @param kakaoUserVO
     */
    @Override
    public void kakaoMessageMe(String accessToken, String gitMessage) throws Exception{
        log.info("메시지 보내기 위한 토큰 : " + accessToken);

        String messageUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        // RestTempalate 선언
        RestTemplate restTemplate = new RestTemplate();

        // headers 선언
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        // JSONOjbect로 body에 담을 내용 정리
        JSONObject templateObject = new JSONObject();
        JSONObject link = new JSONObject();
        templateObject.put("object_type", "text");
        templateObject.put("text", gitMessage);
        link.put("web_url", "");
        templateObject.put("link", link);

        // JSONObject내용 String 형식으로 변환
        String templateObjectJson = templateObject.toString();

        // String 형식 데이터 bodu에 추가
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("template_object", templateObjectJson);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(messageUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("메시지 전송 성공");
        } else {
            System.out.println("메시지 전송 실패");
        }
    }

}
