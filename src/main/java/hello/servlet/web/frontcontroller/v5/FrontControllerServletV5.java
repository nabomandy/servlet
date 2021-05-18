package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();//아무 컨트롤러나 다 들어갈 수 있어야 하기 때문에 Object로 들어갔다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();//어댑터 여러개 담겨있는 거 중에 하나 찾아서 쓸거니까.

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //V4추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());//V4를 추가할 수 있는 어댑터 추가
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ex MemberFormControllerV3가 반환
        Object handler = getHandler(request); //핸들러 찾아와. 요청정보로 핸들러를 찾아오세요 요청이 오면 uri에서 핸들러매핑맵에서 보내서 핸들러를 찾아온다. 디테일한 로직은 아래에.
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //핸들러가 널이면 오류 처리하는 로직
        }

        //ex ControllerV3HandlerAdapter 얘가 반환이 됩니다
        MyHandlerAdapter adapter = getHandlerAdapter(handler); //핸들러 어댑터 찾아와 핸들러 어댑터를 가져왔죠

        ModelView mv = adapter.handle(request, response, handler); //핸들을 호출

        //new-form
        String viewName = mv.getViewName();//논리이름 new-form //뷰네임가져와서
        MyView view = viewResolver(viewName); // /WEB-INF/views/new-form.jsp //뷰네임을 호출해주고 뷰를 얻은다음에

        view.render(mv.getModel(), request, response); //뷰에 렌더를 호출
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
        //요청이 오면 requestURI에서 핸들러매핑맵에서 requestURI보내서 핸들러를 찾아온다.
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        ////ex 지금 핸들러가 얘 MemberFormControllerV3
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) { //어댑터가 핸들러를 지원하느냐
                return adapter; //있으면 성공 그 어댑터를 반환
            }
        }
        throw new IllegalStateException("handler adapter를 찾을 수 없습니다." + handler);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}