package com.example.member.web.frontcontroller.v1.controller;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import com.example.member.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        int age=Integer.parseInt(request.getParameter("age"));

        Member member=new Member(username,age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        request.setAttribute("member",member); // request객체에 내부저장소 존재
        String viewPath="/WEB-INF/views/save-result.jsp";
        RequestDispatcher requestDispatcher =request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request,response);
    }
}
