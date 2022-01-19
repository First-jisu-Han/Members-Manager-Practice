package com.example.member.web.frontcontroller.v3;

import com.example.member.web.frontcontroller.ModelView;
import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.member.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.member.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="FrontControllerServletV3",urlPatterns = "/front-controller/v3/*")  // /front-controller/v1 의 모든 하위 디렉토리들은 항상 이 서블릿을 호출하게 된다.
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap=new HashMap<>();   // URL 매핑정보를 담은 MAP , 이를 통해서 특정 요청에 대한 매핑되는 특정 컨트롤러를 호출가능

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller=controllerMap.get(requestURI);  // 넘겨받은 uri 에 매핑되는 Controller 를 Map에서 찾아 그 객체를 controller 에 저장 ( ControllerV1 인터페이스로 타입을 정의해서 편리 )

        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 해당하는 uri 가 없다면 404에러 발생시킴
            return;
        }

        /*
        Map<String,String> paramMap= new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName,request.getParameter(paramName)));
         */
        Map<String, String> paramMap = createParamMap(request);   // HttpServletRequest에 있는 모든 parameter를 뽑아서 저장

        ModelView mv=controller.process(paramMap); // 해당 컨트롤러의 로직을 실행

        String viewName=mv.getViewName();  //논리이름인 new-form 같은 것들이 저장됨
        //"WEB-INF/views/ ..." 호출될 것이다.
        MyView view= viewResolver(viewName);  // viewResolver 에서 url에 대한 처리를 해준다. 논리이름만 viewName에 넣어주면, 전체 url을 붙여서 view에 넘겨준다.

        view.render(mv.getModel(),request,response); // render 호출하면서 model을 넘겨줌

    }


    // HttpServletRequest에 있는 모든 parameter를 뽑아서 저장
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap= new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
    // viewResolver 에서 url에 대한 처리를 해준다. 논리이름만 viewName에 넣어주면, 전체 url을 붙여서 view에 넘겨준다.
    private MyView viewResolver(String viewName){
        return new MyView("/WEB-INF/views/"+ viewName + ".jsp");
    }
}