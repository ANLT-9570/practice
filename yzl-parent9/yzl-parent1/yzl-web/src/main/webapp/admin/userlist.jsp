<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<style type="text/css">
.button1 {
    background-color: #4CAF50; /* Green */
    color: white;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}

	.button2 {
    background-color: red; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}
</style>
<script type="text/javascript">
	// 工具栏
	var toolbar = '#tb';
	/* //定义冻结列
	var frozenColumns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}, {
		field : 'username',
		title : '名称',
		width : 80,
		rowspan : 2
	} ] ]; */


	// 定义标题栏
	var columns = [ [ {
		field : 'id',
		checkbox : true,
		rowspan : 2
	}, {
		field : 'username',
		title : '登录名称',
		width : 100,
		rowspan : 2,
		align : 'center'
	},{
		field : 'name',
		title : '姓名',
		width : 100,
		rowspan : 2,
		align : 'center'
	},{
		field : 'sex',
		title : '性别',
		width : 100,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'email',
		title : '邮箱',
		width : 110,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 110,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'work',
		title : '单位',
		width : 110,
		rowspan : 2,
		align : 'center'
	}, {
		field : 'status',
		title : '状态',
		width : 100,
		align : 'center',
		rowspan : 2
	},  {
		field : 'createTimes',
		title : '创建时间',
		width : 120,
		align : 'center',
		rowspan : 2
	}  ] ];
	$(function(){
		// 初始化 datagrid
		// 创建grid
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [10,30,50],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/admin/userlist",
			idField : 'id', 
			/* frozenColumns : frozenColumns, */
			columns : columns,
			//onClickRow : onClickRow,
			onDblClickRow : doDblClickRow
			//onSelect:selectOne
		});
		
		$("body").css({visibility:"visible"});
		
	});
	
	// 双击
	function doDblClickRow(rowIndex, rowData) {
		var items = $('#grid').datagrid('selectRow',rowIndex);
		doView();
	}
	// 单击
	/* function onClickRow(rowIndex){

	} */
	
	function doAdd() {
		location.href="${pageContext.request.contextPath}/userinfo_add";
	}

	var mak="";
	function doView() {
		//alert("编辑用户");
		
		var item = $('#grid').datagrid('getSelected');
		var items = $('#grid').datagrid('getSelections');
		
		if(items==0 || items.length>1){
			$.messager.alert('错误提示','<h1>请选择一个!!!</h1>','error');
			return;
		}
		//装用户拥有角色id
		var userIds=item.roleIdList;
		mak = item.password;
		item.password="";
		/* 数据回显 */
		$("#editForm").form('load',item);
		$("#editWindow").window('open');
		if(userIds != null && userIds.length > 0 ){
			for(var i=0;i<userIds.length;i++){
				var rid=userIds[i];
				var c= $('td>#'+rid);
				c.checked=true;
				var ss = document.getElementById(rid);
				ss.checked=true;
				//console.log(ss);
			}
		}
	}

	function doDelete() {
		
		var ids = [];
		var items = $('#grid').datagrid('getSelections');
		
		
		if(items==""){
			$.messager.alert("错误提示框","至少选择一个",'info');
			return;
		}
		for(var i=0; i<items.length; i++){
		    ids.push(items[i].id);
		    if(items[i].username=="mark"){
				$.messager.alert("提示框","mark为系统用户,不能删除!!",'info');
				return;
			}
		}
		
		
		$.ajax({
			url:'${pageContext.request.contextPath}/deleteUser',
			type:'post',
			async:false,
			traditional: true,
			data:{"ids":ids}
		});		
		$('#grid').datagrid('reload');
		$('#grid').datagrid('uncheckAll');
	}
	
</script>		
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <div region="center" border="false">
    	<table id="grid"></table>
	</div>
	
	<div id="tb">
     	
    	<shiro:hasPermission name="sys:yhgl:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="doView()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:yhgl:xz">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="doAdd()">新增</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:yhgl:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="doDelete()">删除</a>
    	</shiro:hasPermission>
    	
    </div>
	
	<div id="editWindow" class="easyui-window" title="数据修改" style="width:800px;height:415px;" data-options="minimizable:false,collapsible:true,modal:true,closed:true,iconCls:'icon-edit'">
		<form id="editForm" action="${pageContext.request.contextPath }/updateUser" method="post"><br><br>
			<table class="table-edit" width="95%" align="center" data-options="">
           		<tr class="title"><td colspan="4">基本信息</td></tr>
           		
	           	<tr>
	           		<td>登录名称:</td><td><input type="text" name="username" id="username" class="easyui-validatebox" required="true"/><input type="hidden" name="id"><span id="usernameinfo"></span></td>
					<td>密码:</td><td><input type="password" name="password" id="password" class="easyui-validatebox"/></td>
				</tr>
				
				<tr class="title"><td colspan="4">其他信息</td></tr>
				
				<tr>
					<td>姓名</td>
					<td >
						<input type="text" name="name" id="name" class="easyui-validatebox"/>
					</td>
					<td>性别:</td>
	           		<td>
		           		<select name="sex" data-options="editable:false" id="sex" class="easyui-combobox" style="width: 150px;">
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
					<td>邮箱:</td><td><input type="email" name="email" id="email" class="easyui-validatebox" required/></td>
					<td>联系电话</td>
					<td >
						<input type="text" name="telephone" id="telephone" class="easyui-numberbox" required="true" />
					</td>
					
				</tr>
				
	           	<tr><td>备注:</td><td colspan="3"><textarea name="remark" style="width:80%"></textarea></td></tr>
	           	
	           	<tr>
	           		<td>角色</td>
	           		<td id="rolTd" colspan="3"></td>
	           	</tr>
	          </table>
	          <br><br>
		</form>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button1" id="edits">修改</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" id="cancel">取消</button>
	</div>
	
	<script type="text/javascript">
				
				//取消按钮
				$("#cancel").click(function(){
					$("#editWindow").window("close");
				})
				//表单提交
				$("#edits").click(function(){
					//alert('1');
					var pas = $("#password").validatebox('isValid');
					if(pas == false){
						$("#password").val(mak);
						
					}
					var rr = $("#editForm").form('validate');
					if(rr){
						var asd=$("#editForm").submit();
					}
				})
	           		$(function(){
	           			
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
	           			
	           			$.post("${pageContext.request.contextPath }/role_getRoles",function(data){
	           				for(var i=0;i<data.length;i++){
	           					var id = data[i].id;
	           					var name = data[i].name;
	           					$("#rolTd").append('<input type="checkbox" id="'+id+'" name="roleIdList" value="'+ id +'"><label for="'+ id +'">'+name+'</label></input>');
	           				}
	           			});
	           			
	           		});
	</script>
	<style>.error{color:red;}</style>
</body>
</html>