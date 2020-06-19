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
  
<!-- 导入jquery核心类库 -->
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
  <div class="Manager_style add_user_info">
  <form id="form1" name="form1" method="post" action="">
    <div class="title_name">crud操作</div>
     
     &nbsp;&nbsp;&nbsp;
     <label class="label_name">市名:</label>
		&nbsp;&nbsp;&nbsp;
		   
		  <input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		  valueField:'id',textField:'name',height:30,width:190">
		   
		&nbsp;&nbsp;&nbsp;
	<label class="label_name">工程名:</label>
		&nbsp;&nbsp;&nbsp;
		   <input class="easyui-combobox" value="请选择工程" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
		  valueField:'id',textField:'ename',height:30,width:190">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="btn btn-primary" id="search" type="submit">查询</button>
		
     <div id="Add_user_style" style="display:none">
    <div class="add_user_style clearfix">
    
     </div>
     </div>
     </form>
     
     
    </div>
    </div>
    </div>
     <br><br>
    <div style="margin-left:20px; width: 1299px;height: 500px">
	    <table id="tab">
	    	
	    </table>
    </div>
    <script type="text/javascript">
    	$("#tab").datagrid({
    		url:'${pageContext.request.contextPath}/json/tab.json',
    		border:2,//边框大小
    		
			nowrap:false,//字符太多是否换行
			fit:true,//自适应
			//url:'${pageContext.request.contextPath}/json/data.json',
			frozenColumns:[[	//冻结在左边
							{title:'单位',align:'center',rowspan:3,field:'dname',width:60},
							{title:'项目',align:'center',rowspan:3,field:'ename',width:80}
						]],
			
			//表头
			columns:[
						[
							{align:'center',width:700,colspan:11, title:'抽查情况', 			field:''},//colspan表示合并7个单元格 
							{align:'center',width:300,colspan:3,  title:'检查质量情况',			field:''},//rowspan表示合并2行
							{align:'center',width:1300,colspan:13, title:'任务完成情况',	field:''},
							{align:'center',width:900,colspan:9,  title:'管理指标',	field:''},
						 ],
					[
						{rowspan:2, align:'center',width:100, title:'自检上报面积', 		field:'sIRArea'},
						{rowspan:2, align:'center',width:100, title:'核实面积',  		field:'vTArea'},
						{rowspan:2, align:'center',width:100, title:'核实合格面积', 		field:'verifiedArea'},
						{colspan:8, align:'center',width:800, title:'管理情况（核实）',	field:''},
						
						{rowspan:2, align:'center',width:100, title:'面积核实率',		field:'areaYield'},
						{rowspan:2, align:'center',width:100, title:'核实面积合格率',	field:'vAQRate'},
						{rowspan:2, align:'center',width:100, title:'上报面积合格率',	field:'rAQRate'},

						{rowspan:2, align:'center',width:100, title:'计划任务',			field:'scheduledTask'},						
						{rowspan:2, align:'center',width:100, title:'任务完成率',		field:'mCRate'},						
						{rowspan:2, align:'center',width:100, title:'当年上报合格面积',	field:'rQAITYear'},						
						{rowspan:2, align:'center',width:100, title:'当年上报面积保存率',	field:'rARRITYear'},						
						{rowspan:2, align:'center',width:100, title:'当年上报成效合格率',	field:'tSTOTYear'},						
						{rowspan:2, align:'center',width:100, title:'推算核实面积',		field:'eVArea'},						
						{rowspan:2, align:'center',width:100, title:'推算完成合格面积',	field:'cTCArea'},		
						{rowspan:2, align:'center',width:100, title:'推算成效合格面积',	field:'cTEArea'},		
						{rowspan:2, align:'center',width:100, title:'推算保存合格面积',	field:'cTSArea'},		
						{rowspan:2, align:'center',width:100, title:'全县自检合格面积',	field:'cSCArea'},		
						{rowspan:2, align:'center',width:100, title:'自检保存合格面积',	field:'kTQArea'},		
						{rowspan:2, align:'center',width:100, title:'封育当年上报合格面积',	field:'tRQArea'},		
						{rowspan:2, align:'center',width:100, title:'造林当年上报合格面积',	field:'rTQArea'},	
						
						{rowspan:2, align:'center',width:100, title:'作业设计率',	field:'jobDesignRate'},		
						{rowspan:2, align:'center',width:100, title:'按设计施工率',	field:'aTCRate'},		
						{rowspan:2, align:'center',width:100, title:'建档率',	field:'bIRate'},		
						{rowspan:2, align:'center',width:100, title:'自检率',	field:'sCRate'},		
						{rowspan:2, align:'center',width:100, title:'自查率',	field:'sCKRate'},		
						{rowspan:2, align:'center',width:100, title:'育林率',	field:'aRate'},		
						{rowspan:2, align:'center',width:100, title:'抚育率',	field:'raisingRate'},		
						{rowspan:2, align:'center',width:100, title:'档案率',	field:'fileRate'},		
						{rowspan:2, align:'center',width:100, title:'管护率',	field:'tMRate'},		
						
					],
					[
						{rowspan:1, align:'center',width:100, title:'作业设计面积',			field:'jobArea'},
						{rowspan:1, align:'center',width:100, title:'按作业设计施工面积',	field:'dTCAATTW'},
						{rowspan:1, align:'center',width:100, title:'有档案面积',			field:'fileArea'},
						{rowspan:1, align:'center',width:100, title:'开展县级自检面积',	field:'cOCLSIA'},
						{rowspan:1, align:'center',width:100, title:'设计育林面积',	field:'dFAreas'},
						{rowspan:1, align:'center',width:100, title:'核实育林面积',	field:'vFArea'},
						{rowspan:1, align:'center',width:100, title:'抚育面积',	field:'wBArea'},
						{rowspan:1, align:'center',width:100, title:'管护面积',	field:'tMArea'},
						
					]
					], 
			//显示序号rownumbers
			rownumbers:true,
			pagination:true,//显示分页
			toolbar:[
				{iconCls:'icon-add',handler:function(){
					$('#addDate').window('open');
					/* $('#addDate').dialog({//弹出框
						title:'数据写入',
						width:850,
						height:535,
						closed: false,
						modal:true,
						shadow:false,
						draggable:false
					}); */
				},text:'添加'}
			],
			onDblClickRow:function(rowIndex, rowData){
				$("#updateDate").window('open');
				$("#editFrom").form('load',rowData);
			}
    	});
    	
    </script>
    
    <!-- 添加的div -->
    <div id="addDate" class="easyui-window" style="width: 830px;height: 530px" data-options="modal:true,minimizable:false,maximizable:false,title:'数据写入',resizable:false,draggable:false,shadow:false,iconCls:'icon-add',closed:true">
    <!-- <div id="addDate">	 -->
    	<form id="addFrom" action="">
    		<label class="label_name">选择单位和工程:</label>&nbsp;&nbsp;&nbsp;
    	   
    	   		<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		 		valueField:'id',textField:'name',height:30,width:190">
    	   
           		&nbsp;&nbsp;&nbsp;
           		<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		  			valueField:'id',textField:'name',height:30,width:190">
           
        			&nbsp;&nbsp;&nbsp;
           		<input id="com" class="easyui-combobox" value="请选择工程" data-options="url:'${pageContext.request.contextPath }/json/工程.json',valueField:'id',textField:'ename',height:30,width:190">
           
		        <br>
				<br>
		     <table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;">自检上报面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">核实面积</th>
				   <th style="text-align: center; vertical-align: middle;">核实合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">作业设置面积</th>
				   <th style="text-align: center; vertical-align: middle;">按作业设置施工面积</th>
				   <th style="text-align: center; vertical-align: middle;">有档案面积</th>
				   <th style="text-align: center; vertical-align: middle;">开展县级自检面积</th>
				   <th style="text-align: center; vertical-align: middle;">抚育面积</th>
				   <th style="text-align: center; vertical-align: middle;">设置育林面积</th>
				   <th style="text-align: center; vertical-align: middle;">核实育林面积</th>
				   <th style="text-align: center; vertical-align: middle;">管护面积</th>
				   <th style="text-align: center; vertical-align: middle;">面积核实率</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="sIRArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="vTArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="verifiedArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="jobArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="dTCAATTW" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="fileArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cOCLSIA" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="wBArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="dFArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="vFArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tMArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="areaYield" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     	
		     	<tr>
		     		<th></th>
		     	</tr>
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;">核实面积合格率</th>
		     	   <th style="text-align: center; vertical-align: middle;">上报面积合格率</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报面积保存率</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报成效合格率</th>
				   <th style="text-align: center; vertical-align: middle;">推算核实面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算完成合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算成效合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算保存合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">计划任务</th>
				   <th style="text-align: center; vertical-align: middle;">任务完成率</th>
				   <th style="text-align: center; vertical-align: middle;">全县自检合格面积</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="vAQRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rAQRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rQAITYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rARRITYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tSTOTYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="eVArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTCArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTEArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTSArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="scheduledTask" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="mCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cSCArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     	
		     	<tr>
		     		<th></th>
		     	</tr>
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;">自检保存合格面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">封育当年上报合格面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">造林当年上报合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">作业设计率</th>
				   <th style="text-align: center; vertical-align: middle;">按设计施工率</th>
				   <th style="text-align: center; vertical-align: middle;">建档率</th>
				   <th style="text-align: center; vertical-align: middle;">自检率</th>
				   <th style="text-align: center; vertical-align: middle;">自查率</th>
				   <th style="text-align: center; vertical-align: middle;">育林率</th>
				   <th style="text-align: center; vertical-align: middle;">抚育率</th>
				   <th style="text-align: center; vertical-align: middle;">档案率</th>
				   <th style="text-align: center; vertical-align: middle;">管护率</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="kTQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tRQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rTQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="jobDesignRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="aTCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="bIRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="sCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="sCKRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="aRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="raisingRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="fileRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tMRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     </table>
		     <br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" >添加</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
    	</form>
    	
    </div>
    
    <!-- 修改的div -->
    <div id="updateDate" class="easyui-window" style="width: 830px;height: 530px" data-options="modal:true,minimizable:false,maximizable:false,title:'数据修改',resizable:false,draggable:false,shadow:false,iconCls:'icon-edit',closed:true">	
    	<form id="editFrom" action="">
    		<label class="label_name">选择单位和工程:</label>&nbsp;&nbsp;&nbsp;
    	   
    	   		<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		 		valueField:'id',textField:'name',height:30,width:190">
    	   
           		&nbsp;&nbsp;&nbsp;
           		<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		  			valueField:'id',textField:'name',height:30,width:190">
           
        			&nbsp;&nbsp;&nbsp;
           		<input id="com" class="easyui-combobox" value="请选择工程" data-options="url:'${pageContext.request.contextPath }/json/工程.json',valueField:'id',textField:'ename',height:30,width:190">
           
		        <br>
				<br>
		     <table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;background-color: #;">自检上报面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">核实面积</th>
				   <th style="text-align: center; vertical-align: middle;">核实合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">作业设置面积</th>
				   <th style="text-align: center; vertical-align: middle;">按作业设置施工面积</th>
				   <th style="text-align: center; vertical-align: middle;">有档案面积</th>
				   <th style="text-align: center; vertical-align: middle;">开展县级自检面积</th>
				   <th style="text-align: center; vertical-align: middle;">抚育面积</th>
				   <th style="text-align: center; vertical-align: middle;">设置育林面积</th>
				   <th style="text-align: center; vertical-align: middle;">核实育林面积</th>
				   <th style="text-align: center; vertical-align: middle;">管护面积</th>
				   <th style="text-align: center; vertical-align: middle;">面积核实率</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="sIRArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="vTArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="verifiedArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="jobArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="dTCAATTW" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="fileArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cOCLSIA" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="wBArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="dFArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="vFArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tMArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="areaYield" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     	
		     	<tr>
		     		<th></th>
		     	</tr>
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;">核实面积合格率</th>
		     	   <th style="text-align: center; vertical-align: middle;">上报面积合格率</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报面积保存率</th>
				   <th style="text-align: center; vertical-align: middle;">当年上报成效合格率</th>
				   <th style="text-align: center; vertical-align: middle;">推算核实面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算完成合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算成效合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">推算保存合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">计划任务</th>
				   <th style="text-align: center; vertical-align: middle;">任务完成率</th>
				   <th style="text-align: center; vertical-align: middle;">全县自检合格面积</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="vAQRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rAQRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rQAITYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rARRITYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tSTOTYear" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="eVArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTCArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTEArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cTSArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="scheduledTask" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="mCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="cSCArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     	
		     	<tr>
		     		<th></th>
		     	</tr>
		     	
		     	<tr align="center">
		     	   <th style="text-align: center; vertical-align: middle;">自检保存合格面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">封育当年上报合格面积</th>
		     	   <th style="text-align: center; vertical-align: middle;">造林当年上报合格面积</th>
				   <th style="text-align: center; vertical-align: middle;">作业设计率</th>
				   <th style="text-align: center; vertical-align: middle;">按设计施工率</th>
				   <th style="text-align: center; vertical-align: middle;">建档率</th>
				   <th style="text-align: center; vertical-align: middle;">自检率</th>
				   <th style="text-align: center; vertical-align: middle;">自查率</th>
				   <th style="text-align: center; vertical-align: middle;">育林率</th>
				   <th style="text-align: center; vertical-align: middle;">抚育率</th>
				   <th style="text-align: center; vertical-align: middle;">档案率</th>
				   <th style="text-align: center; vertical-align: middle;">管护率</th>
		     	</tr>
		     	<tr>
		     		<td ><input name="kTQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tRQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="rTQArea" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="jobDesignRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="aTCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="bIRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="sCRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="sCKRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="aRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="raisingRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="fileRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     		<td ><input name="tMRate" type="text" style=" margin-left:-0.2px; width: 100%"></td>
		     	</tr>
		     </table>
		     <br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" >修改</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
    	</form>
    	
    </div>

