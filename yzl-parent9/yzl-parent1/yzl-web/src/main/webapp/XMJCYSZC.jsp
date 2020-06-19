<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jquery-ui-1.10.3.full.min.css">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
  <style type="text/css">
  
  .datagrid-header,
.datagrid-td-rownumber {
background-color: #efefef;
background: -webkit-linear-gradient(top,#F9F9F9 0,#efefef 100%);
background: -moz-linear-gradient(top,#F9F9F9 0,#efefef 100%);
background: -o-linear-gradient(top,#F9F9F9 0,#efefef 100%);
background: linear-gradient(to bottom,#F9F9F9 0,#efefef 100%);
background-repeat: repeat-x;
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#F9F9F9,endColorstr=#efefef,GradientType=0);
} 
  </style>
<title>xxx</title>
</head>

<body>
<div style="width:1099px;" class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
  <br><br>
  
  <div style="height: 480px;width: 1180px">
    <table id="tab"></table>
    </div>
    <script type="text/javascript">
    		$("#tab").datagrid({
    			singleSelect:false,
    			nowrap:false,//字符太多是否换行
    			fit:true,//自适应
    			url:'${pageContext.request.contextPath}/show_Project',
 
      			frozenColumns:[[	//冻结在左边
        							{title:'市名',rowspan:2,align:'center',field:'city',width:60},
        							{title:'县名',rowspan:2,align:'center',field:'county',width:80}
        						]],
    			//表头
    			columns:[
    						[
    					
    							{align:'center',width:100,rowspan:2,title:'项目名称',	field:'projectname'},
    							{align:'center',width:100,rowspan:2,title:'项目规模',		field:'projectscale'},
    							{align:'center',width:100,rowspan:2	,title:'补助资金（万元）',		field:'subsidyfunds'},
    							{align:'center',width:115,rowspan:2,title:'项目总投资（万元）',		field:'totalprojectinvestment'},
    							{align:'center',width:100,colspan:3,title:'项目产值（万元）',		field:''},
    							{align:'center',width:100,rowspan:2,title:'项目利润',		field:'projectprofit'},
    							{align:'center',width:110,rowspan:2,title:'补助资金发放情况',		field:'grantsubsidy'},
    							{align:'center',width:100,rowspan:2,title:'林农户均年增收（元）',		field:'yearsincome'},
    							{align:'center',width:165,rowspan:2,title:'典型农户增收资料是否已收集',		field:'ifgather'},
    							{align:'center',width:115,rowspan:2,title:'台账资料是否齐全',		field:'ifmany'},
    							{align:'center',width:100,rowspan:2,title:'任务完成情况',		field:'performance'},
    							{align:'center',width:100,rowspan:2,title:'备注',		field:'remark'}
    	
    						 ],
    					[
    						{rowspan:1, align:'center',width:100, title:'合计', 				field:'subtotal'},
    						{rowspan:1, align:'center',width:100, title:'林下部分产值',  	field:'forestsoutput'},
    						{rowspan:1, align:'center',width:100, title:'林上部分产值', 			field:'linoutput'},
    					]], 
    			//显示序号rownumbers
    			rownumbers:true,
    			pagination:true,//显示分页
    			toolbar:[
					{text:'添加',iconCls:'icon-add',handler:addDate},
					{text:'删除',iconCls:'icon-cancel',handler:delDate},
					{text:'修改',iconCls:'icon-edit',handler:editDate},
					{text:'<input id="sear" value="" class="easyui-searchbox" style="width:152px;height:19px;margin-top: -10px">'}
				],
				//双击修改
				/* onDblClickRow:dbEdit */
		 	});
    		$('#sear').searchbox({
    			searcher:searched
    		});
    		//查询
    		function searched(value,name){
    			$('#tab').datagrid('load',{"value":value});
    		}
			//删除
			function delDate(){
				var pap = $('#tab').datagrid('getSelections');
				
				if(pap.length == 0 || pap == null){
					$.messager.alert('错误提示框','必须选择一个 ! !','info');
					return;
				}
				
				var dpapcodes=[];
				
				for(var i=0;i<pap.length;i++){
					dpapcodes.push(pap[i].dpapcode);
				}
				$.ajax({
					url:'${pageContext.request.contextPath}/delXMJC',
					traditional:true,
					data:{'dpapcodes':dpapcodes},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.data==200){
							$.messager.alert('提示框','删除成功 ! !','question');
						}else if(data.data==400){
							$.messager.alert('提示框','删除出错了 ! !','info');
						}
						$('#tab').datagrid('load');
					},
					error:function(){
						$.messager.alert('错误提示框','出错了 ! !','info');
					}
				});
			}
			//添加数据
			function addDate(){
				$("#addDate").window('open');
			}
			//修改
			var did = "";//定义一个全局变量用于判断地区有没有改变
			function editDate(){
				var projs = $('#tab').datagrid('getSelections');
				if(projs.length > 1 || projs.length == 0){
					$.messager.alert('错误提示框','只能选择一个或者不能不选 ! !','info');
					return;
				}
				$("#updateDate").window('open');
				$("#updateForm").form('load',projs[0]);
				did = projs[0].dpapcode;
			}
			//添加校验
			function checkProjAp(){
				var ct = $("#CC").combobox('getValue');
				if(ct == null || ct == ""){
					$.messager.alert('错误提示','市县名称不能为空 ! !','info');
					return false;
				}
				return true;
			}
			//数据修改
			function editProjs(){
				//获取地区编号
				var dcode = $('#edid').combobox('getValue');
				if(did != dcode){
					$.messager.alert('错误提示','市县名称不可更改! !','info');
					$("#edid").combobox('setValue',did);
					return;
				}
				$.ajax({
					async:false,
					url:'${pageContext.request.contextPath }/editProjs',
					data:$("#updateForm").serialize(),
					type:'post',
					success:function(data){
						if(data.data==200){
							$.messager.alert('提示框','修改成功 ! !','question');
						}else if(data.data==400){
							$.messager.alert('提示框','修改出错了 ! !','info');
						}
						$('#updateDate').window('close');
						$('#tab').datagrid('load');
					},
					error:function(){
						$.messager.alert('错误提示','修改出错了! !','info');
					},
					dataType:'json'
				});
			}
    </script>
    <!-- 添加 -->
    <div id="addDate" class="easyui-window" style="height: 450px;width: 850px" data-options="title:'数据写入',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
    	<form id="addForm" action="${pageContext.request.contextPath }/addProjectAP" method="post" onsubmit="return checkProjAp()">
    		
    		<label class="label_name">市，县名:</label>
				&nbsp;&nbsp;&nbsp;
				<input id="CC" class="easyui-combobox" name="dpapcode" data-options="url:'${pageContext.request.contextPath }/show_districts',
				  		mode:'remote',valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		
    		<br><br>
    		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">项目名称</th>
		     		<th style="text-align: center; vertical-align: middle;">项目规模</th>
		     		<th style="text-align: center; vertical-align: middle;">补助资金（万元）</th>
		     		<th style="text-align: center; vertical-align: middle;">项目总投资（万元）</th>
		     		<th style="text-align: center; vertical-align: middle;">林下部分产值</th>
		     		<th style="text-align: center; vertical-align: middle;">林上部分产值</th>
		     		<th style="text-align: center; vertical-align: middle;">项目利润</th>
		     		
		     	</tr>
		     	
		     	<tr>
		     		<td><input id="add_VTArea" name="projectname" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="projectscale" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="subsidyfunds" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="totalprojectinvestment" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="forestsoutput" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="linoutput" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="projectprofit" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	<tr><th></th></tr>
		     	<tr align="center">	
		     		<th style="text-align: center; vertical-align: middle;">补助资金发放情况</th>
		     		<th style="text-align: center; vertical-align: middle;">林农户均年增收（元）</th>
		     		<th style="text-align: center; vertical-align: middle;">典型农户增收资料是否已收集</th>
		     		<th style="text-align: center; vertical-align: middle;">台账资料是否齐全</th>
		     		<th style="text-align: center; vertical-align: middle;">任务完成情况</th>
		     		<th style="text-align: center; vertical-align: middle;">备注</th>
		     	</tr>
		     	
		     	<tr align="center">
		     		<td><input id="add_COCLSIA" name="grantsubsidy" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="yearsincome" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="ifgather" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="ifmany" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="performance" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="remark" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     <br><br><br>
		     <input style="margin-left: 300px" class="btn btn-primary" type="submit">
		     <!-- <button style="margin-left: 300px" class="btn btn-primary" type="button" >添加</button> -->
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
    	</form>
    </div>
    
    <!-- 修改 -->
    <div id="updateDate" class="easyui-window" style="height: 450px;width: 850px" data-options="title:'数据修改',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
    	<form id="updateForm">
    		
    		<label class="label_name">市，县名:</label>
				&nbsp;&nbsp;&nbsp;
				<input id="edid" class="easyui-combobox" type="text" name="dpapcode" data-options="url:'${pageContext.request.contextPath }/show_districts',
				  		mode:'remote',valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		
    		<br><br>
    		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">项目名称</th>
		     		<th style="text-align: center; vertical-align: middle;">项目规模</th>
		     		<th style="text-align: center; vertical-align: middle;">补助资金（万元）</th>
		     		<th style="text-align: center; vertical-align: middle;">项目总投资（万元）</th>
		     		<th style="text-align: center; vertical-align: middle;">林下部分产值</th>
		     		<th style="text-align: center; vertical-align: middle;">林上部分产值</th>
		     		<th style="text-align: center; vertical-align: middle;">项目利润</th>
		     		
		     	</tr>
		     	
		     	<tr>
		     		<td><input id="add_VTArea" name="projectname" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="projectscale" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="subsidyfunds" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="totalprojectinvestment" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="forestsoutput" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="linoutput" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="projectprofit" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	<tr><th></th></tr>
		     	<tr align="center">	
		     		<th style="text-align: center; vertical-align: middle;">补助资金发放情况</th>
		     		<th style="text-align: center; vertical-align: middle;">林农户均年增收（元）</th>
		     		<th style="text-align: center; vertical-align: middle;">典型农户增收资料是否已收集</th>
		     		<th style="text-align: center; vertical-align: middle;">台账资料是否齐全</th>
		     		<th style="text-align: center; vertical-align: middle;">任务完成情况</th>
		     		<th style="text-align: center; vertical-align: middle;">备注</th>
		     	</tr>
		     	
		     	<tr align="center">
		     		<td><input id="add_COCLSIA" name="grantsubsidy" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="yearsincome" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="ifgather" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="ifmany" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="performance" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="remark" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     <br><br><br>
		     <input style="margin-left: 300px" class="btn btn-primary" type="button" value="修改" onclick="editProjs()">
		     <!-- <button style="margin-left: 300px" class="btn btn-primary" type="button" >修改</button> -->
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
    	</form>
    </div>
    
 </div>
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
