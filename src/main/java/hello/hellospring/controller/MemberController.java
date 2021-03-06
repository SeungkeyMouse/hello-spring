package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자에 autowired가 있으면 스프링이 연결시킴
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";//회원가입이 끝나니까 홈화면으로 돌려보냄.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();//멤버 다 가져오기
        model.addAttribute("members", members);//멤버리스트를 모델에 담아
        return "members/memberList";

    }
}
