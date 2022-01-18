<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
회원 저장 성공
<%-- 아래는 프로퍼티 접근법: 가져올경우 getId()수행 하고 , 값을 세팅하는 경우 setId()를 수행한다.--%>
<ul>
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">홈으로</a>
</body>
</html>

<%-- Member 캐스트해서 request 저장소에서 객체꺼내서 데이터 출력하는 방식

<ul>
    <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>
    <li>username=<%=((Member)request.getAttribute("member")).getUserName()%></li>
    <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>
</ul>

    --%>