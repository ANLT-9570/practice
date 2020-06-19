<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML ><!-- PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.alerts.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/png.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cas.login.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/capsLock.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/md5.js"></script>


<title>登录</title>
<style type="text/css">
/* body.login{
	background-image: url(${pageContext.request.contextPath}/images/index_logo.jpg);
	background-repeat: no-repeat;
	/* background-size:100% 100%; */
	/* background-size:contain|cover;
	background-size:1700px 1000px;
	background-position: center center;
}  */ 
body {
	background-image:url(${pageContext.request.contextPath}/images/bjt.jpg);
	background-repeat:no-repeat; 
	background-position:0px 90px;
	
	}
	
.login_boder{
    background: url(${pageContext.request.contextPath}/images/dlbjt.png) no-repeat;
    background-color: white; /* 白色 */
    height: 290px;
    width: 300px;
    border-radius: 5px;
}
.yhan{
background-image: url(images/yhan.png);/*设置小图标*/
background-size: 25px 25px;/*小图标的大小*/
background-position: 5px 4px;/*小图标在input的位置*/
background-repeat: no-repeat;/*背景小图标不重复*/
padding: 0px 0px 0px 40px;/*设置input内边距*/
/*设置input样式好看*/
border:1px solid #ddd;
margin: 0px;
width: 180px;
    height: 36px;
    border: 1px solid #cad2db;
    border-radius: 5px;
    line-height: 36px;
    webkit-border-radius: 5px;
}
.mman{
background-image: url(images/mman.png);/*设置小图标*/
background-size: 25px 25px;/*小图标的大小*/
background-position: 5px 4px;/*小图标在input的位置*/
background-repeat: no-repeat;/*背景小图标不重复*/
padding: 0px 0px 0px 40px;/*设置input内边距*/
/*设置input样式好看*/
border:1px solid #ddd;
margin: 0px;
width: 180px;
    height: 36px;
    border: 1px solid #cad2db;
    border-radius: 5px;
    line-height: 36px;
    webkit-border-radius: 5px;
}
/* .sub_button{
background: url(../images/djan.png) no-repeat -153px -850px;
} */
.rem_sub input.sub_button{
float: left;
/* background: url(../images/djan.png) no-repeat -153px -850px; */
    background: no-repeat -0px -850px;
    width: 200px;
    color: #f9f9f9;
    border-color: #0dea53;
    
}
</style>
<script type="text/javascript">
/* $(function(){
	$("#loginname").css({ background-image: "url(${pageContext.request.contextPath}/images/yhan.jpg)"});
}) */
</script>
</head>

<body class="" onkeydown="keyLogin();">
	<div class="login_logo" style="margin-top:15px;margin-left: -800px;margin-bottom: 5px;"><img src="${pageContext.request.contextPath}/images/logos.png" alt="" /><!-- <p style="font-size:30px; color:blue;margin-top:0px;margin-left: -100px">广西营造林登录系统</p> --></div>
	
<!-- <div class= "bd" style="height: 700px;margin-top: -100px"> -->

	<div class="login_m" style="margin-left: 900px;padding-top: 50px">
		<div class="login_boder" style="margin-left: 0px;">
		
		<h1 style="margin-left: 100px;margin-top: 8px;color: #008844;">用户登录</h1>
		
			<div class="login_padding" style="padding-top: 20px">
				<h2>用户名</h2>
			<form id="formlogin" method="post" >	
				<label>
					<input type="text" id="loginname" name="username" class="yhan"/>
				</label>
				<h2>密码</h2>
				<label>
					<input type="password" name="password" id="password" class="mman"/>
				</label>
				<p>
					<!-- 错误回显 -->
					 <span style="color:red;" id="sfbestNameErr"></span>
					 <span style="color:red" id="sfbestPwdErr"></span>
				</p>
					 <br/>
				 <div class="rem_sub_l">
						<input type="checkbox" name="checkbox" id="save_me"/>
						<label for="checkbox">记住密码</label>
					</div>
				 
				<div class="rem_sub" style="margin-top: 0px;padding-left: -100px">
					
					<label style="margin-left: -10px">
							<input  type="button" class="sub_button" name="button" id="login_sub" value="登录" style="background-color: #06a538;opacity: 0.7;border-color:#f9f9f9;margin-left: 10px "/>
					</label>
				
				</div>
				
				
			  </form>
			</div>
		</div><!--login_boder end-->
	</div><!--login_m end-->
<!-- </div> -->

<br />
<br />
<br />
<br />
<br/>
<!-- <p style="font-size:15px; color:green" align="center">版权所有：广西壮族自治区林业厅 All Rights Reserved | 技术支持：恒瑞信息</p> -->
<p style="font-size:15px;" align="center">版权所有：广西壮族自治区林业厅 All Rights Reserved | 技术支持：恒瑞信息</p>
<script type="text/javascript">

//点击回车键登录
function keyLogin(){  
   if (event.keyCode==13){  //回车键的键值为13
	   $("#login_sub").trigger('click');
    }  
}
$(function(){
	


	$.ajaxSettings.async = false;//取消同步
/* 	window.event.returnValue = false;//（IE7只加入这个）

*/
	if (window != top)
	{
		/* $.messager.alert('提示框','用户已过期请重新登录!!'); */
	    top.location.href = location.href;
	}
	

	$("#login_sub").click(function(){
		LOGIN.login();
	});
	//var redirectUrl = "${redirect}";
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

				var password = $("#password").val();
				//对表单输入的值进行Md5加密
				var md5Password = md5(password);
				//把加密后的值  设置回表单
				$("#password").val(md5Password);
				var der = $.ajax({
						url:'${pageContext.request.contextPath}/user/login', 
						data:$("#formlogin").serialize(),
						success:function(data){
					if (data.status == 200) { 
					//	$.messager.alert('提示','登录成功!','info',function(){
						
						$.messager.alert('提示','登录成功!','info',function(){
								$.messager.show({title:'提示',msg:"登录成功,页面加载中请稍等...",timeout:2000,showType:'slide'});
								//跳转到首页

								//加载首页导航栏
							$.ajax({
									async:false,
									url:'${pageContext.request.contextPath }/menu_user',
									dataType:'json',
									success:function(data){
										//console.info(data);
									}
								}); 
								//$.post("${pageContext.request.contextPath }/index",function(){});//跳首页controller
							//location.href = "http://47.98.175.75:8080/${pageContext.request.contextPath}";
							location.href = "http://localhost:8080/${pageContext.request.contextPath}";
						});
					 	
					} else {
						$.messager.show({title:'提示',msg:"登录失败，原因是：" + data.msg,timeout:3000,showType:'slide'});
					}
				}
					
			  });
			},
			
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
		
	};
});
				

</script>
</body>
</html>
