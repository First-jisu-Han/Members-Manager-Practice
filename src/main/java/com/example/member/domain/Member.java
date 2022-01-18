package com.example.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private long id;
    private String username;
    private int age;

    public Member(){

    }
    // 생성자
    public Member(String username,int age) {
        this.username=username;
        this.age=age;
    }



}
