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
		};
		
		// 메시지 보내기 및 쉬는시간 및 출석체크
		$("#msgBtn").on("click", ()=> {
			var msg = $("#chatMsg").val()
			if (user_type == "L" && msg.slice(0,5) == "#쉬는시간") {
				var breakMsg = msg.slice(6);
				var breakMsgArr = breakMsg.split('/',2);
				// 쉬는 시간
				let breakTime = breakMsgArr[0];
				let breakTimeMsg = breakMsgArr[1];
				ws.send("[서버] 지금부터 "+breakTime+"분 동안 쉬는 시간입니다. 시간이 지나면 알려드릴게요!");
				// 이제 breakTime을 가져와서 시간이 흐르게 하면 된다.
					// [DB를 만들어주자] DB에는 user_belong[PK], breakTime, breakTimeMsg, breakBool
					// 서버에 user_belong, breakTime, breakTimeMsg, breakBool 전송
			} if (user_type == "L" && msg.slice(0,5) == "#출석체크") {
				ws.send("[서버] 5초 후에 출석체크를 시작합니다. 카메라 앞에 얼굴이 잘 보이도록 해주세요.");			
			} else {
				ws.send(name+" "+msg);
			}
			$("#chatMsg").val("");
			let scrollHeight = $("#chatTxt").prop("scrollHeight");
			$("#chatTxt").scrollTop(scrollHeight);
		});
		
		// 메시지가 오면 메시지를 받는다.
		ws.onmessage=function(msg){
			console.log(msg.data);
			var oldMsg=$("textarea").val();
			var message=oldMsg+"\n"+msg.data;
			$("textarea").val(message);
			if (msg.data == "[서버] 5초 후에 출석체크를 시작합니다. 카메라 앞에 얼굴이 잘 보이도록 해주세요." && user_type!="L") {
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
											ws.send("[서버] "+user_name+"님은 현재 부재중인 것 같습니다.");
										// 얼굴이 많으면
											alert("얼굴이 너무 많습니다. 주최자에게 다시 출석체크를 요청해주세요.");
										// 얼굴이 있으면, 비교 시작
										} else if (data==1) {
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
														ws.send("[서버] "+user_name+"님은 현재 대리출석이 의심됩니다.");
													}
												}
											});
										}
									}
								});
							});
						},
						function() {
							alert("[서버] 카메라 문제로 출석이 확인되지 않았습니다. 주최자에게 다시 출석체크를 요청해주세요.");
						}
					);
					if(++count==1) clearInterval(timer);
				},
				5000);
			}
			
			// 만약에 타입이 P이라면
				// breakBool을 우선적으로 체크
					// breakBool이 true일 때, user_belong에 맞는 breakTime과 breakMsg를 가져오기
						// breakTime이 경과하게 되면 오디오 파일이 재생되도록 한다.
						// breakBool을 false로 만들어준다.
					// breakBool이 false이면, 아무것도 안함.
		}
		
		// 엔터키를 이용하여 전송 
		$("#chatMsg").keypress(function(event){
		     if ( event.which == 13 ) {
		         $('#msgBtn').click();
		         return false;
		     }
		});		
			
		// 캡처 설정
		if (user_type!="L" && check_time != 0) {
			alert(check_time);
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
									// 얼굴이 많으면
										alert("얼굴이 너무 많습니다. 주최자에게 다시 출석체크를 요청해주세요.");
									// 얼굴이 있으면, 비교 시작
									} else if (data==1) {
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
						alert("[서버] 카메라 문제로 출석이 확인되지 않았습니다. 주최자에게 다시 출석체크를 요청해주세요.");
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