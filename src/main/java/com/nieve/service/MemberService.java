package com.nieve.service;

import com.nieve.entity.MemberEntity;
import com.nieve.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired private MemberRepository memberRepository;

    public String saveMember(String email){
        MemberEntity entity = MemberEntity.builder()
                        .memName("hi").memEmail(email)
                                .phone("1234").address1("서울시").address2("개봉").postNo("1234").build();
        memberRepository.save(entity);
        return "hello";
    }
}