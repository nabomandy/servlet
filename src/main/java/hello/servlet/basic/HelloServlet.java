package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); //쿼리파라미터 조회
        System.out.println("username = " + username);

        response.setContentType("text/plain"); //컨텐트타입, 헤더정보에 들어가는 거
        response.setCharacterEncoding("UTF-8"); //컨텐트타입, 헤더정보에 들어가는 거
        response.getWriter().write("hello " + username); //write하면 http메시지 바디에 데이터가 들어간다.
    }
}
