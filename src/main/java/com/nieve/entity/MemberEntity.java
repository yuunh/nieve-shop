package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "member")
@Data
@Getter
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memNo;
    private String memName;
    private String memEmail;
    private String memPwd;
    private String phone;
    private String address1;
    private String address2;
    private String postNo;
}
