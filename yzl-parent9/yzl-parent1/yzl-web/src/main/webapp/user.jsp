<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkAll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/linkage.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<title>用户管理</title>
</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
  <div class="Manager_style add_user_info">
     <div id="Add_user_style" style="display:none">
    <div class="add_user_style clearfix">
			  <div id="addDate" class="easyui-window" style="height: 340px;width: 800px" data-options="title:'添加数据',draggable:false,closed:true,iconCls:'icon-add',resizable:true,minimizable:false,maximizable:false,closed:true,modal:true,shadow:true">
     	<form id="addForm" action="${pageContext.request.contextPath }/user/addUser" method="post">
    		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">用户登录名</th>
		     		<th style="text-align: center; vertical-align: middle;">密码</th>
		     		<th style="text-align: center; vertical-align: middle;">角色名称</th>
		     		<th style="text-align: center; vertical-align: middle;">用户真实姓名</th>
		     		<th style="text-align: center; vertical-align: middle;">用户岗位</th>
		     		<th style="text-align: center; vertical-align: middle;">性别</th>
		     		<th style="text-align: center; vertical-align: middle;">电子邮箱</th>
		     		<th style="text-align: center; vertical-align: middle;">手机号</th>
		     		<th style="text-align: center; vertical-align: middle;">备注</th>
		     	</tr>
	     	
		     	
		     	<tr align="center">
		     		<td><input id="add_VTArea"       name="username" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea1"       name="password" type="password" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="yzlRole.charnam" class="easyui-combobox"  data-options=" editable:false,valueField: 'id',textField: 'charnam',url:'${pageContext.request.contextPath }/user/role/getRoles'" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea"      name="name" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea"     name="work" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA"      name="sex" class="easyui-combobox" data-options=" editable:false,required:true,valueField: 'id',textField: 'name',data:[{id:'1',name:'男'},{id:'0',name:'女'}]" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA"      name="email" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td>
		     			<script type="text/javascript">
			     			 $(function(){
			     				//添加表单提交
			     					$("#add").click(function(){
				     		 			var r = $("#addForm").form("validate");
				     		 			alert(r);
				     		 			if(r){
				     			 			$("#addForm").submit();
				     			 			$("#addForm").form("clear");
				     		 			}else{
				     		 				alert("请填完所有必须项！");
				     		 			}
			     		 			});
			     				 //手机号校验
			     					var reg = /^1[3|4|5|7|8][0-9]{9}$/;
			     					$.extend($.fn.validatebox.defaults.rules, {    
			     						telephone: {    
			     			     		validator: function(value,param){    
			     			        		 return reg.test(value);    
			     			     		},    
			     			   			  message: '输入电话号码格式错误！'   
			     						}    
			     					});
			     			 });
		     			</script>
		     		<input id="phone" class="easyui-validatebox"   data-options="required:true,validType:'telephone'" name="telephone" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA"      name="remark" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" id="add">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelAdd">取消</button>
		    <script type="text/javascript">
		 		$("#cancelAdd").click(function(){
		 			$("#addDate").window('close');
		 		});
			</script>	
    	</form>
    </div>
    		<!-- 	修改窗口 -->
    			  <div id="modifyDate" class="easyui-window" style="height: 340px;width: 800px" data-options="title:'修改数据',closable:false,draggable:false,closed:true,iconCls:'icon-add',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true">
     	<form id="modifyForm" action="${pageContext.request.contextPath }/user/updateUser" method="post">
