package com.example.member.web.springmvc.v3;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/springmvc/v3/members")  // @requestMapping에서 중복되는 코드 앞부분 통합
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository=MemberRepository.getInstance(); // 싱글톤 패턴

    @GetMapping("/new-form")  // 요청 메세지가 GET 인경우만 받겠다. @RequestMapping(value = "/new-form",method = RequestMethod.GET) 를 간편화
    public String newForm(){
        return "new-form";   // jsp 포워딩 가능, render()
    }


    @PostMapping("/save") // 요청메세지가 POST 인 경우만 받겠다. @RequestMapping(value = "/save",method = RequestMethod.POST) 를 간편화
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model // 모델에 데이터담기
    )
    {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member",member);
        return "save-result";
    }
    @GetMapping  // 요청 메세지가 GET인경우만 받겠다. @RequestMapping(method = RequestMethod.GET) 를 간편화
    public String members(Model model){
        List<Member> members=memberRepository.findAll();
        model.addAttribute("members",members);
        return "members";
    }


}
