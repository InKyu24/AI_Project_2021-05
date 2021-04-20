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
				alert(data); // 이 까지 확인
//				var obj = JSON.parse(data);
//				if (obj.msg) {
//					alert(obj.msg);
//			  		location.reload();
//		  		} else {
//					data = obj.logoutBtn
//			  		$.cookie("logined",data);
//			  		$("#msgDiv").html(data);
//				}
			}
		);
	});
});
	