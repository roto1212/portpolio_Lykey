<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/codinglearning.css">
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<%-- <div class="navbar"><jsp:include page="navbar.jsp"/></div> --%>
<form action="save">
	${msg_lyrics}<br><br>
	<input type="hidden" name="searchWord" value="${searchWord}">
	<input type="button" onclick="location.href='https://www.google.com/search?q=${searchWord}+lyrics'" value="${searchWord}로 가사 구글링">
	<c:if test="${sessionScope.email == null}">
		<br>
		<br>
		<button name="save" type="submit" disabled="disabled">${searchWord}&nbsp;저장하기</button>
		<span style="color: #0080ff; font-weight: bold">저장하려면 로그인 해주세요!</span>
	</c:if>
	<c:if test="${sessionScope.email != null}">
		<br>
		<br>
		<c:if test="${searchWord != null }">
		<input type="text" name="songTitle" placeholder="insert song title" value="${searchWord}">	
		</c:if>
		<c:if test="${searchWord == null }">
		<input type="text" name="songTitle" placeholder="insert song title" value="${songTitle}">	
		</c:if>
		<input type="text" name="singer" value="${singer}" placeholder="insert singer">	
		<button name="save" type="submit">${searchWord}&nbsp;저장하기</button>
	</c:if>
	<br>
	<br>
	<textarea name="lyric" id="lyric" cols="70" rows="30" style="resize: none; padding: 20px;">${lyric}</textarea>
</form>
<form action="toLatin">
	<!-- <input type="submit" value="to Latin alphabet" disabled="disabled"> -->
	<input type="hidden" name="lyric" value="${lyric}"> 
</form>



</body>
</html>