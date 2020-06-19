<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html ><!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" -->
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  --%>

 <%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %> 
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
<meta charset="utf-8" />
<title>广西营造林数据分析系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link rel="stylesheet"
	href="http://fonts.useso.com/css?family=Open+Sans:400,300" />
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="css/style.css" />
<script src="assets/js/ace-extra.min.js"></script>
<script src="assets/js/jquery.min.js"></script>


<style type="text/css">
iframe {
	overflow-x: hidden;
}

marquee{
    width: 600px;
    float: left;
}
.nav-list li.active>a:after{
    border-right-color: #318c22;
}
.navbar{
    background: #ffffff;/* #318c22#eee */
}
.sidebar-shortcuts-large{
    background-color: #318c22;
}
.ace-nav>li>a{
color: #211f1f;
}
.nav-list>li{
    border-bottom: 1px solid #e5e5e5;
}
/* //提示条的样式 */
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 28px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
        border-right-color: rgb(254, 182, 84);
        border-right-style: solid;
        border-right-width: 1px;
        border-left-color: rgb(254, 182, 84);
        border-left-style: solid;
        border-left-width: 1px;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}
/* body {
    margin: 0px;
    padding: 0px;
} */
.top_box {
    line-height: 26px;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips .icon_tip1 {
    background-position: 0 -4px;
    height: 30px;
    margin: 0px 10px 0 10px;
    width: 18px;

/*     background-position: 0 -26px;
    height: 30px;
    margin: 3px 10px 0 10px;
    width: 15px; */
}
.top_tips a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
}
a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
    blr: expression(this.onFocus=this.blur());
    outline: none;
}
.left {
    float: left;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
        background-position-x: 0px;
        background-position-y: 0px;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
}
.right {
    float: right;
}
.top_tips .icon_close {
    background-position: 0 -26px;
    height: 28px;
    margin: 3px 10px 0 10px;
    width: 15px;
    text-indent: -999em;
}
body {
    font-family: Verdana,Arial,simsun,sans-serif,"Microsoft YaHei",Mingliu,Verdana,Helvetica,Lucida;
    font-size: 12px;
    color: #333;
}
.top_box {
    margin: 0 auto;
    width: 300px;
    line-height: 21px;
    overflow: hidden;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 20px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}
</style>

<script type="text/javascript">
    $(function () {
     	$.post("${pageContext.request.contextPath}/message/queryByRoleId",function(data){});
         var timerc = setTimeout(function () {
            $("#hint").load(location.href + " #hint");//注意后面DIV的ID前面的空格，很重要！没有空格的话，会出双眼皮！（也可以使用类名）
        }, 3000); //2秒自动刷新
    	//timerc(); 
   	 	
   	 	$("#read").click(function(){
   	 	    $.post("${pageContext.request.contextPath}/message/read",function(){
   	 	    	$("#hint").load(location.href + " #hint");//在此刷新页面
		    	//去掉定时器的方法  
		    	window.setTimeout(timerc); 
		    	$("#toptips").hide();//隐藏消息提示的div
		    });  
   	 	}); 
    });
    
    function topRead(){
		var cid = 'taskIssued.jsp';
		var cname = "任务下发";
		$('#nav_list').find('li.home').removeClass('active');
		$("[name='taskIssued.jsp']").parent().addClass('active');;
		$("#iframe").attr("src", cid).ready();
		$("#Bcrumbs").attr("href", cid).ready();
		$(".Current_page a").attr('href', cid).ready();
		$(".Current_page").html(cname).ready();
		$("#parentIframe").html("").css("display",
				"none").ready();
    }    
    $("#taskissued").click(function(){
    	console.log("dasdax");
    });
</script>

	</head>
	
	<body>
