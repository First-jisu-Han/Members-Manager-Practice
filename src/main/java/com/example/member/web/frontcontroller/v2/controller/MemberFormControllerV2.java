package com.example.member.web.frontcontroller.v2.controller;

import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        MyView mv= new MyView("/WEB-INF/views/new-form.jsp");
        return mv;

        코드를 아래와 같이 축약
         */

        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
