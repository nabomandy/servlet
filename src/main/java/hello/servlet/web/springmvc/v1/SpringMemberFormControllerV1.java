package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//또는
//@Component 컴포넌트스캔을 통해 스프링 빈으로 등록
//@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
        //에노테이션 기반으로 동작하기 때문에, 메서드의 이름(여기서는 process)은 임의로 지으면 된다.
    }
}
