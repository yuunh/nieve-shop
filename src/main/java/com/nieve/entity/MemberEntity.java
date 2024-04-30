package com.nieve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MemberEntity {

    @Id
    private int memNo;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address1;
    private String address2;
    private String postNo;
}
