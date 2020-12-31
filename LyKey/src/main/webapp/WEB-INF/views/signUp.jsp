<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.5.1.min.js"
    ></script>
<script type="text/javascript">
	
	$(function () {
		$("#signUp").attr("disabled", "disabled");
		$("#pwDisplay").html("이메일을 id로 사용합니다. 이메일을 입력해주세요.");
		
	})
	function emailChk() {
		if ($("#email").val().length == 0) {
			$("#pwDisplay").html("이메일을 id로 사용합니다. 이메일을 입력해주세요.")
		} else if ($("#email").val().length > 0){
			$.ajax({
				type: "post",
				url: "id_check",
				data: {"email":$("#email").val()},
				success: function(result) {
					/* console.log("test: " + result); */
					
					$("#pwDisplay").html(result);
					
				}
			});
		}
	}
	function pw_Chk() {
		/* console.log('pwChk()실행'); */
		var pw = document.getElementById('pw').value;
		var pwChk = document.getElementById('pwChk').value;
		var reg = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,26}$/; 
		var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		var email = document.getElementById('email').value;
		/* console.log(pw.value); */
		/* console.log(pwChk.value); */
		
		
		if (pw != ""){
			if(false === reg.test(pw)) {
				document.getElementById('pwDisplay').innerHTML = '비밀번호는 8자 이상이어야 하며, 숫자/영문자/특수문자를 모두 포함해야 합니다.';
			} else if(/(\w)\1\1\1/.test(pw)) {
				document.getElementById('pwDisplay').innerHTML = '같은 문자를 4번 이상 사용하실 수 없습니다.';
			 	return false;
			} else if(pw.search(email) > -1) {
				document.getElementById('pwDisplay').innerHTML = "비밀번호에 아이디가 포함되었습니다.";
			  	return false;
			} else if(pw.search(/\s/) != -1) {
				document.getElementById('pwDisplay').innerHTML = "비밀번호는 공백 없이 입력해주세요.";
				return false;
			} else if(hangulcheck.test(pw)) {
				 document.getElementById('pwDisplay').innerHTML = "비밀번호에 한글을 사용 할 수 없습니다."; 
			} else {
				if (pw != pwChk) {
					document.getElementById('pwDisplay').innerHTML = '비밀번호가 일치하지 않습니다.';
					
				} else {
					document.getElementById('pwDisplay').innerHTML = '비밀번호가 일치합니다.';
					$("#signUp").removeAttr("disabled");
				}
			}
		} else {
			document.getElementById('pwDisplay').innerHTML = '비밀번호를 입력해주세요.';
			$("#signUp").attr("disabled", "disabled");
		}
	}
	
</script>
</head>
<body>
<form action="insertMember" method="post">
	<table id="signup" width="600" align="center" border="1" cellpadding="5" cellspacing="0">
		<tr><th colspan="2">sign up </th></tr>
		<tr>
			<td> email </td> 
			<td>
				<input name="email" id="email" type="email" value="${email}">
				<input type="button" onclick="emailChk()" value="중복확인">
			</td>
		</tr>
		<tr><td> password </td><td><input id="pw" type="password" onkeyup="pw_Chk()"/> </td></tr>
		<tr>
			<td> password check </td>
			<td>
				<input id="pwChk" type="password"  onkeyup="pw_Chk()"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"  id="pwDisplay" style="color: red;"></td>
		</tr>
		<tr><td colspan="2"> <input type="submit" id="signUp" value="sign up"></td> </tr>
	</table>
</form>


</body>
</html>