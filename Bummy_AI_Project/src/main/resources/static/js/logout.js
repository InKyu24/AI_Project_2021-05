$(document).on("click", "#logout", function(event) {
	$.post("logout",
		{},
		function(data, status){
			$.removeCookie("user_id");		  	
			$.removeCookie("user_name");
			$.removeCookie("user_type");
			$.removeCookie("logined");
			$.removeCookie("condition_check");
			$.removeCookie("timer");
			$.removeCookie("p_manager");     
			location.reload();						   
		}
	);
});
	 
