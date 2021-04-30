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
     function backToList(){
    	 location.href="../boardList";
     }
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/board/modArticle";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,board_articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var board_articleNOInput = document.createElement("input");
	     board_articleNOInput.setAttribute("type","hidden");
	     board_articleNOInput.setAttribute("name","board_articleNO");
	     board_articleNOInput.setAttribute("value", board_articleNO);
		 
	     form.appendChild(board_articleNOInput);
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
	<form name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">
		<table  border=0  align="center" class="table table-condensed">
		<!-- 글번호 열 -->
			<tr>
   				<td width=150 align="center" bgcolor=lightgray>글번호</td>
   				<td> <input type="text"  value="${article.board_articleNO }"  disabled /><input type="hidden" name="board_articleNO" value="${article.board_articleNO}"  /></td>
  			</tr>
 		<!-- 작성자 아이디 열 -->
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">작성자 아이디</td>
   				<td><input type=text value="${article.board_id }" name="board_id"  disabled /></td>
  			</tr>
 		<!-- 글 제목 열 -->
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">제목</td>
   				<td><input type=text value="${article.board_title }"  name="board_title"  id="i_title" disabled /></td>
  			</tr>
  		<!-- 글 내용 열 -->	
  			<tr>
    			<td width="150" align="center" bgcolor="lightgray">내용</td>
   				<td><textarea rows="10" cols="60"  name="board_content"  id="i_content"  disabled />${article.board_content }</textarea></td>  
  			</tr>

	<c:choose> 
		<c:when test="${not empty article.board_FileName && article.board_FileName!='null' }">
		   	<tr>
				<td width="150" align="center" bgcolor="lightgray"  rowspan="2">이미지</td>
			   	<td><input  type= "hidden"   name="board_FileName" value="${article.board_FileName }" /><img src="${contextPath}/download?board_articleNO=${article.board_articleNO}&board_FileName=${article.board_FileName}" id="preview"  /></td>   
			</tr>
			  
			<tr>
			    <td ></td>
			    <td><input  type="file"  name="board_FileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /></td>
			</tr> 
			
		 </c:when>
		 <c:otherwise>
		    <tr  id="tr_file_upload" >
			    <td width="150" align="center" bgcolor="lightgray"  rowspan="2">이미지</td>
			    <td><input  type= "hidden"   name="board_FileName" value="${article.board_FileName }" /></td>
		    </tr>
	 		
	 		<tr>
			    <td ></td>
			    <td><img id="preview"  /><br><input  type="file"  name="board_FileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /></td>
		  	</tr>
		</c:otherwise>
 	</c:choose>
  			<tr>
  				<td width="150" align="center" bgcolor="lightgray">등록일자</td>
	   			<td><input type=text value="<fmt:formatDate value="${article.board_Date}" />" disabled /></td>   
  			</tr>
  			
  			<tr id="tr_btn_modify"  align="center">
				<td colspan="2"><input type=button value="수정 완료"   onClick="fn_modify_article(frmArticle)"><input type=button value="취소"  onClick="backToList(frmArticle)"></td>   
  			</tr>
  			
	<c:if test="${member.user_id == article.board_id }">    
  			<tr id="tr_btn">
   				<td colspan="2" align="center"><input type=button value="수정하기" onClick="fn_enable(this.form)"><input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.board_articleNO})"><br><input type=button value="리스트로 돌아가기"  onClick="backToList()" class="btn btn-info"></td>
    </c:if>
  			</tr>
		</table>
	</form>
</div>
</body>
</html>