<script type="text/javascript">
//取消按钮
$("#qx").on('click',function(){
	$('#addDate').window('close');
	
});
$("#uqx").on('click',function(){
	$('#updateDate').window('close');
});

function hide1(){
document.getElementById('ycxz').style.display='';
document.getElementById('gys_name').style.display='none';
  
}
function display1(){
document.getElementById('ycxz').style.display='none';
document.getElementById('gys_name').style.display='';
} 

/*删除*/
$(".btn-warning").each(function(){
	 $(this).click(function(){
		var e=$(this);
		var id=10;
		var el= e.parent().parent().children("input").get(0);
		var id=$(el).val();
		 layer.confirm('是否删除这个个工程？', {
			  btn: ['是','否'] //按钮
			}, function(){
				var params = {"TId":id};
				$.post("/manager/indicator/delete",params,function(data){
					if(data.status==200){
						$.messager.alert('提示','删除商品成功!',undefined,function(){
							layer.msg('删除成功！', {time: 1000,icon: 1});
							$("#contentList").datagrid("reload");
						});
					}
			});
			});
	});
});
/*修改*/
$(".btn-primary").each(function(){
	 $(this).click(function(){
		var e=$(this);
		var el= e.parent().parent().children("input").get(0);
		var id=$(el).val();
		var params = {"TId":id};
		$.post("/manager/indicator/modify",params,function(data){
			$('#Add_user_btn').on('click', function(){
				   layer.open({
			        type: 1,
			        title: '数据写入',
					maxmin: true, 
					shadeClose: true, //点击遮罩关闭层
			        area : ['860px' , '400px'],
			        content:$('#Add_user_style'),		
					btn: ['保存','取消'],
				    yes: function(index, layero){ 	 
					  if ($("#city").val()=="---请选择市---"){
							
			          }  
			  
				     else{			  
						  layer.alert('修改成功！',{
			               title: '提示框',				
						icon:1,		
						  }); 
						  layer.close(index);      
					  }	
			     } 	, 
				 cancel: function(index){
					 
					   layer.alert('确定退出！',{
			               title: '提示框',				
						  icon:1,		
						  });
					
				} 
			    });
			});
	
	});
});
});
/***判断用户添加文本**/


