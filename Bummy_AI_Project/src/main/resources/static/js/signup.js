$(document).ready(function(){
	$('#signup').click(function(){
		var user_name = $('#user_name').val();
		var user_id = $('#user_id').val();
		var user_pw = $('#user_pw').val();
		var user_pwc = $('#user_pwc').val();
		var user_phone = $('#user_phone').val();
		var user_email = $('#user_email').val();
		var user_belong = $('#user_belong').val();		
		
		if (user_id == '') {
		alert("아이디를 입력해주세요.");
		return;
		}
		
		if (user_name == '') {
		alert("이름을 입력해주세요.");
		return;
		}
		
		if (user_pw == '') {
		alert("비밀번호를 입력해주세요.");
		return;
		}
		
		if (user_pw != user_pwc) {
		alert("비밀번호 확인과 일치하지 않습니다.");
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
			  
		$.post("../signup.do",
			{
				user_name:user_name, 
				user_id:user_id,
				user_pw:user_pw,
				user_phone:user_phone,
				user_email:user_email,
				user_belong:user_belong
			},
			function(data, status){
				alert(data)
				self.close();
			}
		);
	});
});
	