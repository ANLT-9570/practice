<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>

<title>Insert title here</title>
</head>
<body>
<div class="Manager_style add_user_info" style="margin-left: 20px">
	
</div><br>
<div style="margin-left:20px; width: 1278px;height: 500px">
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
					$('#tWind').window('open');
				},text:'添加'}
			]
    	});
    </script>
    <div id="tWind" class="easyui-window" style="top:20px;left:200px;width: 900px;height: 400px" data-options="shadow:false,minimizable:false,maximizable:false,title:'数据写入',iconCls:'icon-add',closed:true">
    	
    	<form id="form1" name="form1" method="post" action="">
	    	<br>
		    <label class="label_name">选择单位和工程:</label>&nbsp;&nbsp;&nbsp;
		    	   
		    	   <input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
				  valueField:'id',textField:'name',height:30,width:190">
				  
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
     </form>
    </div>
</body>
</html>