package com.nieve.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member {

    private Integer memNo;
    private String memName;
    private String memEmail;
    private String memPwd;
    private String phone;
    private String address1;
    private String address2;
    private String postNo;
    private String adCheck;
    private String memState;
    private Date enrollDate;


}
