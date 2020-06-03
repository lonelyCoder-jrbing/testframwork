package com.mybatisdemos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginPO {
    private String userLoginId;

    private Integer id;

    private String userName;

    private Integer userAge;

    private String userNickName;

    private String userCode;

    private String cipher;

    private Date loginTime;


}