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
<script type="text/javascript">

	$(function(){
		$("#grid").datagrid({
			toolbar : '#tb',
			pageList: [10,30,50],
			pagination : true,
			fit:true,
			rownumbers : true,
			url : '${pageContext.request.contextPath}/function_manage/pageQuery',
			columns : [[
			  {
				  field : 'menuId',
				  checkbox : true,
				  title : '编号',
				  width : 200
			  },
			  {
				  field : 'name',
				  title : '名称',
				  width : 200
			  },  
			  {
				  field : 'description',
				  title : '描述',
				  width : 200
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 200
			  },  
			  {
				  field : 'orderNum',
				  title : '优先级',
				  width : 200
			  },  
			  {
				  field : 'url',
				  title : '路径',
				  width : 200
			  }
			]]
		});
		
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<table id="grid"></table>
</div>

<div id="tb">
     	
    	<shiro:hasPermission name="sys:qxgl:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加权限</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:qxgl:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="deldata()">删除权限</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:qxgl:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="update()">修改</a>
    	</shiro:hasPermission>
    	
    </div>

<div  id="modifyDiv"  class="easyui-window"  data-options="region:'center',closable:false,draggable:true,closed:true,iconCls:'icon-edit',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true">
	<form id="functionForm" action="${pageContext.request.contextPath}/function/updateFunction" method="post">
				<table id="modifyTb" width="80%" align="center">
					<tr class="title">
						<td colspan="2">功能权限信息</td>
					</tr>
					<tr>
					<input type="hidden" name="menuId"/>
						<td width="200">编号</td>
						<td>
						</td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>访问路径</td>
						<td><input type="text" name="url"  /></td>
					</tr>
					<tr>
						<td>是否生成菜单</td>
						<td>
							<select name="generatemenu" class="easyui-combobox">
								<option value="0">不生成</option>
								<option value="1">生成</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>优先级</td>
						<td>
							<input type="text" name="orderNum" class="easyui-numberbox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>父功能点</td>
						<td>
							<input name="pid" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath }/function/getParentNode'"/>
						</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					</table>
					   <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" id="modify">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancel">取消</button>
		     <script type="text/javascript">
		     //添加
		     function add(){
					location.href='${pageContext.request.contextPath}/admin/function_add.jsp';
				}
		     //删除
		     function deldata(){
					var rows = $("#grid").datagrid("getSelections");
					if(rows.length>0){
						$.messager.confirm('提示','你确定要删除这'+ rows.length +'条数据吗？',function(r){
							if(r){
								var array = new Array();
								for(var i=0;i<rows.length;i++){
									//获取所有被选行的id
									var id = rows[i].menuId;
									array.push(id);
								}
								//把所有id拼起来，用","隔开
								var ids = array.join(",");
								$.post("${pageContext.request.contextPath}/function/deleteBatch",{"ids":ids},function(){
									
								});
							//location.href='${pageContext.request.contextPath}/function/delete';
							}
					   });
					}else{
						$.messager.alert('提示','<h1>请最少选择一行进行删除!</h1>','warning');
					}
				}
		     //修改
		     function update(){
					var rows = $('#grid').datagrid("getSelections");
					//判断是否选择的是一行
					if(rows.length==1){
						//获取当前被选行
						var row = $('#grid').datagrid("getSelected");
						$("#modifyDiv").window('open');
						//数据回显
						$("#modifyTb").form('load',row);
			    		//点击修改
					     $("#modify").click(function(){
				    			var r =$("#functionForm").form("validate");
				    			if(r){
				    				$("#functionForm").submit();
				    			}
				    	});
					}else{
						$.messager.alert('提示','<h1>请选择一行进行编辑!</h1>','warning');
					}
					
				}
			     //取消
			 		$("#cancel").click(function(){
			 			$("#modifyDiv").window('close');
			 		});

		     </script>
			</form>
</div>
</body>
</html>