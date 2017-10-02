function verifyUserLogin(){
	var userName = document.getElementById("userName");
	var userPasswd = document.getElementById("userPasswd");
	var status = document.getElementById("loginStatus");
	if(userName.value == ""){
		status.innerHTML="账号不能为空";
		return false;
	}
	if(userPasswd.value == ""){
		status.innerHTML="密码不能为空";
		return false;
	}
	status.innerHTML="";
	ajaxSubmit(status);

}

function verifyUserRegister(){
	var userName = document.getElementById("userName");
	var userPasswd = document.getElementById("userPasswd");
	var confirmPassword = document.getElementById("confirmPassword");
	var status = document.getElementById("registerStatus");
	if(userName.value == ""){
		status.innerHTML="账号不能为空";
		return false;
	}
	if(userPasswd.value == ""){
		status.innerHTML="密码不能为空";
		return false;
	}
	if(confirmPassword.value == ""){
		status.innerHTML="确认密码不能为空";
		return false;
	}
	if(userPasswd.value != confirmPassword.value){
		status.innerHTML="两次输入密码不一致";
		userPasswd.value="";
		confirmPassword.value="";
		return false;
	}
	status.innerHTML="";
	ajaxSubmit(status);

}

function ajaxSubmit(status){
	var userForm = document.forms[0];
  	$(userForm).ready(function() {
	  	$(userForm).submit(
	    	function(event) {
		      	var userNameLocal = $('#userName').val();
		     	var userPasswdLocal = $('#userPasswd').val(); 
		        var data = 'userName='
		          	+ encodeURIComponent(userNameLocal)
		          	+ '&userPasswd='
		          	+ encodeURIComponent(userPasswdLocal);
		        $.ajax({
		         	url : $(userForm).attr("action"),
		         	data : data,
		         	type : "POST",

			        success : function(response) {
			          if(response == "SUCCESS"){
			            location.href="homepage?"+userNameLocal;
			          }else{
			            status.innerHTML="账号或密码错误";
			            userName.value='';
			            userPasswd.value='';
			            if(document.getElementById("confirmPassword")!=null){
			            	confirmPassword.value='';
			            }
			          }
			        },
			        error : function(xhr, status, error) {
			          alert(xhr.responseText);
			        }
			     });
			     return false;
	    });
 	});
}