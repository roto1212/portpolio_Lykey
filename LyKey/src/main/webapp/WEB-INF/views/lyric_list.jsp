<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.5.1.min.js"
    ></script>
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
    />

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/codinglearning.css"
    />
    <script
      type="text/javascript"
      src="<c:url value='/resources/js/bootstrap.js' />"
    ></script>
    <script type="text/javascript"></script>
    <style type="text/css">
      td,
      th {
        text-align: center;
      }
      .container {
      	display: flex;
      }
      .item1, .item2{
      	padding: 20px;
      }
      #songTitle:hover {
		background-color: #4287f5;
		font-weight: bold;
		color: white;
}
    </style>
  </head>
  <body>
    <div><jsp:include page="navbar.jsp" /></div>
    ${msg_session}<br />

    <div class="container">
    	<div class="item1">
        	<jsp:include page="searching.jsp" />
    	</div>
		<div class="item2">
        	<form name="form1" action="search_saved">
	          <table class="table table-bordered" style="width: 600px;">
	            <tr>
	              <td style="font-weight: bold" colspan="5">saved lyrics</td>
	            </tr>
	            <tr>
	              <td style="font-weight: bold">index</td>
	              <td style="font-weight: bold">song title</td>
	              <td style="font-weight: bold">singer</td>
	              <td style="font-weight: bold">saved date</td>
	              <td style="font-weight: bold">delete</td>
	            </tr>
	            <jsp:useBean id="date" class="java.util.Date" />
	            <c:forEach var="vo" items="${lyricsList.list}">
	              <tr>
	                <td>${vo.idx }</td>
	                <td
	                  id="songTitle"
	                  onclick="location.href='search_saved?idx=${vo.idx}&songTitle=${vo.songTitle}&singer=${vo.singer}'"
	                >
	                  ${vo.songTitle}
	                </td>
	                <td>${vo.singer}</td>
	                <td width="210">
	                  <fmt:formatDate
	                    value="${vo.saved_date}"
	                    pattern="yyyy.MM.dd(E) HH:mm:ss"
	                  />
	                </td>
	                <td>
	                	<input type="button" value="del" onclick="location.href='del_ly?idx=${vo.idx}'">
	              </tr>
	            </c:forEach>
	          </table>
	        </form>
		</div>    
    
    </div>
  </body>
</html>
