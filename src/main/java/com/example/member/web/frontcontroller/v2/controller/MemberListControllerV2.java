package com.example.member.web.frontcontroller.v2.controller;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MemberListControllerV2 implements ControllerV2 {

    MemberRepository memberRepository= MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members=memberRepository.findAll();

        request.setAttribute("members",members); // key , value - Model에 담아줌.

        return new MyView("/WEB-INF/views/members.jsp");

    }
}
