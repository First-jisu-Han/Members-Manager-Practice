<%@ page import="com.example.member.domain.MemberRepository" %>
<%@ page import="com.example.member.domain.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--

<% %> 에 JAVA 코드 로직을 넣는다.
JSP도 나중에 자동으로 서블릿으로 자동변환된다.

--%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
회원 저장 성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">홈으로</a>
</body>
</html>
