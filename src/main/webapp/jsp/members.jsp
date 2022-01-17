<%@ page import="com.example.member.domain.MemberRepository" %>
<%@ page import="com.example.member.domain.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository=MemberRepository.getInstance();
    List<Member> members= memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%-- 자바코드 내부에 html 그대로 사용가능하다.     --%>
    <%
        for(Member member:members){
            out.write("    <tr>");
            out.write("       <td>" + member.getId() + "</td>");
            out.write("       <td>" + member.getUserName() + "</td>");
            out.write("       <td>" + member.getAge() + "</td>");
            out.write("    </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>
