package com.example.member.web.servletmvc;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="mvcMemberListServlet",urlPatterns ="/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members=memberRepository.findAll();

        request.setAttribute("members",members); // key , value - Model에 담아줌.

        String viewPath="/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher =request.getRequestDispatcher(viewPath); // Controller -> View 로 이동할때 사용
        dispatcher.forward(request,response);
    }
}
