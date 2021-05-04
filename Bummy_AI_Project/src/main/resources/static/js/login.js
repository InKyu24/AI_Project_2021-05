$(document).ready(function(){
	$.removeCookie("user_id");		  	
	$.removeCookie("user_name");
	$.removeCookie("user_type");
	$.removeCookie("user_belong");
	$.removeCookie("logined");
	// 엔터키를 이용하여 전송 
	$("#user_pw").keypress(function(event){
	     if ( event.which == 13 ) {
	         $('#login').click();
	         return false;
		     }
	});
	
	$('#login').click(function(){
		var user_id = $('#user_id').val();
		var user_pw = $('#user_pw').val();
		var week = ['일', '월', '화', '수', '목', '금', '토'];
		var dayOfWeek = week[new Date().getDay()];
		if (user_id == '') {
		alert("아이디를 입력해주세요.");
		return;
		}
		
		if (user_pw == '') {
		alert("비밀번호를 입력해주세요.");
		return;
		}
				  
		$.post("/login",
			{
				user_id:user_id,
				user_pw:user_pw
			},
			function(data, status){
				var obj = JSON.parse(data);
					if(obj.user_name) {
						user_id = obj.user_id;
						user_name = obj.user_name;
						user_type = obj.user_type;
						user_belong = obj.user_belong;
	  					
	  					if (obj.user_type=="N"){
	  						alert("주최자에게 가입승인을 받은 후, 버미를 이용해주세요");
	  						location.href='login.html';
	  					} else if (obj.user_type!="N"){
	  						$.cookie("user_id",user_id);
	  						$.cookie("user_name",user_name);
	  						$.cookie("user_type",user_type);
		  					$.cookie("user_belong",user_belong);
	  						$("#msgDiv").html(data);
							if (obj.user_type=="L") {
								$('.litem').show();
	  						};
	  						location.href='main-s.html';
	  					};
	  				}else{
	  					alert(obj.msg);
  					};
			}
		);
	});
});
	