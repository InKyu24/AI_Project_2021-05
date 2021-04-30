$(document).ready(function(){
	$('#findId').click(function(){ // 아이디 찾기 처리
		var user_name = $('#user_name').val();
		var user_phone = $('#user_phone').val();
		var user_email = $('#user_email').val();
		var user_belong = $('#user_belong').val();
		
		if (user_name == '') {
		alert("이름을 입력해주세요.");
		return;
		}
		
		if (user_phone == '') {
		alert("전화번호를 입력해주세요.");
		return;
		}
		
		if (user_email == '') {
		alert("이메일을 입력해주세요.");
		return;
		}
		
		if (user_belong == '') {
		alert("소속을 입력해주세요.");
		return;
		}
			
		$.post("/findId",
			{
				user_name:user_name,
				user_phone:user_phone,
				user_email:user_email,
				user_belong:user_belong
			},
			function(data, status){
				alert(data);		
				self.close();
			}
		);
	});
});