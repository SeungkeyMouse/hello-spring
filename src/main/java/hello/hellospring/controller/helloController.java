package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "hello";
    }
    //MVC 템플릿엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API실습
    @GetMapping("hello-string")
    @ResponseBody//이게 붙어있으면! viewResolver한테 안던지고 HTTPMessageController한테 그냥 바로 넘김
    //이전과의 차이는 뷰가 없음. 그대로 그냥 글자 표현. 쓸일별로 x
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello kim"
    }
    @GetMapping("hello-api")
    @ResponseBody//HTTPMessageController한테 그냥 "객체"를 넘기면 JSON 방식으로 만들어서 넘겨
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//{"name":"api"} key:value 로 표현(JSON방식)
    }

    static class Hello{//객체 단위로 전달하는용 생성했음!!getter/setter "ctrl+N"
        private String name;
        //"프로퍼티 접근 방식"
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    
}
