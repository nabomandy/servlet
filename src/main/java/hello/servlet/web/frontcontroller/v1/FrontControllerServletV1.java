package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// "/front-controller/v1/*" 는 v1 하위의 어떤 게 들어와도 이 서블릿이 호출된다는 의미
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        //맵핑정보를 서블릿에 생성할 때 담아놓는 것
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");//실행이 되는가를 보는 거 우선.


        // 예시 아래게 이거가 된다. /front-controller/v1/members
        String requestURI = request.getRequestURI();

//      다형성 이해를 위한 예시 부모는 자식을 다 받을 수 있다. 자식이 부모에 담길 수 있다. ControllerV1 controller = new MemberListControllerV1();
        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //잘 조회가 되면 인터페이스의 process
        //다형성으로 인해서 오버라이드된 process가 호출된다.
        controller.process(request, response);


    }
}

