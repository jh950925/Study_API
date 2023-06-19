package com.example.aloneDrinlk.domain.member;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserInfoVO {

    private String userName;    // 사용자명
    private String userCp;      // 사용자 전화번호
    private String userEmail;   // 사용자 이메일
    private String userGen;     // 사용자 성별

}
