package com.example.member.web.frontcontroller.v4;

import com.example.member.web.frontcontroller.ModelView;
import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v3.ControllerV3;
import com.example.member.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.member.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.member.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="FrontControllerServletV4",urlPatterns = "/front-controller/v4/*")  // /front-controller/v1 의 모든 하위 디렉토리들은 항상 이 서블릿을 호출하게 된다.
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap=new HashMap<>();   // URL 매핑정보를 담은 MAP , 이를 통해서 특정 요청에 대한 매핑되는 특정 컨트롤러를 호출가능

    public FrontControllerServletV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV4 controller=controllerMap.get(requestURI);  // 넘겨받은 uri 에 매핑되는 Controller 를 Map에서 찾아 그 객체를 controller 에 저장 ( ControllerV1 인터페이스로 타입을 정의해서 편리 )

        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 해당하는 uri 가 없다면 404에러 발생시킴
            return;
        }

        Map<String,String> paramMap = createParamMap(request); // 해당 HttpServletRequest에 있는 모든 parameter를 뽑아서 저장
        Map<String,Object> model= new HashMap<>(); // 비어있는 Map

        // 해당로직 실행하면 그 특정 컨트롤러에서 http 요청의 정보들을 받아서 처리하고, 이를 나중에 model에 넣고, 물리주소 return 한다.
        String viewName=controller.process(paramMap,model); // controller는 해당 매핑된 컨트롤러 , paramMap에는 (파라미터 이름 , 이름으로 찾은 결과)가 들어있다

        MyView view= viewResolver(viewName); // view에는 /WEB-INF/views/리턴되는 viewName.jsp 가 저장됨

        view.render(model,request,response);
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