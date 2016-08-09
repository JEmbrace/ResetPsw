<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>******学校</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<script type="text/javascript">
	window.onload = function(){
		
		document.getElementsByTagName("button")[0].onclick = function(){
			if(form1.password.value!=form1.password2.value) { 
				alert("两个输入密码不一致，请重新输入");
				return false;
			}
			
		return true;
		}
	

	}
	
</script>
</head>

<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>输入你的新密码</h1>
			<form name="form1" class="form" action="setCode" method="post">
				<input type="password" name="password" placeholder="请输入你的新密码">
				<input type="password" name="password2" placeholder="请确认你的密码">
				<button type="submit" id="login-button">提交</button>
			</form>
		</div>
		
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</div>

<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>


</body>
</html>