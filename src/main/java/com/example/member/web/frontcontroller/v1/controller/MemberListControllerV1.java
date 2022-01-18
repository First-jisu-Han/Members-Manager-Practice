package com.example.member.web.frontcontroller.v1.controller;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import com.example.member.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members=memberRepository.findAll();

        request.setAttribute("members",members); // key , value - Model에 담아줌.

        String viewPath="/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher =request.getRequestDispatcher(viewPath); // Controller -> View 로 이동할때 사용
        dispatcher.forward(request,response);
    }
}
