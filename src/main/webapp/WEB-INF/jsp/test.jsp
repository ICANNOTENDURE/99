<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
	<title>Java API for WebSocket (JSR-356)</title> 
	<script   src="https://code.jquery.com/jquery-2.2.4.min.js"   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="   crossorigin="anonymous"></script> 
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
	function test(){
		 $.post("../apppatuser/getVerification.do",
				 {account:$("#code").val()},
				 function(result){
			   
			  });
	}
	function token(){
		 $.post("../apptest/checkToken.do",
				 {APP_USER_CODE:$("#code").val(),
			     APP_USER_TYPE:$("#usertype").val(),
			 	 APP_TOKEN:$("#token").val()},
				 function(data){
			   		alert(data.message)
			  });
	}
	function check(){
		 $.post("../apppatuser/checkByPwd.do",
				 {pwd:$("#usertype1").val(),
			 account:$("#account1").val()},
				 function(data){
			   		alert(data.code)
	});
	}
	function add(){
		 $.post("../apppatuser/addPatUser.do",
				 {pwd:$("#pwd2").val(),
			 	  account:$("#account2").val(),
			 	  verifyCode:$("#verf").val()},
				 function(data){
			   		alert(data.code)
		});
	}
	
	function addFamily(){
		 $.post("../apppatuser/addPatFamily.do",
				 {famName:"222",
			      APP_TOKEN:'V/TDA3TFB7SD19x5ev5B3pLCcwMn2mC2LaDOytcg3mgqGYaLqSWzqDH3EeSGQRHCxazV3sZNX21lRxxd9eLDUQ/jh65XITpkN90cH+xEn8zbse+1YqB4LHmaFiQ/vpde',
			      famId:"",
			      famSex:"",
			      famBrith:"",
			      famMarry:"",
			      famFlag:""},
				 function(data){
			   		alert(data.code)
		});
		
	}
</script>
	<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
	<button onclick="doSend();">发送</button>
	<br>
	验证码测试<input  id="code" value='13919007855' />
	<button onclick="test();">发送</button>
	<br>
	token测试
	account<input  id="account" value='13919007855' />
	usertype<input  id="usertype" value='1' />
	token<input  id="token" value='V/TDA3TFB7SD19x5ev5B3pLCcwMn2mC2LaDOytcg3mjFEeJnl6UaJCMxZ4hvygEEH3w5zUkAwH2vyroH7fTOOjWtAuLZ/BVtPbSdF0PmAq04s6ljX0efwKSpoVbXoQpI' />
	<button onclick="token();">发送</button>
	<br>
	用户名密码测试
	account<input  id="account1" value='13919007855' />
	usertype<input  id="usertype1" value='1' />
	<button onclick="check();">发送</button>
	<br>
	病人用户注册测试
	account<input  id="account2" value='13919007855' />
	usertype<input  id="pwd2" value='1' />
	验证码<input  id="verf" />
	<button onclick="add();">发送</button>
	<button onclick="addFamily();">addFamily</button>
</body>
</html>