<!-- 		     	<tr align="center">
		     		<input id="addc"   name="id" type="hidden"/>
		     		<th style="text-align: center; vertical-align: middle;">用户登录名</th>
		     		<th style="text-align: center; vertical-align: middle;">角色名称</th>
		     		<th style="text-align: center; vertical-align: middle;">用户真实姓名</th>
		     		<th style="text-align: center; vertical-align: middle;">用户岗位</th>
		     		<th style="text-align: center; vertical-align: middle;">性别</th>
		     		<th style="text-align: center; vertical-align: middle;">电子邮箱</th>
		     		<th style="text-align: center; vertical-align: middle;">手机号</th>
		     	</tr> -->
		     	<!-- <tr align="center"> -->
		     		        <div class="fitem">
            <label>姓名：</label>        
		     		<input id="add_VTArea"       name="username" type="text" style="width: 100%; margin-left: -0.2px;">
		     		</div>
		     				     		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="roleCharnam" name="yzlRole.id" class="easyui-combobox"   data-options="editable:false,required:true,valueField: 'id',textField: 'charnam',url:'${pageContext.request.contextPath }/user/role/getRoles'" style="width: 100%; margin-left: -0.2px;"></td>
		     				     </div>		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="add_JobArea"      name="name" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     				     				     </div>		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="add_FileArea"     name="work" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     				     				     </div>		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="add_COCLSIA1"      name="sex" class="easyui-combobox" data-options=" editable:false,required:true,valueField: 'id',textField: 'name',data:[{id:'1',name:'男'},{id:'0',name:'女'}]" style="width: 100%; margin-left: -0.2px;"></td>
		     				     				     </div>		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="add_COCLSIA"      name="email" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     				     				     </div>		        <div class="fitem">
            <label>姓名：</label>  
		     		<td><input id="add_COCLSIA"     name="telephone" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		 </div>
		   <!--   	</tr> -->
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" id="modify">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelModify">取消</button>
		     <script type="text/javascript">
		 		$("#cancelModify").click(function(){
		 			$("#modifyDate").window('close');
		 		});
		     </script>
    	</form>
    </div>
	    </div><!--onclick="return checkNull()"-->
    </div>
    </div>
     <br><br>
     <div style="width: 1300px;height: 500px">
	    <table id="tab">
	    	
	    </table>
    </div>
     
  <script type="text/javascript">
    	$("#tab").datagrid({
    		url:'${pageContext.request.contextPath}/user/pageQuery',
			nowrap:false,//字符太多是否换行
			fit:true,//自适应
			

					toolbar:[
			    				{text:'添加',iconCls:'icon-add',handler:addUser}
			    			],
			    			//双击修改
			    			onDblClickRow:dbEdit,
			//显示序号rownumbers
			rownumbers:true,
			pageList:[10,20,50],//显示记录数
			pagination:true,//显示分页
			//表头
			columns:[
						[
							{align:'center',width:100, title:'序列化',checkbox:true, 	field:'id'},
							{align:'center',width:100, title:'用户登录名',			field:'username'},
							{align:'center',width:100, title:'角色名称',	field:'yzlRole.id',
								formatter: function (value,rows,index) {
									if(rows.yzlRole == null){
										 return "";
										 }
											var val=rows.yzlRole.charnam;
							                return val;
							            }
							},
							{align:'center',width:100, title:'用户真实姓名',	field:'name'},
							{align:'center',width:100, title:'用户状态',	field:'state'		
							
							},
															
							{align:'center',width:100, title:'用户岗位',	field:'work'},
							{align:'center',width:100, title:'性别',	field:'sex',
						formatter: function (value,rows,index) {
									if(rows == null){
										 return "";
										 }
							                	return rows.sex=='1'?"男":"女";
									}
							},
							{align:'center',width:100, title:'电子邮箱',	field:'email'},
							{align:'center',width:100, title:'手机号',	field:'telephone'},
							{align:'center',width:100, title:'备注',		field:'remark'}
						 ]
					]
			
    	});

		//添加数据
		function addUser(){
			$("#addDate").window('open');
		}
		
		//数据回显
	 	function dbEdit(rowIndex, rowData){
	 		if(rowData.yzlRole!=null&&rowData!=null){
				$("#roleCharnam").combobox('select',rowData.yzlRole.charnam);
				$("input[name='yzlRole.id']").val(rowData.roleId);
			}else{
				$("#roleCharnam").combobox('select',"");
			} 

			$("#modifyDate").window('open');
			$("#modifyForm").form('load',rowData);
		} 
			//修改
			$("#modify").click(function(){
	 			var r = $("#modifyForm").form("validate");
	 			if(r){
		 			$("#modifyForm").submit();
		 			//$("#addForm").form("clear");
	 			}else{
	 				alert("请填完所有必须项！");
	 			}
	 			
	 		});

    </script>
     
    </div>
 </div>
<script type="text/javascript">
function hide1(){
document.getElementById('ycxz').style.display='';
document.getElementById('gys_name').style.display='none';
  
}
function display1(){
document.getElementById('ycxz').style.display='none';
document.getElementById('gys_name').style.display='';
} 

 $('.reset_Password').on('click', function(){
	 layer.confirm('是否重置密码，重置后原密码将失效？', {
  btn: ['重置','取消'] //按钮
}, function(){
  layer.msg('重置成功！', {time: 1000,icon: 1});
});

});
</script>
</body>
</html>

