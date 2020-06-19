<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/additional-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
<script type="text/javascript">
	$(function(){
		$("#telephone").blur(function(){
			var tel=$(this).val;
			
		})
		//用户名校验
		$("#username").blur(function(){
			var inusername=$(this).val();
			//alert(inusername);
			if(inusername != null){
				$.post("${pageContext.request.contextPath }/verifyUsername",{"username":inusername},
						function(data){
							//获取到返回来的植
							var reusername = "";
							if(data != null){
								reusername=data.username;
							}
							
							var usernameinfo="";
							if(inusername==reusername){
								usernameinfo="";
								usernameinfo="用户名已经存在或不能为空";
								
								$("#usernameinfo").css("color","red");
							}else{
								usernameinfo="";
								usernameinfo="用户名可以用";
								
								$("#usernameinfo").css("color","green");
							}
							//将提示信息写到输入框后面
							$("#usernameinfo").html(usernameinfo);
						},
				"json")
			}
		})
		
		$("body").css({visibility:"visible"});
		$('#save').click(function(){
			var r = $('#userForm').form("validate");
			if(r){
				$('#userForm').submit();
			}
		});
		$('#ret').click(function(){
			$.ajax({
				url:'${pageContext.request.contextPath}/ret',
				type:'post',
				async:true,
				//traditional: true,
			});		
		})
	});
	
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			
		</div>
		
	</div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
       <form id="userForm" action="${pageContext.request.contextPath }/user_addUser" method="post" >
       <input name="id" type="hidden">
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
           		
	           	<tr>
	           		<td>登录名称:</td><td><input type="text" name="username" id="username" class="easyui-validatebox" required="true"/><span id="usernameinfo"></span></td>
					<td>密码:</td><td><input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="minLength[5]" /></td>
				</tr>
				
				<tr class="title"><td colspan="4">其他信息</td></tr>
				
				<tr>
					<td>姓名</td>
					<td >
						<input type="text" name="name" id="name" class="easyui-validatebox"/>
					</td>
					<td>性别:</td>
	           		<td>
		           		<select name="sex" id="sex" data-options="editable:false" class="easyui-combobox" style="width: 150px;">
		           			<option value="男">男</option>
		           			<option value="女">女</option>
		           		</select>
	           		</td>
				</tr>
				
	           	<tr>
	           		<td>单位:</td>
					<td>
						<input type="text" name="work" id="work" class="easyui-validatebox"/>
					</td>
					<td>状态:</td>
					<td>
						<select name="status" id="status" data-options="editable:false" class="easyui-combobox" style="width: 150px;">
		           			<option value="启用">启用</option>
		           			<option value="禁用">禁用</option>
		           		</select>
		           	</td>
				</tr>
				
				<tr>
					<td>邮箱:</td><td><input type="email" name="email" id="email" class="easyui-validatebox" /><span id="emailinfo"></span></td>
					<td>联系电话</td>
					<td >
						<input type="text"  name="telephone" id="telephone"  data-options="validType:'telephone'" 
						 class="easyui-numberbox"  />
					</td>
					
				</tr> 
				
	           	<tr><td>备注:</td><td colspan="3"><textarea name="remark" style="width:80%"></textarea></td></tr>
	           	
	           	<tr>
	           		<td>角色</td>
	           		<td id="rolTd" colspan="3"></td>
	           		<!-- <td><input type="checkbox" :value="role.roleId" v-model="user.roleIdList"></td> -->
	           	</tr>
	           	<style>.error{color:red;}</style>
	           	  <script type="text/javascript">
	           	
	           		$(function(){
	           		//手机号校验
	          			var reg = /^1[3|5|7|8][0-9]{9}$/;
	          			$.extend($.fn.validatebox.defaults.rules, {    
	          			 		telephone: {    
	          		    	validator: function(value,param){    
	          		       		 return reg.test(value);    
	          		    	},    
	          		  		  message: '输入电话号码格式错误！'   
	          					}    
	          			});

	           			$("#userForm").validate();
	           			
	           			$.post("${pageContext.request.contextPath }/role_getRoles",function(data){
	           				for(var i=0;i<data.length;i++){
	           					var id = data[i].id;
	           					var name = data[i].name;
	           					$("#rolTd").append('<input type="checkbox" id="'+id+'" name="roleIds" value="'+ id +'"><label for="'+ id +'">'+name+'</label></input>');
	           				}
	           			})
	           		})
	           	  </script>
           </table>
       </form>
	</div>
</body>
</html>