package com.example.member.web.frontcontroller.v3.controller;

import com.example.member.web.frontcontroller.ModelView;
import com.example.member.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // 물리주소 parameter로 보내준다.
    }
}
