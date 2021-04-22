$(document).ready(function(){
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
						data=obj.user_name+" "+obj.user_type+"님 환영합니다. 기분좋은 "+dayOfWeek+"요일 입니다. <input type='button' value='logout' id='logout'>";
						$.cookie("user_id",user_id);	
	  					$.cookie("user_name",user_name);
	  					$.cookie("user_type",user_type);
	  					$.cookie("logined",data);	
	  					$("#msgDiv").html(data);	
	  				}else{
	  					alert(obj.msg);
	  					location.reload();
  					}
			}
		);
	});
});
	