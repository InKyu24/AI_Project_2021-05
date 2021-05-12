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
		ws=new WebSocket("wss://34.233.254.189:8090/class");
		// 연결이 되면 아래를 실행 + Java class "WebSocket.java"에서 작성한 메소드 실행
		ws.onopen=function(){
			console.log("con ok");
		};
		
		
		$('#disconnectBtn').click(function(){
			if (user_type == "L") {
				$.post("/attend_break",
						{user_id:user_id, user_belong:user_belong, user_type:user_type },
							function(data, status) {
								console.log("출석 초기화 완료");
							}
				);
			}
			ws.close();
			console.log("WebSocket is closed now.");
			swal('연결 종료', '회의에서 안전하게 연결이 종료되었습니다.', 'success')
		});		
		
		// 메시지 보내기 및 쉬는시간 및 출석체크
		$("#msgBtn").on("click", ()=> {
			var msg = $("#chatMsg").val()
			if (user_type == "L" && msg.slice(0,5) == "#쉬는시간") {
				var breakMsg = msg.slice(6);
				var breakMsgArr = breakMsg.split('/',2);
				let breakTime = breakMsgArr[0];
				let breakTimeMsg = breakMsgArr[1];
				ws.send("[서버] 지금부터 "+breakTime+"분 동안 쉬는 시간입니다. 시간이 지나면 알려드릴게요!");
					$.post("/break_set",
						{
							user_id:user_id,
							user_belong:user_belong,
							breakTime:breakTime,
							breakTimeMsg:breakTimeMsg,
						},
						function(data, status){
							alert(data);
						}
					);
			}else if (user_type == "L" && msg.slice(0,7) == "#출석자 확인") {
				$.post("/attend_check",
						{user_id:user_id, user_belong:user_belong, user_type:user_type },
							function(data, status) {
								ws.send("[서버] 출석이 확인되는 학생은 아래와 같습니다.");
								ws.send(data);
						}
				);
			}else if (user_type == "L" && msg.slice(0,5) == "#출석체크") {
				ws.send("[서버] 5초 후에 출석체크를 시작합니다. 카메라 앞에 얼굴이 잘 보이도록 해주세요.");
				setTimeout(function() { 
					$.post("/attend_check",
							{user_id:user_id, user_belong:user_belong, user_type:user_type },
								function(data, status) {
									ws.send("[서버] 출석이 확인되는 학생은 아래와 같습니다.");
									ws.send(data);
								}
							);
				}, 15000);	
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
									url : '../condition',
									cache : false,
									data : formData,
									processData : false,
									contentType : false,
									success : function(data) {
										if (data=="얼굴이 인식되지 않습니다. 자리에 계시나요?") {
											ws.send("[서버] "+user_name+"님은 현재 부재중인 것 같습니다.");
										} else if (data =="얼굴이 너무 많습니다. 주최자에게 다시 출석체크를 요청해주세요.") {
											alert(data);
										} else if (data=="대리 출석이 의심되는 상황") {
											ws.send("[서버] "+user_name+"님은 현재 대리출석이 의심됩니다.");
										} else if (data=="출석 확인") {
											$.post("/attend_p",
													{user_id:user_id},
														function(data, status) {
															alert("출석체크 완료");
													});	
										} else {
											ws.send("[서버] "+user_name+"님의 컴퓨터에서 알 수 없는 에러 발생")
										}	
									}
								});
							});
						},
						function() {
							ws.send("[서버] "+user_name+"님의 컴퓨터는 현재 웹캠 사용 불가 상태")
						}
					);
					if(++count==1) clearInterval(timer);
				}, 5000);
			}
			
			if (user_type!="L" && msg.data.slice(0,9) == "[서버] 지금부터") {
				$.post("/breakTime_get",
					{	
						user_belong:user_belong
					},
						function(data, status){
							var breakTime = Number(data);
							if (breakTime != null) {
								if (breakTime == 0) {
									alert ("쉬는 시간 설정 오류");
								} else {
									console.log("설정한 쉬는 시간"+breakTime)
									// !!! 테스트 중! 나중에 60를 추가로 곱해줘야 해요
									var breakSecond = breakTime*1000;
									console.log(breakSecond);
									
										setTimeout(function() {
											$.post("/break_get",
												{user_belong:user_belong},
													function(data, status) {
															// !!! 여기서 음성이 나오도록 해야한다.
		/* 														audio=document.querySelector('audio');
																audio.src="https://34.233.254.189:8090/html/"+data+".mp3"
																audio.onloadedmetadata=function(e){
																	audio.play();
																} */
																
																snd=document.querySelector('audio');
																console.log(data);
																data=JSON.parse(data);
																console.log(data);
																var snd = new Audio("data:audio/mp3;base64," + data.base64audio);
																snd.play();

															$.post("/break_break",
																{user_belong:user_belong},
																	function(data, status) {
																		console.log("초기화 완료");
																	}
															)
													}
											)
										}, breakSecond);
								}
							}
						}
				) 
			}
			
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
									url : '../condition',
									cache : false,
									data : formData,
									processData : false,
									contentType : false,
									success : function(data) {
										if (data=="얼굴이 인식되지 않습니다. 자리에 계시나요?") {
											ws.send("[서버] "+user_name+"님은 현재 부재중인 것 같습니다.");
										} else if (data =="얼굴이 너무 많습니다. 주최자에게 다시 출석체크를 요청해주세요.") {
											alert(data);
										} else if (data=="대리 출석이 의심되는 상황") {
											ws.send("[서버] "+user_name+"님은 현재 대리출석이 의심됩니다.");
										} else if (data=="출석 확인") {
											$.post("/attend_p" ,
													{user_id:user_id},
														function(data, status) {
															alert("출석체크 완료");
													});	
										} else {
											ws.send("[서버] "+user_name+"님의 컴퓨터에서 알 수 없는 에러 발생")
										}	
									}
								});
							});
						},
						function() {
							ws.send("[서버] "+user_name+"님의 컴퓨터는 현재 웹캠 사용 불가 상태")
						}
					);
					if(++count==1) clearInterval(timer);
				}, check_time);
			}
		
	});

