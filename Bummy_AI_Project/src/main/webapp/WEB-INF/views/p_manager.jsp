<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
if (${p.user_type } == "P") {
	var user_type = "가입 승인됨"
} else if (${p.user_type } == "N") {
	var user_type = "승인 대기 중"
} else {
	var user_type = "알 수 없는 회원"
}
</script>
</head>
<body>
	<table border="1" align="center" width="100%">
		<tr align="center" bgcolor="lightgreen">
			<td> <b>번호</b> </td>
			<td> <b>아이디</b> </td>
			<td> <b>이름</b> </td>
			<td> <b>전화번호</b> </td>
			<td> <b>이메일</b> </td>
			<td> <b>타입</b> </td>
			<td> <b>사진</b> </td>
			<td> <b>소속</b> </td>
		</tr>
	<form action="가입승인.jsp">
	<c:forEach var="p" items="${pList }" varStatus="i">
		<tr>
			<td>${i.count }</td>
			<td>${p.user_id }</td>
			<td>${p.user_name }</td>
			<td>${p.user_phone }</td>
			<td>${p.user_email }</td>
			<td>
				<select name="user_type">
				<option value=${p.user_type } selected>${p.user_type }</option>
				<option value="P">가입 승인</option>
				<option value="N">승인 취소 </option>
				</select>
			</td>
			<td>${p.user_img }</td>
			<td>${p.user_belong }</td>
		</tr>
	</c:forEach>
	</table>
	<input type="submit" value="클릭하여 가입을 승인합니다.">
	</form>
</body>
</html>