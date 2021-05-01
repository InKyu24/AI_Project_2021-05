<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<% request.setCharacterEncoding("UTF-8"); %> 

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
 
   <title>글보기</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   
	</style>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
	<script type="text/javascript" >

	// 리스트로 돌아가기 기능 
   	function backToList(){
    	 location.href="../noticeList";
     }
   	
	// 수정 버튼으로, 수정 가능 내용 활성화 & 수정완료 및 취소 버튼 생성
	function fn_enable(obj){
		// 작성자 이름이 회원의 이름과 같은 경우에만 가능하도록		
		
		document.getElementById("i_title").disabled=false; // 제목 수정 가능하게
		document.getElementById("i_content").disabled=false; // 내용 수정 가능하게
		// document.getElementById("i_imageFileName").disabled=false;
		document.getElementById("modifyAndCancel").style.display="block"; 
		//document.getElementById("tr_file_upload").style.display="block";
		document.getElementById("modifyAndDelete").style.display="none";
	}
	// 수정 취소 버튼으로, 수정 가능 내용 비활성화 & 수정하기 및 삭제하기 버튼 생성 
	function fn_disable(obj){
		 document.getElementById("i_title").disabled=true; // 제목 수정 가능하게
		 document.getElementById("i_content").disabled=true; // 내용 수정 가능하게
		 // document.getElementById("i_imageFileName").disabled=true;
		 document.getElementById("modifyAndCancel").style.display="none"; 
		 //document.getElementById("tr_file_upload").style.display="none";
		 document.getElementById("modifyAndDelete").style.display="block";
	 }
	
	// 수정 완료 버튼 작동
	function fn_modify_noti(obj){
		obj.method="post"
		obj.action="${contextPath}/modNoti";
		obj.submit();
	}
	 
	function fn_remove_noti(url,notice_notiNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var notice_notiNOInput = document.createElement("input");
	     notice_notiNOInput.setAttribute("type","hidden");
	     notice_notiNOInput.setAttribute("name","notice_notiNO");
	     notice_notiNOInput.setAttribute("value", notice_notiNO);
		 
	     form.appendChild(notice_notiNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 }
	 
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 } 
 </script>
</head>
<body>

<br><br><br>
<div class="container table-responsiv">
	<form name="frmNoti" method="post"  action="${contextPath}"  enctype="multipart/form-data">
		<table  border=0  align="center" class="table table-condensed">
		<!-- 글번호 열 -->
			<tr>
   				<td width=150 align="center" bgcolor=lightgray>글번호</td>
   				<td> <input type="text"  value="${noti.notice_notiNO }"  disabled /><input type="hidden" name="notice_notiNO" value="${noti.notice_notiNO}"  /></td>
  			</tr>
 		<!-- 작성자 이름 열 -->
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">작성자</td>
   				<td><input type=text value="${noti.notice_name }" name="notice_name" id="notice_name" disabled /></td>
  			</tr>
 		<!-- 글 제목 열 -->
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">제목</td>
   				<td><input type=text value="${noti.notice_title }"  name="notice_title"  id="i_title" disabled /></td>
  			</tr>
  		<!-- 글 내용 열 -->	
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">내용</td>
   				<td><textarea rows="10" cols="60"  name="notice_content"  id="i_content"  disabled />${noti.notice_content }</textarea></td>  
  			</tr>
  			
		<!-- 파일을 업로드하는 열 -->
<!-- 
	<c:choose> 
		<c:when test="${not empty noti.notice_filename && noti.notice_filename!='null' }">
		   	<tr>
				<td width="150" align="center" bgcolor="lightgray"  rowspan="2">이미지</td>
			   	<td><input type="hidden" name="notice_filename" value="${noti.notice_filename }" /><img src="${contextPath}/download?notice_notiNO=${noti.notice_notiNO}&notice_filename=${noti.notice_filename}" id="preview"  /></td>   
			</tr>
 
 
			<tr>
			    <td ></td>
			    <td><input  type="file"  name="notice_filename " id="i_imageFileName"   disabled   onchange="readURL(this);"   /></td>
			</tr> 
			
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
			    <td width="150" align="center" bgcolor="lightgray"  rowspan="2">이미지</td>
			    <td><input  type= "hidden"   name="notice_filename" value="${noti.notice_filename }" /></td>
		    </tr>
	 		
	 		<tr>
			    <td ></td>
			    <td><img id="preview"  /><br><input  type="file"  name="notice_filename " id="i_imageFileName"   disabled   onchange="readURL(this);"   /></td>
		  	</tr>
		</c:otherwise>
 	</c:choose>
-->
		
		<!-- 등록일자 열 -->
  			<tr>
  				<td width="150" align="center" bgcolor="lightgray">등록일자</td>
	   			<td><input type=text value="<fmt:formatDate value="${noti.notice_Date}" />" disabled /></td>   
  			</tr>			
  		
		<!--  수정, 삭제 버튼 / 수정완료, 취소 버튼-->   
			<c:if test="${member.user_id == noti.notice_id}">
	      	<tr id="modifyAndDelete">
   				<td><input type=button value="수정하기" onClick="fn_enable(this.form)"><input type=button value="삭제하기" onClick="fn_remove_noti('${contextPath}/removeNoti', ${noti.notice_notiNO})"></td>
  			</tr>
	    	</c:if>
  			
  			<tr id="modifyAndCancel" style="display : none">
				<td><input type=button value="수정 완료" onClick="fn_modify_noti(frmNoti)"><input type=button value="취소"  onClick="fn_disable(this.form)"></td>
			</tr> 
			
  			<tr>	
   				<td><input type=button value="리스트로 돌아가기"  onClick="backToList()" class="btn btn-info"></td>
  			</tr>
		</table>
	</form>
</div>
</body>
</html>