</script>

</head>
<body>
	

										<div class="col-xl-12">
                                              <div class="card proj-progress-card">
                                                    <div class="card-block"><div class="card">
                                            <div class="card-header">
                                                <h5>회의실에 오신 것을 환영합니다.</h5>
<div class="card-header-right">

                                              </div>
                                            </div>
<div class="card-block table-border-style">

	<h6>주최자는 명령어를 사용하여 여러 가지 기능을 수행할 수 있습니다.</h6>
	<br> 1. 회의실 관리 탭에서 자동 출석 시간을 설정하면, 참여자들의 출석을 자동으로 확인할 수 있습니다.
	<br> 2. 채팅창에 '#출석체크' 라고 입력하게 되면, 5초 뒤에 자동으로 참여자들의 출석을 확인할 수 있습니다.
	<br> 3. 채팅창에 '#쉬는시간 숫자(단위:분)/메시지'를 입력하게 되면, 쉬는 시간이 종료된 후에 메시지를 전달해줍니다. 예시) #쉬는시간 10/쉬는시간 끝!
	<br> 4. 회의실을 떠날 때에는 반드시 연결 종료 버튼을 눌러주세요.
	<br><br>
	
	<table>
	 <tr>
	 	<td><textarea id="chatTxt" rows="20" cols="60"></textarea></td>	
	 	<td><audio src="" controls="controls" style = "display : none"></audio></td> 	
	 </tr>
	 <tr>
	 	<td><input type="text" id="chatMsg" size="60"></td>
	 	<td><input type="button" id="msgBtn" value="전송"></td>
	 </tr>
	 	<td></td>
	 	<td><input type="button" id="disconnectBtn" value="연결 종료"></td>
	 </table>
</div>
                                                      <div class="row">

</div>
                                                </div>
</div>
                                        </div>
<div class="card-block proj-progress-card"> </div>
<div> </div>
                      <div class="card proj-progress-card"> </div>
                                      </div>
<div class="col-xl-12">
  <div class="card proj-progress-card"> </div>
</div>
<div class="col-xl-6">
                        <div class="card proj-progress-card"> </div>
</div>
                                          <div class="col-xl-6">
                                              <div class="card proj-progress-card"> </div>
                                          </div>
                                            <div class="col-xl-12">
                                              <div class="card proj-progress-card"> </div>
                                            </div>
<!-- Project statustic end -->
                                  </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
						
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	

	
	
</body>
</html>