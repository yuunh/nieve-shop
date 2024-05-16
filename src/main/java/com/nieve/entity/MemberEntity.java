package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Entity(name = "member")
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String adCheck;
    private String memState;
    private Date enrollDate;
}
