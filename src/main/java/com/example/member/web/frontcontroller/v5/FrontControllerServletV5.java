package com.example.member.web.frontcontroller.v5;

import com.example.member.web.frontcontroller.ModelView;
import com.example.member.web.frontcontroller.MyView;
import com.example.member.web.frontcontroller.v3.ControllerV3;
import com.example.member.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.member.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.member.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.member.web.frontcontroller.v4.ControllerV4;
import com.example.member.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5",urlPatterns ="/front-controller/v5/*" )
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String,Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    public void initHandlerMappingMap(){
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }
    public void initHandlerAdapters(){
        handlerAdapters.add(new ControllerV3HandlerAdapter());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler =getHandler(request);

        if( handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 해당하는 uri 가 없다면 404에러 발생시킴
            return;}

        MyHandlerAdapter adapter=getHandlerAdapter(handler);
        ModelView mv= adapter.handle(request,response,handler);

        String viewName=mv.getViewName();
        MyView view= viewResolver(viewName);
        view.render(mv.getModel(),request,response); // render 호출하면서 model을 넘겨줌
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {   // 찾는 핸들러에 부합한다면 true라면
                return adapter;     // 해당 어뎁터를 가져온다.
            }}
        throw new IllegalArgumentException("handlerAdapter를 찾을 수 없습니다." + handler);
        }
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);  // 넘겨받은 uri 에 매핑되는 Controller 를 Map에서 찾아 그 객체를 controller 에 저장 ( ControllerV1 인터페이스로 타입을 정의해서 편리 )
    }

    // HttpServletRequest에 있는 모든 parameter를 뽑아서 저장
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    // viewResolver 에서 url에 대한 처리를 해준다. 논리이름만 viewName에 넣어주면, 전체 url을 붙여서 view에 넘겨준다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

