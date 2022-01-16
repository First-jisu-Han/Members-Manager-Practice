package com.example.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    // 싱글톤이라서
    MemberRepository memberRepository=MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save () {
        // ~일떼
        Member member=new Member("jisuhan",26);
        // ~면
        Member storedMember=memberRepository.save(member);
        // ~다
        Member findMember=memberRepository.findById(1L);
        assertThat(findMember).isSameAs(storedMember);
    }

    @Test
    void findAll(){
        // ~일때
        Member memberA=new Member("memberA",10);
        Member memberB=new Member("memberB",22);
        Member memberC=new Member("memberC",40);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);
        // ~면
        List<Member> result = memberRepository.findAll();
        // ~다
        assertThat(result.size()).isSameAs(3);
        assertThat(result).contains(memberA,memberB,memberC);
    }

}
