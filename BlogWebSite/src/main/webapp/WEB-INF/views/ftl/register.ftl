<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
<title> 个人博客注册页面</title>

<link rel="icon" href="./resources/image/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="./resources/image/favicon.ico" type="image/x-icon">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" />
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/user.js"></script> 
<style>
#header {
  text-align: center;
}
.outer{width: 80%;height: 400px;margin:0 auto;
display: flex;/*设置外层盒子display为flex*/
justify-content:center;/*设置内层盒子的水平居中*/
align-items:center;/*设置内层盒子的垂直居中*/
}
body{
  background-image:url(./resources/image/user_background2.jpg);
}
</style>

</head>

<body>
<div id="header">
<H2>
	个人博客注册
</H2>
</div>

<div class="outer">
  <center>
    <form name="register" id="registerForm" action="signup" method="post" onsubmit="return false;">
    	<label> 账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;</label>
      <input type="text" id="userName" name="userName" /><br/>
    	<label> 密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;</label>
      <input type="password" id="userPasswd" name="userPasswd"/><br/>
      <label>&nbsp;确认密码</label>
      <input type="password" id="confirmPassword" name="confirmPassword"/>
      <br/>
      <span id="registerStatus" style="color:yellow"></span>
      <br/>
    	<input type="submit" value="   注册   " onclick="return verifyUserRegister();"/>
      <input type="reset" value="    重置   " />
      
    </form>
    </br>
    <div id="login">
    <a href="/login"><span style="color:green">登 录</span></a>
    </div>
  </center>
</body>
</html>  