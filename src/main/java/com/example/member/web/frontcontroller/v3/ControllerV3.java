package com.example.member.web.frontcontroller.v3;

import com.example.member.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String,String>paramMap);
}
