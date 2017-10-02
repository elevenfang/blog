<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
<title> 个人博客登录页面</title>

<link rel="icon" href="./resources/image/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="./resources/image/favicon.ico" type="image/x-icon">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" />
<script src="./resources/js/jquery.js"></script>
<script src="./resources/js/user.js"></script>

<style>
#header {
  text-align: center;
}
.outer{
	width: 80%;
	height: 400px;
	margin:0 auto;
	display: flex;/*设置外层盒子display为flex*/
	justify-content:center;/*设置内层盒子的水平居中*/
	align-items:center;/*设置内层盒子的垂直居中*/
}
body{
  background-image:url(./resources/image/user_background1.jpg);
  background-size: 100% 100%;
}

</style>

</head>

<body>
<div id="header">
<H2>
	个人博客登录
</H2>
</div>

<div class="outer">
  <center>
    <form name="login" id="loginForm" action="verify" method="post" onsubmit="return false;">
    	<label> 账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;</label>
      <input type="text" id="userName" name="userName" />	<br/>
    	<label> 密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;</label>
      <input type="password" id="userPasswd" name="userPasswd" />	
      <br/>
      <span id="loginStatus" style="color:yellow"></span>
      <br/>
    	<input type="submit" value="   登录   " onclick="return verifyUserLogin();" />
      <input type="reset" value="    重置   " />
      
    </form>

    <br/>
    <center>
    <table style="display:none;">
    	<tr>
    		<th>UserName</th>  <th>UserPasswd</th>
    	</tr>
      <#list model["userList"] as user>
  	  	<tr>
  	  		<td>${user.userName}</td> <td>${user.userPasswd}</td>
  	  	</tr>
      </#list>
    </table>
    <div id="signup">
    <a href="register"><span style="color:DARKRED">注 册</span></a>
    </div>
    </br>
    </center>
</body>
</html>  
