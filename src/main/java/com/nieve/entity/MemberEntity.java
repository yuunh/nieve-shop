package com.nieve.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "member")
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime enrollDate;
}
