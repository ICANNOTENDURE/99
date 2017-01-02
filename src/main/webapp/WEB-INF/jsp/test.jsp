<html>
<head>
	<title>Java API for WebSocket (JSR-356)</title>  
</head>
<script type="text/javascript">
	var websocket = null;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/MVNFHM/websocket?WEBSOCKETUSERNAME=aa");
	} 
	else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://localhost:8080/MVNFHM/websocket");
	} 
	else {
		websocket = new SockJS("ws://localhost:8080/MVNFHM/websocket");
	}
	websocket.onopen = onOpen;
	websocket.onmessage = onMessage;
	websocket.onerror = onError;
	websocket.onclose = onClose;
	      	
	function onOpen(openEvt) {
		//alert(openEvt.Data);
	}
	
	function onMessage(evt) {
		alert(evt.data);
	}
	function onError() {}
	function onClose() {}
	
	function doSend() {
		if (websocket.readyState == websocket.OPEN) {  		
            var msg = document.getElementById("inputMsg").value;
            var o=new Object();
            o.fromUser="2";
            o.toUser="2";
            o.msg=msg;
            websocket.send(JSON.stringify(o));//调用后台handleTextMessage方法
            alert("发送成功!");  
        } else {  
        	alert("连接失败!");  
        }  
	}
</script>
请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSend();">发送</button>
</body>
</html>
    login.jsp
 
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java API for WebSocket (JSR-356)</title> 
</head>
<body>
        <!-ship是我的项目名-->
	<form action="/ship/websocket/login.do">
		登录名：<input type="text" name="username"/>
		<input type="submit" value="登录"/>
	</form>

</body>
</html>