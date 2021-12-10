package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자에 autowired가 있으면 스프링이 연결시킴
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
