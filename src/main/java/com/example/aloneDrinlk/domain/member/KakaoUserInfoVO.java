package com.example.aloneDrinlk.domain.member;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
public class KakaoUserInfoVO {

    private String    custNo;             // 고객번호
    private String    custNm;			  // 고객명
    private String    custGnd;            // 고객성별
    private String    custCp;             // 고객전화번호
    private String    custId;             // 고객ID
    private String    custYmd;            // 고객생년월일
    private String    custAge;            // 고객나이
    private String    custAgeCd;          // 고객연령대코드
    private String    custEmail;          // 고객이메일
    private String    chnlClsf;           // 채널분류
    private Timestamp joinDtm;            // 가입일시
    private Timestamp wtdwDtm;            // 탈퇴일시
    private Timestamp lstLginDtm;         // 최종로그인일시
}
