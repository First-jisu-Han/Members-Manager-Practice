package com.example.member.web.servletmvc;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet",urlPatterns ="/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath="/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher =request.getRequestDispatcher(viewPath); // Controller -> View 로 이동할때 사용

        dispatcher.forward(request,response);// 다른 서버나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시호출이 발생한다. 리다이렉트는 아니다
// View까지 가서 렌더링 되었지만,url인 /servlet-mvc/members/new-form을 유지한다.
// 클라이언트에서 서버로 호출 - 서버안에서 자체적으로 서블릿이 호출됐다가 jsp를 호출하고 jsp에서 응답을 만들어서 고객에게 보낸것. 메서드 호출하듯이 한번 호출된것이다.


    }
}
