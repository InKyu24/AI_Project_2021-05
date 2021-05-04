<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %>
  
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	// 공지 쓰기 버튼 실행 
	$(function(){
		var user_type=$.cookie('user_type');
		$('#noticeWriteBtn').click(function(){
			if (user_type != "L") {
				alert("주최자만 공지 작성이 가능합니다.")
			} else {
				var href = 'noticeWriteForm';
				$("#main").load(href);
			} 
		});		
	});
</script>

</head>
<body>
<div class="col-xl-12">
	<div class="card proj-progress-card">
		<div class="card-block">
			<div class="card">
				<div class="card-header">
				<h5>공지사항</h5>
				<div class="card-header-right">
				</div>
			</div>
			<div class="card-block table-border-style container">
				<div class="table-responsive">
					<table align="center" border="0"  width="80%"  class="table table-striped">                                               
						<thead>
							<tr>
								<th>No</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
					<tbody>
					<c:choose>
					<c:when test="${notiList=='[]'}" >
						<tr  height="10">
							<td colspan="4">
								<p align="center">
									<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
								</p>
							</td>  
						</tr>
					</c:when>
					
					<c:when test="${notiList !='[]' }" >
						<c:forEach  var="noti" items="${notiList }" varStatus="notiNum" >
							<tr>
								<th scope="row">
									${noti.notice_notiNO}
								</td>
								<td align='left'  width="35%">
									<a class='cls1' href="../viewNoti?notice_notiNO=${noti.notice_notiNO}">${noti.notice_title }</a>		
								</td>
								<td width="10%">
									${noti.notice_name }
								</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${noti.notice_Date}" />
								</td> 
							</tr>
						</c:forEach>
					</c:when>
					</c:choose>
					</tbody>
					</table>
				</div>
				<center>
					<input id="noticeWriteBtn" type="button" value="공지 쓰기"/>
				</center>
			</div>
		</div>
	</div>
</div>
</body>
</html>