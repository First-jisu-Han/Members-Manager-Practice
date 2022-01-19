package com.example.member.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName;     // view 논리 이름
    private Map<String,Object> model=new HashMap<>();  // 모델 객체 - request에 넣을 값들을 여기다가 넣어서 서블릿에서 사용- 다른컨트롤러들은 서블릿으로부터 자유로워짐

    public ModelView(String viewName){
        this.viewName=viewName;
    }

    public String getViewName(){
        return viewName;
    }
    public void setViewName(String viewName){
        this.viewName=viewName;
    }
    public Map<String,Object> getModel(){
        return model;
    }
    public void setModel(Map<String,Object>model){
        this.model=model;
    }

}
