<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 쿠키 설정 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script>
	$(document).ready(function(){
		var user_name=$.cookie('user_name');
		
		var user_id=$.cookie('user_id');
		var user_type=$.cookie('user_type');
		var user_belong=$.cookie('user_belong');
		var check_time=${check_time}*1000;
			
		let ws, ws_cam;
		let localstream;
		let cam_loop;
		
		let name="["+user_name+"]";
		// HTML5의 내장 객체 WebSocket 생성 + Java class "WebSocket.java"와 연결
		ws=new WebSocket("ws://localhost:8090/class");
		// 연결이 되면 아래를 실행 + Java class "WebSocket.java"에서 작성한 메소드 실행
		ws.onopen=function(){
			console.log("con ok");
		}
			// 메시지가 오면 아래를 
			ws.onmessage=function(msg){
				console.log(msg.data);
				let oldMsg=$("textarea").val();
				let message=oldMsg+"\n"+msg.data;
				$("textarea").val(message);
			}
		
			// 보내기
			$("#msgBtn").on("click", ()=> {
				let msg = $("#chatMsg").val();
				ws.send(name+msg);
				$("#chatMsg").val("");
				let scrollHeight = $("#chatTxt").prop("scrollHeight");
				$("#chatTxt").scrollTop(scrollHeight);
			})
			
			// 엔터키를 이용하여 전송 
			$("#chatMsg").keypress(function(event){
			     if ( event.which == 13 ) {
			         $('#msgBtn').click();
			         return false;
			     }
			});
			
		// 캡처 설정
		if (user_type!="L") {
			let count=0;
			let timer=setInterval(function(){ 
				navigator.getUserMedia(
					{audio:false, video:true},
					function(stream){
						const track=stream.getVideoTracks()[0];
						let imageCapture=new ImageCapture(track);
						imageCapture.takePhoto().then(function(photo){
							console.log(photo);
							const fileName = user_id+'.jpg';
							let formData = new FormData();
							formData.append('file', photo, fileName);
							// 얼굴 개수 확인
							$.ajax({
								type : 'post',
								url : '../condition1',
								cache : false,
								data : formData,
								processData : false,
								contentType : false,
								success : function(data) {
									// 얼굴이 없으면
									if (data==0) {
										alert("얼굴이 인식되지 않습니다. 자리에 계시나요?");
										ws.send("[서버]"+user_name+"님은 현재 부재중인 것 같습니다.");
									// 얼굴이 있으면, 비교 시작
									} else {
										$.ajax({
											type : 'post',
											url : '../condition2',
											cache : false,
											data : formData,
											processData : false,
											contentType : false,
											success : function(data) {
												console.log(data);
												alert(data);
												if (data == "출석 확인") {
												} else if (data == "대리 출석이 의심되는 상황") {
													ws.send("[서버]"+user_name+"님은 현재 대리출석이 의심됩니다.");
												}
											}
										});
									}
								}
							});
						});
					},
					function() {
						alert("fail");
					}
				);
				if(++count==1) clearInterval(timer);
			},
			//선생님이 정한 시간
			check_time);
		}
	});
</script>

</head>
<body>
	<div id="checkMsg"></div>
	<br><br>
	<input type="text" id="chatMsg"><input type="button" id="msgBtn" value="전송"><br>
	<textarea id="chatTxt" rows="10" cols="30"></textarea>
</body>
</html>