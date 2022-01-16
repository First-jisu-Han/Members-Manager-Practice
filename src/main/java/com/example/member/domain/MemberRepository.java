package com.example.member.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long,Member> store = new HashMap<>();
    private static Long sequence=0L;

    private static final MemberRepository instance = new MemberRepository(); // 싱글톤객체로 생성

    private MemberRepository(){
    }
    public static MemberRepository getInstance(){
        return instance;
    }
    public Member save(Member member){
        member.setId(++sequence);  // sequence 변수 자체가 MemberRepository 클래스 변수 이므로 인스턴스를 생성해도 같은 값을 공유.
        store.put(member.getId(),member);
        return member;
    }
    public Member findById(Long id){
        return store.get(id);
    }

    // 모든 store에 저장된 값들 가져오기
    public List<Member> findAll(){
        return new ArrayList<>(store.values()); // 외부로부터 store 를 보호하기 위해서
    }

    // store 내용 다 삭제
    public void clearStore(){
        store.clear();
    }






}
