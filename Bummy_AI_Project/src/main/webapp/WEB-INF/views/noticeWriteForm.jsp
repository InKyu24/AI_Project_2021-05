<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  /> 
<% request.setCharacterEncoding("UTF-8"); %> 

<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
     <!-- Favicon icon -->
    <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
    <!-- Google font-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
    <!-- waves.css -->
    <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all">
    <!-- Required Fremwork -->
    <link rel="stylesheet" type="text/css" href="assets/css/jieun/css/jieun.min.css">
    <!-- waves.css -->
    <link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all">
    <!-- themify icon -->
    <link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css">
    <!-- font-awesome-n -->
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome-n.min.css">
    <link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
    <!-- scrollbar.css -->
    <link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css">
    <!-- Style.css -->
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    
<script type="text/javascript">
$('#backToList').click(function(){
	var href = 'noticeList';
	$("#main").load(href);
});	

function writeNotice(){
 	
	$("#main").load("../boardList");
};
  	
  	
</script>

</head>
<body>
<div class="col-xl-12">
                                              <div class="card proj-progress-card">
                                                    <div class="card-block"><div class="card">
                                            <div class="card-header">
                                                <h5>&nbsp;공지 작성&nbsp;</h5>
<div class="card-header-right">

                                              </div>
                                            </div>
<div class="card-block table-border-style">
                                                <div class="table-border-style"> 
	                                                            <div class="form-group row">
                                                                <label class="col-sm-1 col-form-label">제목</label>
                                                                <div class="col-sm-11">
                                                                    <input type="text" class="form-control" maxlength="300" name="notice_title" >
                                                                </div>
																	
                                                  </div>
	</div>
	                                                            <div class="form-group row">
                                                                <label class="col-sm-1 col-form-label">파일</label>
                                                                <div class="col-sm-11">
                                                                    <input type="file" class="form-control">
                                                                    <script type="text/javascript">
																		  var cnt=1;
																		  function fn_addFile(){
																			  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
																			  cnt++;
																		  }  
															  		</script>
                                                                </div>
                                                            </div>
	<div class="form-group row">
      <label class="col-sm-1 col-form-label">내용</label>
<div class="col-sm-11">
        <textarea name="notice_content" maxlength="4000" rows="17" cols="15" class="form-control">
	</textarea>
	
</div>
		
  </div>
       <input type="submit" class="btn waves-effect waves-light btn-primary pull-right" id="writeNotice" value="작성하기"/>                                    </div>
														
                                            <table>
                                                <div class="table-responsive">
                                                    <table class="table">
                                              <tbody>
                                                <tr> </tr>
                                              </tbody>
												</div>
                                            </table>
                                                                                                </div>
                                                      <div class="row">
<di></di>
</div>
                                                </div>
</div>
                                        </div>
<div class="card-block proj-progress-card"> </div>
</body>
</html>

