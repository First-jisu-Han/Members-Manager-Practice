package com.example.member.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Controller 이전의 옛날버전의 인터페이스 실험

@Component("/springmvc/old-controller")    // OldController 의 빈이름으로 , /springmvc/old-controller 를 지정
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
        // 스프링 부트가 자동으로 viewResolver를 가져와서 논리주소 -> 물리주소로 매핑해주는데 , 대신 아래와 같이 properties에 추가해주어야한다.
        // spring.mvc.view.prefix=/WEB-INF/views/
        // spring.mvc.view.suffix=.jsp
    }
}

// localhost8080/springmvc-old-controller를 호출하면 호출이 되는 것을 알 수 있다.

