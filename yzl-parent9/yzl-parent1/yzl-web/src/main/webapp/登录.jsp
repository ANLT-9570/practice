<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/png.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cas.login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/capsLock.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>

<title>登录</title>

</head>

<body class="login">
	<div class="login_logo" style="margin-top:68px;"><p style="font-size:30px; color:blue">广西营造林登录系统</p></div>
<div class="login_m">
	<div class="login_boder">
		<div class="login_padding">
			<h2>用户名</h2>
		<form id="formlogin" method="post" >	
			<label>
				<input type="text" id="loginname" name="username" class="txt_input txt_input2">
			</label>
			<h2>密码</h2>
			<label>
				<input type="password" name="password" id="password" class="txt_input">
			</label>
			 <span style="color:red" id="sfbestNameErr"></span>
			 <span style="color:red" id="sfbestPwdErr"></span>
			<div class="rem_sub">
				<div class="rem_sub_l">
					<input type="checkbox" name="checkbox" id="save_me">
					<label for="checkbox">记住密码</label>
				</div>
				<label>
						<input type="button" class="sub_button" name="button" id="login_sub" value="登录" style="opacity: 0.7;">
				</label>
			</div>
		  </form>
		</div>
	</div><!--login_boder end-->
</div><!--login_m end-->

<br />
<br />

<p style="font-size:15px; color:green" align="center">版权所有：广西营造林</p>

</body>

<script type="text/javascript">

	var redirectUrl = "${redirect}";
	var LOGIN = {
			
			checkInput:function() {
				$("#sfbestNameErr").attr("class", "").html("").prev().attr("class", "border");
				$("#sfbestPwdErr").attr("class", "").html("").prev().attr("class", "border");
				
				if(!$("#formlogin #loginname").val()) {
					$("#sfbestNameErr").attr("class", "error").show().html("请输入用户名").prev().attr("class", "border-error");
					return false;
				}
				if(!$("#formlogin input[name='password']").val()) {
				    $("#sfbestPwdErr").attr("class", "error").show().html("请输入密码").prev().attr("class", "border-error");
			        return false;
				}
				$("#username").val($("#loginname").val());
				return true;
			},
			doLogin:function() {
				$.post("${pageContext.request.contextPath}/user/login", $("#formlogin").serialize(),function(data){
					if (data.status == 200) {
						$.messager.alert('提示','登录成功!','info',function(){
							if (redirectUrl == "") {
								location.href = "http://localhost:8080/${pageContext.request.contextPath}";
							} else {
								location.href = redirectUrl;
							}							
						});
						
					} else {
						$.messager.alert("错误提示框",data.msg,'info');
						//alert("登录失败，原因是：" + data.msg);
					}
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		
	};
	$(function(){
		$("#login_sub").click(function(){
			LOGIN.login();
		});
	});
</script>
</html>
