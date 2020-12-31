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
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1"/>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/custom.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/codinglearning.css">
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script> --%>
<style type="text/css">
	.searching{
		padding: 20px;
		float: left;
	}
	.container {
		display: flex;
	}
	#mic {
		width: 100%;
	}

</style>
</head>
<body>
<div><jsp:include page="navbar.jsp"/></div>

<c:if test="${lyric == null }">
<div class="container">
	<img id="mic" alt="mic" src="./resources/images/GettyImagesBank_Editor_Cut_4711712.png">
</div>
</c:if>
<c:if test="${lyric != null }">
<div class="searching" style="margin-left: 50px;"><jsp:include page="searching.jsp"/></div>

</c:if>
    
</body> 
</html>