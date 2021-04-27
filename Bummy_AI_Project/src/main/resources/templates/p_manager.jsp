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
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td> <b>번호</b> </td>
			<td> <b>아이디</b> </td>
			<td> <b>비밀번호</b> </td>
			<td> <b>이름</b> </td>
			<td> <b>가입일</b> </td>
		</tr>
		
		<c:forEach var="m" items="${memberList }" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${m.id }</td>
				<td>${m.pw }</td>
				<td>${m.name }</td>
				<td>${m.date }</td>
			</tr>
		</c:forEach>
	
	</table>
</body>
</html>