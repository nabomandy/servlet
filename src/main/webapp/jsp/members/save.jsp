<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //request, response는 사용 가능. jsp도 나중에 서블릿으로 변환됨
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");

    //1.폼에서 온 걸 꺼낸 다음에
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    //request.getParameter의 응답결과는 항상 문자. 그래서 숫자타입으로 변환

    //2. 비즈니스 로직, 세이브를 합니다.
    Member member = new Member(username, age);
    memberRepository.save(member);



%>
<html>
<head>
    <title>Title</title>
</head>
<body>

성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%member.getAge();%></li>

</ul>
<a href="/index.html">메인</a>
</body>
</html>
