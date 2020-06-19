<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
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
<title>xxx</title>

</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
     <br><br>
   <div style="width:1180px;height:500px;  margin-top:-20.5px; padding-top: 0px">
    	<table id="tab"></table>
    </div>
    
    <div id="tb">
     	
    	<shiro:hasPermission name="sys:xbjcys:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:xbjcys:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updata()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:xbjcys:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deldata()">删除</a>
    	</shiro:hasPermission>
    	
    </div>
    
    <script type="text/javascript">
    		$("#tab").datagrid({ 
    			border:2,//边框大小
    			nowrap:false,//字符太多是否换行
    			fit:true,//自适应
    			url:'${pageContext.request.contextPath}/show_ss',
    			frozenColumns:[[	//冻结在左边
    							{title:'市名',align:'center',field:'city',width:60},
    							{title:'县名',align:'center',field:'count',width:80},
    							{title:'乡',align:'center',field:'town',width:80},
    							{title:'村(林班)',align:'center',field:'villages',width:80},
    						
    						]],
    			
    			//表头
    			columns:[
    						[
    							{align:'center',width:100,rowspan:2,title:'经度',			field:'longitude'},//rowspan表示合并2行
    							{align:'center',width:100,rowspan:2,title:'纬度',		field:'latitude'},
    							{align:'center',width:100,rowspan:2,title:'地区类别',	field:'regionalcategory'},
    							{align:'center',width:100,rowspan:2,title:'小班号',		field:'smallnumber'},
    							{align:'center',width:100,rowspan:2,title:'图幅号',		field:'sheetdesignation'},
    							{align:'center',width:100,rowspan:2,title:'坡度',		field:'gradient'},
    							{align:'center',width:100,rowspan:2,title:'计划年度',	field:'planannual'},
    							{align:'center',width:100,rowspan:2,title:'作业年度',		field:'homeworkannual'},
    							{align:'center',width:100,rowspan:2,title:'退耕地类别',		field:'pittype'},
    							{align:'center',width:100,rowspan:2,title:'林地权属',			field:'forestlandownership'},
    							{align:'center',width:100,rowspan:2,title:'林木权属',				field:'forestownership'},
    							{align:'center',width:100,rowspan:2,title:'林种',				field:'forestcategory'},
    							{align:'center',width:100,rowspan:2,title:'树种',					field:'varietiestrees'},
    							{align:'center',width:100,rowspan:2,title:'植被配置类型',					field:'vegetationallocationtype'},
    							{align:'center',width:100,rowspan:2,title:'上报面积',					field:'reportarea'}, 
    							{align:'center',width:100,rowspan:2,title:'核实面积',					field:'verifyarea'}, 
    							{align:'center',width:100,rowspan:2,title:'不核实原因',					field:'causeverified'}, 
    							{align:'center',width:100,colspan:3,title:'成活率',					field:''}, 
    							{align:'center',width:100,colspan:4,title:'核实面积等级',					field:''}, 
    							{align:'center',width:100,rowspan:2,title:'待补植原因',					field:'causereplanted'}, 
    							{align:'center',width:100,rowspan:2,title:'失败原因',					field:'causefailure'}, 
    							{align:'center',width:100,rowspan:2,title:'损失原因',					field:'reasonsloss'}, 
    							{align:'center',width:100,colspan:6,title:'管理情况(核实面积)',					field:''}, 
    							{align:'center',width:100,rowspan:2,title:'验收人',					field:'acceptor'}, 
    							{align:'center',width:100,rowspan:2,title:'验收年度',					field:'acceptanceannual'} 
    						 ],
    					[
    						{rowspan:1, align:'center',width:100, title:'合格', 				field:'qualified'},
    						{rowspan:1, align:'center',width:100, title:'待补植',  	field:'forreplanting'},
    						{rowspan:1, align:'center',width:100, title:'失败', 			field:'defeated'},
    						{rowspan:1, align:'center',width:100, title:'合格面积',	field:'qualifiedarea'},
    						{rowspan:1, align:'center',width:100, title:'待补植面积',		field:'unreplantedarea'},
    						{rowspan:1, align:'center',width:100, title:'失败面积',			field:'failurearea'},
    						{rowspan:1, align:'center',width:100, title:'损失面积',	field:'lossarea'},
    						{rowspan:1, align:'center',width:100, title:'设计',	field:'design'},
    						{rowspan:1, align:'center',width:100, title:'验收',	field:'accept'},
    						{rowspan:1, align:'center',width:100, title:'档案',	field:'record'},
    						{rowspan:1, align:'center',width:100, title:'管护',	field:'manageprotect'},
    						{rowspan:1, align:'center',width:100, title:'抚育面积',	field:'barearea'},
    						{rowspan:1, align:'center',width:100, title:'不动产权证书',	field:'realestatecertificate'}
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
   	   				 url:'${pageContext.request.contextPath }/delss',
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
   	   	 mk=iwas[0].dsscode;
   	    }
   	    
   			function add(){
   				$("#addDate").window('open');
   			}
   			//添加数据
   			function addDates(){
   				var dis = $("#ddi").combobox('getValue');
   				console.info(dis);
   				if(dis==null || dis == ""){
   					$.messager.alert('错误提示','<h3>请选择一个市县 ! !</h3>','info');
   					return;
   				}
   				$.ajax({
   					url:'${pageContext.request.contextPath }/addss',
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
   					url:'${pageContext.request.contextPath }/editss',
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
 
 	<div id="addDate" class="easyui-window" style="height: 450px;width: 900px" data-options="title:'数据写入',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="addForm">
     		
     		<label class="label_name">市名:</label>
						&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" id="ddi" name="dsscode" data-options="url:'${pageContext.request.contextPath }/show_diss',
			  		mode:'remote',valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		
     		<br><br>
 			<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">乡</th>
		     		<th style="text-align: center; vertical-align: middle;">村（林班）</th>
		     		<th style="text-align: center; vertical-align: middle;">经度</th>
		     		<th style="text-align: center; vertical-align: middle;">纬度</th>
		     		<th style="text-align: center; vertical-align: middle;">地区类别</th>
		     		<th style="text-align: center; vertical-align: middle;">小班号</th>
		     		<th style="text-align: center; vertical-align: middle;">图幅号</th>
		     		<th style="text-align: center; vertical-align: middle;">坡度</th>
		     		<th style="text-align: center; vertical-align: middle;">计划年度</th>
		     		<th style="text-align: center; vertical-align: middle;">作业年度</th>
		     		<th style="text-align: center; vertical-align: middle;">退坑地类别</th>
		     		<th style="text-align: center; vertical-align: middle;">林地权属</th>
		     		<th style="text-align: center; vertical-align: middle;">林木权属</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="town" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="villages" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="longitude" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="latitude" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="regionalcategory" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="smallnumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="sheetdesignation" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="gradient" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="planannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="homeworkannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="pittype" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="forestlandownership" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="forestownership" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     	<tr><th></th></tr>
		     	
		     	<tr>
		     		<th style="text-align: center; vertical-align: middle;">林种</th>
		     		<th style="text-align: center; vertical-align: middle;">树种</th>
		     		<th style="text-align: center; vertical-align: middle;">植被配置类型</th>
		     		<th style="text-align: center; vertical-align: middle;">上报面积</th>
		     		<th style="text-align: center; vertical-align: middle;">核实面积</th>
		     		<th style="text-align: center; vertical-align: middle;">不核实原因</th>
		     		<th style="text-align: center; vertical-align: middle;">合格</th>
		     		<th style="text-align: center; vertical-align: middle;">待补植</th>
		     		<th style="text-align: center; vertical-align: middle;">失败</th>
		     		<th style="text-align: center; vertical-align: middle;">合格面积</th>
		     		<th style="text-align: center; vertical-align: middle;">待补植面积</th>
		     		<th style="text-align: center; vertical-align: middle;">失败面积</th>
		     		<th style="text-align: center; vertical-align: middle;">损失面积</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="forestcategory" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="varietiestrees" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="vegetationallocationtype" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="reportarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="verifyarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="causeverified" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="qualified" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="forreplanting" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="defeated" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="qualifiedarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="unreplantedarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="failurearea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="lossarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     	<tr><th></th></tr>
		     	
		     	<tr>	
		     		
		     		<th style="text-align: center; vertical-align: middle;">待补植原因</th>
		     		<th style="text-align: center; vertical-align: middle;">失败原因</th>
		     		<th style="text-align: center; vertical-align: middle;">损失原因</th>
		     		<th style="text-align: center; vertical-align: middle;">设置</th>
		     		<th style="text-align: center; vertical-align: middle;">验收</th>
		     		<th style="text-align: center; vertical-align: middle;">档案</th>
		     		<th style="text-align: center; vertical-align: middle;">管护</th>
		     		<th style="text-align: center; vertical-align: middle;">抚育面积</th>
		     		<th style="text-align: center; vertical-align: middle;">不动产权证书</th>
		     		<th style="text-align: center; vertical-align: middle;">验收人</th>
		     		<th style="text-align: center; vertical-align: middle;">验收年度</th>
		     	</tr>
		     	<tr align="center">
		     		
		     		<td><input id="add_FileArea" name="causereplanted" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="causefailure" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="reasonsloss" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="design" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="accept" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="record" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="manageprotect" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="barearea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="realestatecertificate" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="acceptor" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="acceptanceannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     
		    <input style="margin-left: 300px" class="btn btn-primary" type="button" value="添加" onclick="addDates()">
			<button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
 		</form>
 	</div>
 
 	<div id="updateDate" class="easyui-window" style="height: 550px;width: 800px" data-options="title:'修改数据',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="updateForm">
     	
 			<label class="label_name">市名:</label>
						&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" id="ct" name="dsscode" data-options="url:'${pageContext.request.contextPath }/show_diss',
			  		mode:'remote',valueField:'dcode',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input name="id" type="hidden">
     		<br><br>
 			<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">乡</th>
		     		<th style="text-align: center; vertical-align: middle;">村（林班）</th>
		     		<th style="text-align: center; vertical-align: middle;">经度</th>
		     		<th style="text-align: center; vertical-align: middle;">纬度</th>
		     		<th style="text-align: center; vertical-align: middle;">地区类别</th>
		     		<th style="text-align: center; vertical-align: middle;">小班号</th>
		     		<th style="text-align: center; vertical-align: middle;">图幅号</th>
		     		<th style="text-align: center; vertical-align: middle;">坡度</th>
		     		<th style="text-align: center; vertical-align: middle;">计划年度</th>
		     		<th style="text-align: center; vertical-align: middle;">作业年度</th>
		     		<th style="text-align: center; vertical-align: middle;">退坑地类别</th>
		     		<th style="text-align: center; vertical-align: middle;">林地权属</th>
		     		<th style="text-align: center; vertical-align: middle;">林木权属</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="town" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="villages" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="longitude" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="latitude" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="regionalcategory" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="smallnumber" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="sheetdesignation" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="gradient" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="planannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="homeworkannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="pittype" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="forestlandownership" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="forestownership" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     	<tr><th></th></tr>
		     	
		     	<tr>
		     		<th style="text-align: center; vertical-align: middle;">林种</th>
		     		<th style="text-align: center; vertical-align: middle;">树种</th>
		     		<th style="text-align: center; vertical-align: middle;">植被配置类型</th>
		     		<th style="text-align: center; vertical-align: middle;">上报面积</th>
		     		<th style="text-align: center; vertical-align: middle;">核实面积</th>
		     		<th style="text-align: center; vertical-align: middle;">不核实原因</th>
		     		<th style="text-align: center; vertical-align: middle;">合格</th>
		     		<th style="text-align: center; vertical-align: middle;">待补植</th>
		     		<th style="text-align: center; vertical-align: middle;">失败</th>
		     		<th style="text-align: center; vertical-align: middle;">合格面积</th>
		     		<th style="text-align: center; vertical-align: middle;">待补植面积</th>
		     		<th style="text-align: center; vertical-align: middle;">失败面积</th>
		     		<th style="text-align: center; vertical-align: middle;">损失面积</th>
		     	</tr>
		     	<tr align="center">
		     		<td><input id="add_VTArea" name="forestcategory" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="varietiestrees" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="vegetationallocationtype" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="reportarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_FileArea" name="verifyarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="causeverified" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="qualified" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="forreplanting" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="defeated" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="qualifiedarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="unreplantedarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="failurearea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="lossarea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     	<tr><th></th></tr>
		     	
		     	<tr>	
		     		
		     		<th style="text-align: center; vertical-align: middle;">待补植原因</th>
		     		<th style="text-align: center; vertical-align: middle;">失败原因</th>
		     		<th style="text-align: center; vertical-align: middle;">损失原因</th>
		     		<th style="text-align: center; vertical-align: middle;">设置</th>
		     		<th style="text-align: center; vertical-align: middle;">验收</th>
		     		<th style="text-align: center; vertical-align: middle;">档案</th>
		     		<th style="text-align: center; vertical-align: middle;">管护</th>
		     		<th style="text-align: center; vertical-align: middle;">抚育面积</th>
		     		<th style="text-align: center; vertical-align: middle;">不动产权证书</th>
		     		<th style="text-align: center; vertical-align: middle;">验收人</th>
		     		<th style="text-align: center; vertical-align: middle;">验收年度</th>
		     	</tr>
		     	<tr align="center">
		     		
		     		<td><input id="add_FileArea" name="causereplanted" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="causefailure" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="reasonsloss" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="design" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="accept" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_COCLSIA" name="record" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VTArea" name="manageprotect" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_VerifiedArea" name="barearea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_JobArea" name="realestatecertificate" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="acceptor" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input id="add_DTCAATTW" name="acceptanceannual" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     </table>
		     
		    <input style="margin-left: 300px" class="btn btn-primary" type="button" value="修改" onclick="checkData()" >
			<button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
 		</form>
 	</div>
 
 </div>
 </div>
<script type="text/javascript">
$("#qx").click(function(){
	$("#addDate").window('close');
	$("#addForm")[0].reset();
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
