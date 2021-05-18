package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
        //에노테이션 기반으로 동작하기 때문에, 메서드의 이름(여기서는 process)은 임의로 지으면 된다.
    }

    @RequestMapping("save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        //HttpServletRequest request, HttpServletResponse response 쓰지는 않지만, 이렇게도 받아올 수 있다는 걸 보여드리려고요
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); //문자로오기때문에 파스

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }

}
