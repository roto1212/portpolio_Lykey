<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/codinglearning.css">
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
</head>
<body>
<!-- 네비게이션 -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle collaspsed" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="main" class="navbar-brand">Ly-Key</a>
        </div>
        <!-- 네비게이션 내용 -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <!-- 네비게이션 왼쪽 내용 -->
            <form action="searching" class="navbar-form navbar-left" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="searchWord" size="50" placeholder="검색할 곡명을 입력하세요.(다음 검색)">
                    <button type="submit" class="btn btn-default">검색</button>
                </div>
            </form>
            <!-- 네비 오른쪽 -->
            <div class="nav navbar-nav navbar-right">
                <ul class="nav navbar-nav">
                	<c:if test="${sessionScope.email == null}">
    	            <li>
	                	<a href="signUp" >NEW HERE?</a>
	                </li>
	                <li>
	                	<button class="btn btn-default" style="margin-top: 8px" onclick="location.href='signUp'">sign up</button>
	                </li>
		               	<li>
			               <form action="signIn.do" class="navbar-form" method="post">
			                   <div class="form-group">
			                       <input type="email" class="form-control" name="email" placeholder="input Email">
			                       <input type="password" class="form-control" name="pw"  placeholder="input password">
			                       <button type="submit" class="btn btn-default">sign in</button>
			                   </div>
			               </form>
		               	</li>
                	</c:if>
                	<c:if test="${sessionScope.email != null}">
               			<li>
		                	<a href="lyric_list">saved lyrics</a>
		                </li>
		                
               			<li>
		                	<a href="lyric_list">${sessionScope.email}님 안녕하세요.</a>
		                </li>
	                	<li>
			               <form action="signOut" class="navbar-form" method="post">
			                   <div class="form-group">
			                       <button type="submit" class="btn btn-default">sign out</button>
			                   </div>
			               </form>
	                	
	                	</li>
                	</c:if>
                	
                </ul>
            </div>
        </div>
    </div>
</nav>
</body>
</html>