package com.example.member.web.springmvc.v3;

import com.example.member.domain.Member;
import com.example.member.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")  // @requestMapping에서 중복되는 코드 앞부분 통합
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository=MemberRepository.getInstance();

    // @RequestMapping 같은 경우 메서드 단위로 되기때문에 한 컨트롤러 클래스에 한번에 모아서 가능하다.
    @RequestMapping("/new-form")
    public String newForm(){
        return "new-form";   // jsp 포워딩 가능, render()
    }
    @RequestMapping("/save")
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

    @RequestMapping
    public String members(Model model){
        List<Member> members=memberRepository.findAll();
        model.addAttribute("members",members);
        return "members"; 
    }


}
