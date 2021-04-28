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
	<H1>가입 대기자</H1>
	<table border="1" align="center" width="100%">
		<tr align="center" bgcolor="lightgreen">
			<td> <b>선택</b> </td>
			<td> <b>아이디</b> </td>
			<td> <b>이름</b> </td>
			<td> <b>전화번호</b> </td>
			<td> <b>이메일</b> </td>
			<td> <b>타입</b> </td>
			<td> <b>소속</b> </td>
		</tr>
	<form name="pAccpet_form" method="post" action="pList">
	<c:forEach var="p" items="${pList }" varStatus="i">
		<tr>
			<td><input type="checkbox" value=${p.user_id } name="user_id"></td>
			<td>${p.user_id }</td>
			<td>${p.user_name }</td>
			<td>${p.user_phone }</td>
			<td>${p.user_email }</td>
			<td>${p.user_type }</td>
			<td>${p.user_belong }</td>
		</tr>
	</c:forEach>
	</table>
	<input type="submit" value="클릭하여 가입을 승인합니다.">
	</form>
</body>
</html>