package com.example.member.web.frontcontroller.v2.controller;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String username=request.getParameter("username");
            int age=Integer.parseInt(request.getParameter("age"));

            Member member=new Member(username,age);
            memberRepository.save(member);

            //Model에 데이터를 보관한다.
            request.setAttribute("member",member); // request객체에 내부저장소 존재
//          MyView mv=new MyView("/WEB-INF/views/save-result.jsp");
//          return mv;
//
          return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
