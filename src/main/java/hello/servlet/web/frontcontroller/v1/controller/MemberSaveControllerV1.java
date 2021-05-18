package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.폼에서 온 걸 꺼낸 다음에
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //request.getParameter의 응답결과는 항상 문자. 그래서 숫자타입으로 변환

        //2. 비즈니스 로직, 세이브를 합니다.
        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        //request객체에 맵같은 내부저장소가 있다. 거기에 저장을 하는 거다.
        request.setAttribute("member", member); //request내부 저장소에 저장

        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }
}
