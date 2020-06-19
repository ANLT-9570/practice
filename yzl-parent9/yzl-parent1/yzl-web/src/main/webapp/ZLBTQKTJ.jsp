<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>

<title>xxx</title>
</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">

     <br><br>
     <div style="height: 500px;width: 1180px">
     	<table id="tab"></table>
     </div>
     
     <div id="tb">
    	<shiro:hasPermission name="sys:btqk:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:btqk:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updata()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:btqk:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deldata()">删除</a>
    	</shiro:hasPermission>
    	
    </div>
     
     AfforestationSubsidyBasicConditionS
     <script type="text/javascript">
	     $("#tab").datagrid({ 
				nowrap:false,//字符太多是否换行
				fit:true,//自适应
				url:'${pageContext.request.contextPath}/show_zlb',
				frozenColumns:[[	//冻结在左边
					{title:'序列号',			rowspan:2, align:'center',field:'xl',checkbox:true,width:100,},
					{title:'单位',			rowspan:2, align:'center',field:'name',width:110},
				]],
				//表头
				columns:[
							[
								{title:'试点县(市、区）数',	rowspan:2, align:'center',field:'countynumber',width:120},
								{title:'试点乡(镇、场）数',	rowspan:2, align:'center',field:'villagenumber',width:120},
								{align:'center',width:700,colspan:6,title:'涉及造林主体数量', 		field:''},//colspan表示合并7个单元格 
								{align:'center',width:110,rowspan:2,title:'签订合同份数',			field:'contractnumber'},//rowspan表示合并2行
								{align:'center',width:125,rowspan:2,title:'编制作业设计份数',		field:'compilenumber'},
							 ],
						[
							{rowspan:1, align:'center',width:120, title:'合计', 							field:'count'},
							{rowspan:1, align:'center',width:115, title:'林农(户)',  					field:'agroforestry'},
							{rowspan:1, align:'center',width:130, title:'林业合作组织（个）', 			field:'forestrycooperationorganization'},
							{rowspan:1, align:'center',width:160, title:'承包经营国有林的林业职工（人）',	field:'stateownedforests'},
							{rowspan:1, align:'center',width:115, title:'国有林场（个）',				field:'stateforestfarm'},
							{rowspan:1, align:'center',width:115, title:'其他',							field:'rests'},
						]], 
				//显示序号rownumbers
				rownumbers:true,
				pagination:true,//显示分页
				toolbar:'#tb'/* [
					{iconCls:'icon-add',text:'添加',handler:add},
					{iconCls:'icon-edit',text:'修改',handler:updata},
					{iconCls:'icon-cancel',text:'删除',handler:deldata},
				] */,
			});
			
			function add(){
				$("#addDate").window('open');
			}
			//删除
		    function deldata(){
		   	 var iwass = $('#tab').datagrid('getSelections');
		   	 if(iwass.length == 0){
		   		 $.messager.alert('错误提示','<h3>必须选择一个</h3>','info');
		   		 return;
		   	 }
		   	 var ids=[];
		   	 for(var i=0;i<iwass.length;i++){
		   		 ids.push(iwass[i].id);
		   	 }
		   	 $.messager.confirm('提示框','确定要删除吗 ? ?',function(r){
		   		 if(r == true){
		   			 $.ajax({
		   				 async:false,
		   				 url:'${pageContext.request.contextPath }/delzlb',
		   				 data:{"ids":ids},
		   				 traditional:true,
		   				 type:'post',
		   				 dataType:'json',
		   				 success:function(data){
		   					 if(data.data==200){
		   							$.messager.alert('提示框','<h3>删除成功 ! !</h3>','question');
		   						}else if(data.data==400){
		   							$.messager.alert('错误提示框','<h3>删除出错了 ! !</h3>','info');
		   						}
		   						$('#tab').datagrid('load');
		   				 },
		   				 error:function(){
		   					 $.messager.alert('错误提示','<h3>出错了 ! !</h3>','info');
		   				 }
		   			 });
		   		 }
		   	 });
		    }
		    //全局变量
		    var mk="";
		    //修改窗口
		    function updata(){
		   	 var iwas = $('#tab').datagrid('getSelections');
		   	 if(iwas.length > 1 || iwas.length == 0){
		   		 $.messager.alert('错误提示框','<h4>只能选择一个或不能不选 ! !</h4>','info');
		   		 return;
		   	 }
		   	 $('#updateForm').form('load',iwas[0]);
		   	 $("#updateDate").window('open');
		   	 mk=iwas[0].dbascode;
		    }
		    
				function add(){
					$("#addDate").window('open');
				}
				//添加数据
				function addDates(){
					var dis = $("#ddi").combobox('getValue');
					var obj = $("#addForm").serialize();
					
					console.info(dis);
					if(dis==null || dis == ""){
						$.messager.alert('错误提示','<h3>请选择一个市县 ! !</h3>','info');
						return;
					}
					$.ajax({
						url:'${pageContext.request.contextPath }/addzlb',
						async:false,
						data:$("#addForm").serialize(),
						type:'post',
						dataType:'json',
						success:function(data){
							if(data.data==200){
								$.messager.alert('提示框','<h3>添加成功 ! !</h3>','question');
							}else if(data.data==400){
								$.messager.alert('错误提示框','<h3>添加出错了 ! !</h3>','info');
							}if(data.data==300){
								$.messager.alert('错误提示框','<h3>列表中以存在 ! !</h3>','info');
							}
							$('#addDate').window('close');
							$('#tab').datagrid('load');
							$("#addForm")[0].reset();
						},
						error:function(){
							$.messager.alert('错误提示框','<h2>出错了 ! !</h2>','error');
						}
					});
				}
				//数据修改
				function checkData(){
					var dmk = $('#ct').combobox('getValue');
					if(mk != dmk){
						$.messager.alert('提示框','<h3>不可更改市 ! !</h3>','info');
						$('#ct').combobox('setValue',mk);
						return;
					}
					$.ajax({
						async:false,
						url:'${pageContext.request.contextPath }/editzlb',
						data:$("#updateForm").serialize(),
						type:'post',
						dataType:'json',
						success:function(data){
							if(data.data==200){
								$.messager.alert('提示框','<h3>修改成功 ! !</h3>','question');
							}else if(data.data==400){
								$.messager.alert('错误提示框','<h3>修改出错了 ! !</h3>','info');
							}
							$('#updateDate').window('close');
							$('#tab').datagrid('load');
						},
						error:function(){
							$.messager.alert('错误提示框','<h3>出错了 ! !</h3>','info');
						}
					});
				}
     </script>
     
     <!-- 添加数据div -->
     <div id="addDate" class="easyui-window" style="height: 350px;width: 800px" data-options="title:'数据写入',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="addForm" action="">
    		<label class="label_name">选择单位:</label>&nbsp;&nbsp;&nbsp;
    	   
	        	<label class="label_name">市,县名:</label>
						&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" id="ddi" name="dbascode" data-options="url:'${pageContext.request.contextPath }/show_diss',
			  		mode:'remote',valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<br><br>
    
		     <table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">县(市、区）数</th>
		     		<th style="text-align: center; vertical-align: middle;">乡(镇、场）数</th>
		     		<th style="text-align: center; vertical-align: middle;">林农(户)</th>
		     		<th style="text-align: center; vertical-align: middle;">林业合作组织（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">承包经营国有林的林业职工（人）</th>
		     		<th style="text-align: center; vertical-align: middle;">国有林场（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">其他（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">签订合同份数</th>
		     		<th style="text-align: center; vertical-align: middle;">编制作业设计份数</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="countynumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="villagenumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="agroforestry" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="forestrycooperationorganization" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="stateownedforests" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_WBArea" name="stateforestfarm" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DFArea" name="rests" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VFArea" name="contractnumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_TMArea" name="compilenumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table><br><br>
		     <input style="margin-left: 300px" class="btn btn-primary" type="button" value="添加" onclick="addDates()">
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
     	</form>
     </div>
  </div>
 
 <!-- 修改数据div -->
     <div id="updateDate" class="easyui-window" style="height: 350px;width: 800px" data-options="title:'数据写入',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="updateForm" action="">
    		<label class="label_name">选择单位:</label>&nbsp;&nbsp;&nbsp;
    	   
	        	<label class="label_name">市名:</label>
						&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" id="ct" name="dbascode" data-options="url:'${pageContext.request.contextPath }/show_diss',
			  		valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		<input type="hidden" name="id">
        		<br>
    		 <br>
		     <table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">县(市、区）数</th>
		     		<th style="text-align: center; vertical-align: middle;">乡(镇、场）数</th>
		     		<th style="text-align: center; vertical-align: middle;">林农(户)</th>
		     		<th style="text-align: center; vertical-align: middle;">林业合作组织（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">承包经营国有林的林业职工（人）</th>
		     		<th style="text-align: center; vertical-align: middle;">国有林场（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">其他（个）</th>
		     		<th style="text-align: center; vertical-align: middle;">签订合同份数</th>
		     		<th style="text-align: center; vertical-align: middle;">编制作业设计份数</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="countynumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="villagenumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="agroforestry" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="forestrycooperationorganization" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="stateownedforests" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_WBArea" name="stateforestfarm" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DFArea" name="rests" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VFArea" name="contractnumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_TMArea" name="compilenumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table><br><br>
		     <br><br>
		     <input style="margin-left: 300px" class="btn btn-primary" type="button" value="修改" onclick="checkData()" >
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
     	</form>
     </div>
     
<script type="text/javascript">

//取消按钮
$("#qx").click(function(){
	$("#addDate").window('close');
});
$("#uqx").click(function(){
	$("#updateDate").window('close');
});


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
