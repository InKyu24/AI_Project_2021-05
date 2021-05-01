<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>전체 참여자</H1> 
	<table border="1" align="center" width="100%">
		<tr align="center" bgcolor="lightgreen">
			<td> <b>아이디</b> </td>
			<td> <b>이름</b> </td>
			<td> <b>전화번호</b> </td>
			<td> <b>이메일</b> </td>
			<td> <b>타입</b> </td>
			<td> <b>소속</b> </td>
		</tr>
	<c:forEach var="u" items="${uList }" varStatus="i">
		<tr>
			<td>${u.user_id }</td>
			<td>${u.user_name }</td>
			<td>${u.user_phone }</td>
			<td>${u.user_email }</td>
			<td>${u.user_type }</td>
			<td>${u.user_belong }</td>
		</tr>
	</c:forEach>
	</table>
	<input type="button" value="가입 대기 중인 회원만 보기" onclick="location.href='pList'">
</body>
</html>