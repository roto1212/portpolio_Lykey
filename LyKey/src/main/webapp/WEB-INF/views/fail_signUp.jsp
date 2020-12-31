<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action="signUp"> -->
		<c:if test="${email == null || email == ''}">
			이메일을 입력해주세요.
		</c:if>
		<c:if test="${email != null && email != ''}">
			${email}은 존재하는 이메일입니다.
		</c:if>
		<button onclick="location.href='signUp?email=${email}'">돌아가기</button>
	<!-- </form> -->
	
</body>
</html>