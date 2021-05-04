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
				<h5>가입 대기자</h5>
				<div class="card-header-right">
				</div>
			</div>
			<div class="card-block table-border-style container">
				<div class="table-responsive">
					<table align="center" border="0"  width="80%"  class="table table-striped">                                               
						<thead>
							<tr>
								<th> 선택 </th>
								<th> 아이디 </th>
								<th> 이름 </th>
								<th> 전화번호 </th>
								<th> 이메일 </th>
								<th> 타입 </th>
								<th> 소속 </th>
							</tr>
						</thead>
					<tbody>
						<c:forEach var="p" items="${pList }" varStatus="i">
						<tr>
							<td><input type="checkbox" value=${p.user_id } id="user_id"></td>
							<td>${p.user_id }</td>
							<td>${p.user_name }</td>
							<td>${p.user_phone }</td>
							<td>${p.user_email }</td>
							<td>${p.user_type }</td>
							<td>${p.user_belong }</td>
						</tr>
						</c:forEach>
					</table>
						<input type="button" id="pAcceptBtn" value="클릭하여 가입을 승인합니다.">
						<input type="button" id="uListBtn" value="전체 회원 보기">
							<script>
								var user_id = $('#user_id').val();
								$('#pAcceptBtn').click(function(){
									console.log(user_id);
									$.post("pAccept",
										{
											user_id:user_id
										},
										function(data, status){
											$("#main").load('pList');
										}
									);
								});
								
								$('#uListBtn').click(function(e){
							    	var href = 'uList';
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