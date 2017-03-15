<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/sockjs.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>12</h1>
	
	<form action="/javawebsocket/userAction.do/sendMessage"  method="post">
		用户登录成功：<br/>
		消息<input type="text" name="message" /><br/>
		用户id <input type="text" name="userId" /><br/>
		<div>
			<!-- 显示所有登陆的 用户 -->
			
			<div id="allUsers">
				<c:forEach items="${allUsers }" var="allUser">
					<div>
						用户id:${allUser.key } ---- 用户真实姓名：${allUser.value.name }
					</div>
				</c:forEach>
			</div>
		</div>
		<input type="text" id="receive_msg"/>
		<input type="submit"/>	
	</form>
	
	
	
	<form action="/javawebsocket/userAction.do/playUser" method="post">
		<input type="text" name="userId"/>
		<input type="submit" value="踢谁下线"/>
	</form>
	
	
	
	<script type="text/javascript">
	
	var socket;
 	if ('WebSocket' in window) {
	 socket = new WebSocket("ws://"+window.location.host+"/javawebsocket/portfolio2");
    } else if ('MozWebSocket' in window) {
   	 socket = new MozWebSocket("ws://"+window.location.host+"/javawebsocket/portfolio2");
    } else {
   	 socket = new SockJS("http://"+window.location.host+"/javawebsocket/sockjs/portfolio2");
    }
	/**
	 * 建立成功的回调函数
	 */
	socket.onopen = function() {
		console.log('open');
	};

	/**
	 * 服务器有消息返回的回调函数
	 */
	socket.onmessage = function(e) {
		console.log('message', e.data);
		if(e.data=="-1"){
			loginOut();
		}else{
			receiveMsg(e.data);
		}
		
	};

	/**
	 * websocket链接关闭的回调函数
	 */
	socket.onclose = function() {
		console.log('close');
	};
	
	function sendMsg2Server(){
		var msg=document.getElementById("msg").value;
		socket.send(msg);
	}
	
	function loginOut(){
		alert("您被强制下线");
		window.location.href="/javawebsocket/loginOut.do/loginOut";
	}
	
	function sendMsg2One(){
		var msg=document.getElementById("msg2").value;
		var reciver=document.getElementById("reciver2").value;
		$.get("test/sendMsg2One",{userId:reciver,msg:msg},function(data){
			
		});
	}
	
	function receiveMsg(msg){
		$("#allUsers").append("<div>"+msg+"</div>");
		//document.getElementById("receive_msg").value=document.getElementById("receive_msg").value+"\n"+msg;
	}
</script>
</body>
</html>