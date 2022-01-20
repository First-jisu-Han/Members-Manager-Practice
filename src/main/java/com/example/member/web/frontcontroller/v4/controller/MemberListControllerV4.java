package com.example.member.web.frontcontroller.v4.controller;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import com.example.member.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap,Map<String,Object>model) {
        List<Member> members=memberRepository.findAll();
        model.put("members",members);

        return "members";
    }
}
