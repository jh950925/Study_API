package com.example.aloneDrinlk.serviceImpl.member;

import com.example.aloneDrinlk.domain.member.UserInfoVO;
import com.example.aloneDrinlk.domain.member.UserVO;
import com.example.aloneDrinlk.service.member.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserVO userVO;
    @Autowired
    private UserInfoVO userInfoVO;


    @Test
    @DisplayName("사용자 로그인 테스트")
    void loginMember() {
        userInfoVO.setUserCp("01041640337");
        userInfoVO.setUserName("김준형");
        userInfoVO.setUserEmail("jh950925@naver.com");
        userInfoVO.setUserGen("남");
        userVO.setUserID("jh950925");
        userVO.setPassword("1234");
        userVO.setUserInfoVO(userInfoVO);

        String id = "jh950925";
        String pass = "1234";

//        UserVO loginMember = userService.loginMember(id, pass);


    }


}

