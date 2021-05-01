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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
	
  <meta charset="UTF-8">
  <title>글 목록창</title>
</head>

<body>
<br><br><br>
<div class="container">
<table align="center" border="0"  width="80%"  class="table table-striped">
  <tr height="10" align="center"  bgcolor="lightgray">
     <td width="10%">글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
  </tr>
<c:choose>
  <c:when test="${notiList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${notiList !=null }" >
    <c:forEach  var="noti" items="${notiList }" varStatus="notiNum" >
     <tr align="center">
	<td width="5%">${notiNum.count}</td>
	<td width="10%">${noti.notice_name }</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   <c:choose>
	      <c:when test='${noti.level > 1 }'>  
	         <c:forEach begin="1" end="${noti.level }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="../viewNoti?notice_notiNO=${noti.notice_notiNO}">${noti.notice_title}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="../viewNoti?notice_notiNO=${noti.notice_notiNO}">${noti.notice_title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  <td  width="30%"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${noti.notice_Date}" /></td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
</div>
<center>
<button type="button" onclick="location.href='noticeWriteForm'">글쓰기</button>
</center>
</body>
</html>