package com.example.aloneDrinlk.service.member;

import com.example.aloneDrinlk.domain.member.UserInfoVO;
import com.example.aloneDrinlk.domain.member.UserVO;

public interface UserService {

    // 회원 로그인
    public UserVO loginMember(UserInfoVO userInfoVO) throws Exception;

    // 회원가입
    public int createMember() throws Exception;

    // ID찾기
    public String findMemberId(UserInfoVO userInfoVO) throws Exception;

    // 비밀번호 찾기
    public String findMemberPassword(UserVO userVO) throws Exception;

    // 로그아웃
    public int logoutMember(UserVO userVO) throws Exception;

    // 회원 탈퇴
    public int deleteMember() throws Exception;

}
