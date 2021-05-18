package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("andy");
        helloData.setAge(30);

        //위에 거를 이런 식으로 바꿀 거{"username:"andy", "age":31}
        //그러려면 Jackson이 제공하는 ObjectMapper가 필요하다
        //writeValueAsString은 객체를 값을 써서 문자로 바꿔라
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);

    }
}
