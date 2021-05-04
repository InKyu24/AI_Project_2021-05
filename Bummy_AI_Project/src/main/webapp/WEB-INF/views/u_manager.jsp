<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
</head>
<body>
<div class="col-xl-12">
	<div class="card proj-progress-card">
		<div class="card-block">
			<div class="card">
				<div class="card-header">
				<h5>전체 참여자</h5>
				<div class="card-header-right">
				</div>
			</div>
			<div class="card-block table-border-style container">
				<div class="table-responsive">
					<table align="center" border="0"  width="80%"  class="table table-striped">                                               
						<thead>
							<tr>
								<th> 아이디 </th>
								<th> 이름 </th>
								<th> 전화번호 </th>
								<th> 이메일 </th>
								<th> 타입 </th>
								<th> 소속 </th>
							</tr>
						</thead>
					<tbody>
						
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
						<input type="button" id="pListBtn" value="가입 대기 중인 회원만 보기">
						<script>
							$('#pListBtn').click(function(e){
						    	var href = 'pList';
						    	e.preventDefault();
								$("#main").load(href);
							});
						</script>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>