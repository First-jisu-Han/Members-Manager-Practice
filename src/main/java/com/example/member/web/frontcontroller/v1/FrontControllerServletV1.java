package com.example.member.web.frontcontroller.v1;

import com.example.member.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.member.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.member.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name="FrontControllerServletV1",urlPatterns = "/front-controller/v1/*")  // /front-controller/v1 의 모든 하위 디렉토리들은 항상 이 서블릿을 호출하게 된다.
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String,ControllerV1> controllerMap=new HashMap<>();   // URL 매핑정보를 담은 MAP , 이를 통해서 특정 요청에 대한 매핑되는 특정 컨트롤러를 호출가능

    public FrontControllerServletV1(){
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.class");

        String requestURI = request.getRequestURI();    // HTTP 요청에대한 uri 정보를 넘겨받고

        ControllerV1 controller=controllerMap.get(requestURI);  // 넘겨받은 uri 에 매핑되는 Controller 를 Map에서 찾아 그 객체를 controller 에 저장 ( ControllerV1 인터페이스로 타입을 정의해서 편리 )

        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 해당하는 uri 가 없다면 404에러 발생시킴
            return;
         }

        controller.process(request,response); // 해당 컨트롤러의 로직을 실행시키고, 나중에 View 를 내려준다.

    }
}
