package com.example.aloneDrinlk.domain.member;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserVO {


    private Long userNo;
    private String UserID;
    private String password;
    private UserInfoVO userInfoVO;


}