$('#Add_user_btn').on('click', function(){
	   layer.open({
        type: 1,
        title: '数据写入',
        skin:'layer-ext-fo',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['860px' , '510px'],
        content:$('#Add_user_style'),		
		btn: ['添加','取消'],
	    yes: function(index, layero){ 	 
		  if ($("#name").val()==""){
			  layer.alert('登录用户名不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }  
          if ($("#password").val()==""){
			  layer.alert('密码不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          } 
		  
		   if ($("#select_Roles").val()==""){
			  layer.alert('用角色不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }   
		    
		   if ($("#phone").val()==""){
			  layer.alert('电话号码不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }  
		   if ($("#user_name").val()==""){
			  layer.alert('用户名不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          } 
		  
		  if ($("#select_status").val()==""){
			  layer.alert('用户状态能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }   
		 if(!$(':radio[name=check]:checked').length) {
              layer.alert('请选择用户性质!',{
                title: '提示框',				
				icon:0,				
          }); 
		   return false;
		   
             } 			  
	     else{			  
			  layer.alert('添加成功！',{
               title: '提示框',				
			icon:1,		
			  }); 
			  layer.close(index);      
		  }	
     } 	, 
	 cancel: function(index){
		 
		   layer.alert('确定退出！',{
               title: '提示框',				
			  icon:1,		
			  });
		
	} 
    });
});
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
