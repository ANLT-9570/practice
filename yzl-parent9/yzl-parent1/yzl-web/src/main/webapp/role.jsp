<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
  <![endif]-->
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/checkAll.js"></script>
  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<title>角色管理</title>
</head>

<body>
<div class="page-content">
 <!--角色管理-->
 <div class="Role_Manager_style">
  <div class="Manager_style">
    <div class="title_name">用户角色</div>
     <div id="Add_Roles_style"  style="display:none">
     <div class="user_Manager_style ">
  </div>
  </div>
  </div>
  <!---->
  	<br><br>
    <div class="Role_list">
        		<!-- 	添加窗口 -->
    			  <div id="addyRole" class="easyui-window" style="height: 340px;width: 800px" data-options="title:'添加数据',closable:false,draggable:false,closed:true,iconCls:'icon-add',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true">
     	<form id="addyForm" action="${pageContext.request.contextPath }/role/addRole" method="post">
    		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">用户标识符</th>
		     		<th style="text-align: center; vertical-align: middle;">角色名称</th>
		     		<th style="text-align: center; vertical-align: middle;">备注</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea"       name="ch" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input name="charnam"  class="easyui-validatebox"    data-options="required:true" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea"      name="remark" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" id="add">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelModify">取消</button>
		     <script type="text/javascript">
		 		$("#cancelModify").click(function(){
		 			$("#modifyRole").window('close');
		 		});
		     </script>
    	</form>
    </div>
  					  <!-- //修改数据 -->
        			  <div id="modifyRole" class="easyui-window" style="height: 340px;width: 800px" data-options="title:'添加数据',closable:false,draggable:false,closed:true,iconCls:'icon-add',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true">
     	<form id="modifyForm" action="${pageContext.request.contextPath }/role/updateRole" method="post">
    		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">用户标识符</th>
		     		<th style="text-align: center; vertical-align: middle;">角色名称</th>
		     		<th style="text-align: center; vertical-align: middle;">备注</th>
		     	</tr>
		     	<tr align="center">
		     		<input id="add_VTArea"       name="id" type="hidden" style="width: 100%; margin-left: -0.2px;">
		     		<td><input id="add_VTArea"       name="ch" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input name="charnam"  class="easyui-validatebox"    data-options="required:true" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea"      name="remark" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" id="modify">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelAdd">取消</button>
		     <script type="text/javascript">
			     //取消
			 		$("#cancelAdd").click(function(){
			 			$("#modifyRole").window('close');
			 		});
		     </script>
    	</form>
    </div>
    
    <div id="tb">
    	<shiro:hasPermission name="sys:ccqk:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:ccqk:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleter()">删除</a>
    	</shiro:hasPermission>
    	
    	<input id="sear" value="" class="easyui-searchbox" style="width:110px;height:19px;">
    </div>
    
    <div style="height: 500px;width: 1278px">
     	<table id="tabs"></table>
     </div>
    <script type="text/javascript">
    	$("#tabs").datagrid({
    		rownumbers:true,
    		columns:[[
    			{width:300,align:'center',field:'id',title:'',width:100,checkbox:true},
    			{width:300,align:'center',field:'logo',title:'角色标识',width:100},
    			{width:300,align:'center',field:'charnam',title:'角色名称',width:100},
    			{width:300,align:'center',field:'status',title:'角色状态',width:100,
    				formatter: function (value,rows,index) {
						if(rows != null && rows.status=='1'){
							return "启动";	
						}
						return "禁用";
				   }	
    			},
    			{width:300,align:'center',field:'remark',title:'备注',width:100}
    		]],
    		url:'${pageContext.request.contextPath }/role/pageQuery',
    		toolbar:[
    			{iconCls:'icon-add',text:'添加',handler:add},
    		],
    		//双击修改
    		onDblClickRow:update,
    		pagination:true,
    		fit:true
    	});
    	//修改窗口 数据回显
    	function update(rowIndex, rowData){
    		$("#modifyRole").window('open');
    		$("#modifyForm").form("load",rowData);
    		//修改
		     $("#modify").click(function(){
	    			var r =$("#modifyForm").form("validate");
	    			if(r){
	    				$("#modifyForm").submit();
	    				$("#modifyForm").clear();
	    			}
	    		});
    	}
    	function add(){
    		$("#addRole").window('open');
    		$("#add").click(function(){
    			var r =$("#addForm").form("validate");
    			if(r){
    				$("#addForm").submit();
    				$("#addForm").clear();
    			}
    		});
    	}
    </script>

    </div>
 </div>
</div>
</body>
</html>

