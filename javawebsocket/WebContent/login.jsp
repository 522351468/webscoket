<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="resources/sockjs.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	
	
	<form action="/javawebsocket/userLogin.do/selectUser" method="post">
		用户名:<input type="text" name="userName"/>  <br/>
		密码： <input type="password" name="userPwd"/> <br/>
		<input type="submit" value="提交"/>
	</form>


</body>
</html>