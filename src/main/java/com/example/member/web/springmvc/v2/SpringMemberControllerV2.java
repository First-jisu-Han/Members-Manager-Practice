package com.example.member.web.springmvc.v2;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")  // @requestMapping에서 중복되는 코드 앞부분 통합
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository=MemberRepository.getInstance();

    // @RequestMapping 같은 경우 메서드 단위로 되기때문에 한 컨트롤러 클래스에 한번에 모아서 가능하다.
    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");   // jsp 포워딩 가능, render()
    }
    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

}
