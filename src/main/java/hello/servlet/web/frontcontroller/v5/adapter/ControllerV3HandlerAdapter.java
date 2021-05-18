package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//컨트롤러 v3를 지원하는 어댑터
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //ex MemberFormControllerV3 얘는 이거의 인스턴스 맞지요
        return (handler instanceof ControllerV3); //핸들러가 v3 인스턴스야? v3가 오면 참. 지원할수 있어 없어?/
        }

    @Override //핸들은 실제 돌리는 거에요
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        //ex MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler; // V3컨트롤을 바꾼다음에
        //캐스팅 이유 :서포츠에서 Contro llerV3만 지원한다고 한번 거름. 실제 호출될 때 핸들이 호출될 텐데, 걸러진 애를 찾아서 호출하기 때문에 v3라고 확정이됨. 그래서 캐스팅해서 쓰면 된다.

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); //컨트롤러에 프로세스를 호출 모델뷰에 반환
        ////ex MemberFormControllerV3의 프로세스를 호출

        return mv ;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> paramMap. put(paramName, request.getParameter(paramName)));
        return paramMap;
    }


}
