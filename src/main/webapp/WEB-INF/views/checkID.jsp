<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkID</title>
</head>
<body>
	저는 checkID.jsp 입니다
<!-- 	jstl -> if, else 문 -->
	<c:choose>
 		<c:when test="${idcheck eq 'memberOk'}">  <%-- jstl -> 문자열 비교 eq --%>
			<h2>${loginid }님 ${loginpw }번호로 로그인 성공하셨습니다.</h2>
		</c:when>
		<c:otherwise>
			<h2>로그인 실패!! 아이디 암호 확인하세요.</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>