package com.nieve.service;

import com.nieve.entity.MemberEntity;
import com.nieve.model.Member;
import com.nieve.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void memberInsert(Member member) {

        MemberEntity me = MemberEntity.builder()
                .memEmail(member.getMemEmail())
                .memPwd(member.getMemPwd())
                .memName(member.getMemName())
                .phone(member.getPhone())
                .address1(member.getAddress1())
                .postNo(member.getPostNo())
                .build();
        memberRepository.save(me);
    }

    public List<Member> getMemberList() {

        List<MemberEntity> memberList = memberRepository.findAll();

        List<Member> members = new ArrayList<>();
        for (MemberEntity me : memberList) {
            Member m = Member.builder()
                    .memEmail(me.getMemEmail())
                    .memPwd(me.getMemPwd())
                    .memName(me.getMemName())
                    .phone(me.getPhone())
                    .address1(me.getAddress1())
                    .build();
            members.add(m);
        }

        return members;
    }

    public void updateMember(Member member) {

        MemberEntity me = memberRepository.findById(member.getMemNo()).orElseThrow();

        me.setMemEmail(member.getMemEmail());
        me.setMemPwd(member.getMemPwd());
        me.setMemName(member.getMemName());
        me.setPhone(member.getPhone());
        me.setPostNo(member.getPostNo());
        me.setAddress1(member.getAddress1());
        me.setAddress2(member.getAddress2());

        memberRepository.save(me);
    }

    public Member getMember(int memNo) {

        MemberEntity me = memberRepository.findById(memNo).orElseThrow();

        Member m = Member.builder()
                .memNo(me.getMemNo())
                .memEmail(me.getMemEmail())
                .memPwd(me.getMemPwd())
                .memName(me.getMemName())
                .phone(me.getPhone())
                .postNo(me.getPostNo())
                .address1(me.getAddress1())
                .address2(me.getAddress2())
                .build();
        return m;
    }

}