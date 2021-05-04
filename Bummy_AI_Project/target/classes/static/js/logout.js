$(document).ready(function() {
	$('.logout').click(function() {
		$.post("/logout",
			{},
			function(data, status){
				$.removeCookie("user_id");		  	
				$.removeCookie("user_name");
				$.removeCookie("user_type");
				$.removeCookie("user_belong");
				location.href='login.html';				   
			}
		);
	});
});
	 