<!--  	<div  style="text-align:center;" class="top_tips" id="toptips">
		<div class="top_box">
		<div style="display: block;" class="top_news">
			<div class="icons icon_tip1 left"></div>
			<div class="left">
				<a target="_blank" href="//news.dd373.com/Placard/Detail-266.html">警惕：要游戏账号密码信息，谨防上当！</a>
			</div>
		</div>
		</div>
	</div>  -->

	<%-- <div class="top_tips" id="toptips">
		<div class="top_box">
			<div style="display: block;" class="top_news">
				<div class="icons icon_tip1 left"></div>
				<div  id="hint" class="left">
					<c:if test="${sessionScope.updateDataNumber}>0">
						<a target="_blank"  onClick='topRead();' href="javascript:void(0);">温馨提示：当前有<font style="color:red;">${sessionScope.updateDataNumber}条</font>下发任务已更新</a>
					</c:if>
				</div>
				<a href="javascript:void(0);" title="关闭提示，不再提醒" id="read" class="icons icon_close right">关闭提示，不再提醒</a>
			</div>
		</div>
	</div> --%>

	<div class="navbar navbar-default" id="navbar"> 
        <script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<img style="width:85px;height:80px;margin-top: -10px;" alt=广西营造林  src="${pageContext.request.contextPath }/images/logo_index.png">
						<small style="font:50px">					
						<font size="6" color="#1f880e">广西营造林数据分析系统</font><!-- color="#1f880e" -->
						</small> 
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				<div class="navbar-header pull-right" role="navigation">
                   <div class="get_time" ><span id="time"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span>欢迎光临 ${sessionScope.user.username } 管理员</span></div>
					<ul class="nav ace-nav">	
						<li><a href="javascript:ovid(0)" class="change_Password">修改密码</a></li>
                        <li><a href="javascript:ovid(0)" id="Exit_system">退出系统</a></li>
                       
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
		<div class="main-container" id="main-container">
        <script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="rrapp" class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar">
			<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
						
					</script>
					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
                     <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						后台管理系统
						
						</div>
						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>
							<span class="btn btn-info"></span>
							<span class="btn btn-warning"></span>
							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
					<ul class="nav nav-list" id="nav_list">
	
					<%-- <%@include file="/menu.jsp" %> --%>
					<c:forEach items="${list }" var="list">
					
					<c:if test="${empty list.children }">
						<c:if test="${list.generatemenu eq 1 }"><!-- //营造林辅助管理 -->
							<li class="home" id="home"><a href="javascript:void(0)" name="${list.url }" class="iframeurl" title=""><i class="${list.icon }"></i><span class="menu-text"> ${list.name } </span></a></li>
						</c:if>
					</c:if>
					
					<c:if test="${not empty list.children }">
						<li>
						<c:if test="${list.generatemenu eq 1 }"><!--  -->
							<a href="#" class="dropdown-toggle"><i class="${list.icon }"></i><span class="menu-text"> ${list.name } </span><b class="arrow icon-angle-down"></b></a>
						</c:if>
							<ul class="submenu">
								<c:forEach items="${list.children }" var="children">
									<c:if test="${children.generatemenu eq 1 }">
										<li class="home"><a href="javascript:void(0)" name="${children.url }" title="${children.name }" class="iframeurl"><i class="icon-double-angle-right"></i>${children.name }</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:if>
					<li>
							<ul class="submenu">
										<li class="home"><a href="javascript:void(0)" name="backlogTaskIssued.jsp" title="0" class="iframeurl"><i class="icon-double-angle-right"></i>待办</a></li>
							</ul>
						</li>
					</c:forEach>
					
					</ul><!-- /.nav-list -->
					<!-- <div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div> -->
                    <script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
				<div class="main-content">
                <script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
					<div class="breadcrumbs" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="index.jsp">首页</a>
							</li>
							<li class="active"><span class="Current_page"></span></li>
                            <li class="active" id="parentIframe"><span class="parentIframe"></span></li>
						</ul>
					</div>
			<br/>
                 <iframe id="iframe" value="0" style="border:0; width:100%; background-color:#FFF;"  frameborder="0" src="home.jsp">  </iframe>
				</div>
                
                <div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; 选择皮肤</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl">切换到左边</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								切换窄屏
								<b></b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->		
			</div><!-- /.main-container-inner -->
			
		</div>
         <!--底部样式-->
       
         <div class="footer_style" id="footerstyle">  
          <p class="lf"><!-- 版权所有  中华人民共和国版权 --></p>
          <p class="rf"><!-- 地址：广西  邮编：****** 技术支持： --><a style="color:red"><!-- 联系10086 --></a></p>
         </div>
         <!--修改密码样式-->
         <div class="change_Pass_style" id="change_Pass">
            <ul class="xg_style">
             <li><label class="label_name">原&nbsp;&nbsp;密&nbsp;码</label><input name="原密码" type="password" class="" id="password"></li>
             <li><label class="label_name">新&nbsp;&nbsp;密&nbsp;码</label><input name="新密码" type="password" class="" id="Nes_pas"></li>
             <li><label class="label_name">确认密码</label><input name="再次确认密码" type="password" class="" id="c_mew_pas"></li>
              
            </ul>
     <!--       <div class="center"> <button class="btn btn-primary" type="button" id="submit">确认修改</button></div>-->
         </div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<script src="js/jquery.js" type="text/javascript"></script>
	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
		

	</script>

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/typeahead-bs2.min.js"></script>

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	<script src="assets/layer/layer.js" type="text/javascript"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	//前台ajax每次请求都会执行的一段js
/* 	$(document).ajaxComplete(function(event, xhr, settings){
		    if(xhr.responseText && xhr.responseText == "SESSION_OUT"){
		        alert("SESSION_OUT");
		        //ajax 访问 session 失效
		        window.location.href = "${pageContext.request.contextPath }/login";//这边写跳转登录页面网址
		    }
		});
	 */
		jQuery(document).ready(
				function() {
					//初始化宽度、高度    
					$("#main-container").height($(window).height() - 76);
					$("#iframe").height($(window).height() - 155);
					$(".sidebar").height($(window).height() - 99);
					var thisHeight = $("#nav_list").height(
							$(window).height() - 185);
					$(".submenu").height($(thisHeight).height() - 160);
					$("#nav_list").children(".submenu").css("height",
							thisHeight);

					//当文档窗口发生改变时 触发  
					$(window).resize(
							function() {
								$("#main-container").height(
										$(window).height() - 76);
								$("#iframe").height($(window).height() - 155);
								$(".sidebar").height($(window).height() - 99);
								var thisHeight = $("#nav_list").height(
										$(window).height() - 185);
								$(".submenu").height(
										$(thisHeight).height() - 160);
								$("#nav_list").children(".submenu").css(
										"height", thisHeight);
							});
					$(".iframeurl").bind(
							"click",
							function() {
								var cid = $(this).attr("name");
								var cname = $(this).attr("title");
								$("#iframe").attr("src", cid).ready();
								$("#Bcrumbs").attr("href", cid).ready();
								$(".Current_page a").attr('href', cid).ready();
								$(".Current_page").html(cname).ready();
								$("#parentIframe").html("").css("display",
										"none").ready();
							});

				});

		/*********************点击事件*********************/
		$(document).ready(function() {
			$('#nav_list').find('li.home').click(function() {
				$('#nav_list').find('li.home').removeClass('active');
				$(this).addClass('active');
			});

		})
		//时间设置
		function currentTime() {
			var d = new Date(), str = '';
			str += d.getFullYear() + '年';
			str += d.getMonth() + 1 + '月';
			str += d.getDate() + '日';
			str += d.getHours() + '时';
			str += d.getMinutes() + '分';
			str += d.getSeconds() + '秒';
			return str;
		}
		setInterval(function() {
			$('#time').html(currentTime)
		}, 1000);
		//修改密码
		$('.change_Password')
				.on(
						'click',
						function() {
							layer
									.open({
										type : 1,
										title : '修改密码',
										area : [ '300px', '300px' ],
										shadeClose : true,
										content : $('#change_Pass'),
										btn : [ '确认修改' ],
										yes : function(index, layero) {
											if ($("#password").val() == "") {
												layer.alert('原密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}
											if ($("#Nes_pas").val() == "") {
												layer.alert('新密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}

											if ($("#c_mew_pas").val() == "") {
												layer.alert('确认新密码不能为空!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											}
											if (!$("#c_mew_pas").val
													|| $("#c_mew_pas").val() != $(
															"#Nes_pas").val()) {
												layer.alert('密码不一致!', {
													title : '提示框',
													icon : 0,

												});
												return false;
											} else {

												$
														.post(
																"${pageContext.request.contextPath}/change/password",
																{
																	oldps : $(
																			"#password")
																			.val(),
																	newps : $(
																			"#Nes_pas")
																			.val()
																},
																function(data) {

																	if (data.status == 200) {
																		layer
																				.alert(
																						'修改成功！',
																						{
																							title : '提示框',
																							icon : 1,
																						});
																		layer
																				.close(index);
																		//清空表单
																		$(
																				"#c_mew_pas")
																				.val(
																						"");
																		$(
																				"#password")
																				.val(
																						"");
																		$(
																				"#Nes_pas")
																				.val(
																						"");
																	} else {
																		layer
																				.alert(
																						data.msg
																								+ "，请重新输入！",
																						{
																							title : '提示框',
																							icon : 0,
																						});
																		//清空表单
																		$(
																				"#c_mew_pas")
																				.val(
																						"");
																		$(
																				"#password")
																				.val(
																						"");
																		$(
																				"#Nes_pas")
																				.val(
																						"");
																	}

																});
											}
										}
									});
						});
		$('#Exit_system')
				.on(
						'click',
						function() {
							layer
									.confirm(
											'是否确定退出系统？',
											{
												btn : [ '是', '否' ]
											//按钮
											},
											function() {
												$
														.post(
																"${pageContext.request.contextPath}/user/logout",
																function() {
																	location.href = "login.jsp";
																})
											});
						});
	</script>

	<script type="text/javascript">
		$.ajaxSetup({
			complete : function(XMLHttpRequest, textStatus) {
				if (textStatus == "parsererror") {
					$.messager.alert('提示信息', "登陆超时！请重新登陆！", 'info', function() {
						window.location.href = 'login';
					});
				} else if (textStatus == "error") {
					$.messager.alert('提示信息', "请求超时！请稍后再试！", 'info');
				}
			}
		});
		function ajaxPost(url, params, callback) {
			var result = null;
			var headers = {};
			headers['CSRFToken'] = $("#csrftoken").val();

			$.ajax({
						type : 'post',
						async : false,
						url : url,
						data : params,
						dataType : 'json',
						headers : headers,
						success : function(data, status) {
							result = data;
							if (data && data.code && data.code == '101') {
								modals.error("操作失败，请刷新重试，具体错误：" + data.message);
								return false;
							}
							if (callback) {
								callback.call(this, data, status);
							}
						},
						error : function(err, err1, err2) {
							console
									.log("ajaxPost发生异常，请仔细检查请求url是否正确，如下面错误信息中出现success，则表示csrftoken更新，请忽略");
							console.log(err.responseText);
							if (err && err.readyState && err.readyState == '4') {
								var sessionstatus = err
										.getResponseHeader("session-status");
								if (sessionstatus == "timeout") {
									//如果超时就处理 ，指定要跳转的页面
									window.location.href = basePath + "/";
								} else {//csrf异常
									var responseBody = err.responseText;
									if (responseBody) {
										responseBody = "{'retData':"
												+ responseBody;
										var resJson = eval('(' + responseBody
												+ ')');
										$("#csrftoken").val(
												resJson.csrf.CSRFToken);
										this.success(resJson.retData, 200);
									}
									return;
								}
							}
							modals.error({
								text : JSON.stringify(err) + '<br/>err1:'
										+ JSON.stringify(err1) + '<br/>err2:'
										+ JSON.stringify(err2),
								large : true
							});
						}
					});
			return result;
			}
	</script>
</body>
</html>

