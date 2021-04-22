$(document).ready(function(){
	$('#login').click(function(){
		var user_id = $('#user_id').val();
		var user_pw = $('#user_pw').val();
		
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
				alert(data);
				var obj = JSON.parse(data);
					if(obj.user_name) {
						data=obj.user_name+" "+obj.user_type+"님 환영 "+"<input type='button' value='logout' id='logout'>";
						user_id = obj.user_id;
						user_name = obj.user_name;
						user_type = obj.user_type;
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
	