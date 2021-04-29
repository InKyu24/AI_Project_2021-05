$(document).on("click", "#logout", function(event) {
	$.post("logout",
		{},
		function(data, status){
			$.removeCookie("user_id");		  	
			$.removeCookie("user_name");
			$.removeCookie("user_type");
			$.removeCookie("user_belong");
			$.removeCookie("logined");
			location.reload();						   
		}
	);
});
	 
