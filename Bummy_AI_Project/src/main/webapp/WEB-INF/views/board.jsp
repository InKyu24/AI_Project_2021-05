<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %>
  
<!DOCTYPE html>
<html>

</head>
<body>
<div class="col-xl-12">
	<div class="card proj-progress-card">
		<div class="card-block">
			<div class="card">
				<div class="card-header">
				<h5>자유게시판</h5>
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
					<c:when test="${articlesList=='[]'}" >
						<tr  height="10">
							<td colspan="4">
								<p align="center">
									<b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
								</p>
							</td>  
						</tr>
					</c:when>
					
					<c:when test="${articlesList !='[]' }" >
						<c:forEach  var="articles" items="${articlesList }" varStatus="articlesNum" >
							<tr>
								<th scope="row">
									${articles.board_articleNO}
								</td>
								<td align='left'  width="35%">
									<a class='cls1' href="../viewNoti?board_articleNO=${articles.board_articleNO}">${articles.board_title }</a>		
								</td>
								<td width="10%">
									${articles.board_name }
								</td>
								<td>
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${articles.board_Date}" />
								</td> 
							</tr>
						</c:forEach>
					</c:when>
					</c:choose>
					</tbody>
					</table>
				</div>
				<center>
					<input id="articlesWriteBtn" type="button" value="글쓰기" onclick="location.href='boardWriteForm'"/>
				</center>
			</div>
		</div>
	</div>
</div>
</body>
</html>