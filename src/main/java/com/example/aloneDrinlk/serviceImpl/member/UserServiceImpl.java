package com.example.aloneDrinlk.serviceImpl.member;

import com.example.aloneDrinlk.domain.member.UserInfoVO;
import com.example.aloneDrinlk.domain.member.UserVO;
import com.example.aloneDrinlk.service.member.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserVO loginMember(UserInfoVO userInfoVO) {
        UserVO userVO = new UserVO();

        return userVO;
    }

    @Override
    public int createMember() throws Exception {
        return 0;
    }

    @Override
    public String findMemberId(UserInfoVO userInfoVO) throws Exception {
        return null;
    }

    @Override
    public String findMemberPassword(UserVO userVO) throws Exception {
        return null;
    }

    @Override
    public int logoutMember(UserVO userVO) throws Exception {
        return 0;
    }

    @Override
    public int deleteMember() throws Exception {
        return 0;
    }


}
