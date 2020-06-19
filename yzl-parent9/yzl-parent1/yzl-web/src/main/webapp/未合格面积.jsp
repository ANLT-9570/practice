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

<title>xxx</title>
</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">

  <div class="Manager_style add_user_info">
    <div class="title_name">crud操作</div>
    <form id="searchForm" method="post" action="/cc/search">
	
	<label class="label_name">市名:</label>
		&nbsp;&nbsp;&nbsp;
		<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		
	<label class="label_name">县名:</label>
		&nbsp;&nbsp;&nbsp;
		<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
		  		valueField:'id',textField:'cname',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
		<button class="btn btn-primary" id="search">查询</button>
	</form>
     <div id="Add_user_style" style="display:none">
    <div class="add_user_style clearfix">
    <div>
        </div>
        <br>
    
    </div>
    </div>
    </div>
     <br><br>
     
     <div style="height: 500px;width: 1300px">
     	<table id="tab"></table>
     </div>
     
     <script type="text/javascript">
	     $("#tab").datagrid({ 
	    	 	singleSelect:true,//只能选择一个
				nowrap:false,//字符太多是否换行
				fit:true,//自适应
				url:'${pageContext.request.contextPath}/json/whgmj.json',
				frozenColumns:[[	//冻结在左边
								{title:'序列号',			rowspan:2,align:'center',field:'xl',checkbox:true,width:60,},
								{title:'单位',			rowspan:2,align:'center',field:'dname',width:80},
								{title:'不合格面积合计',	rowspan:2,align:'center',field:'nArea',width:120},
								
							]],
				
				//表头
				columns:[
							[
								{align:'center',width:2400,colspan:24,title:'各种原因导致的未合格面积', 		field:''},//colspan表示合并7个单元格 
								{align:'center',width:500,colspan:5,title:'各种原因导致的损失面积',			field:''},//rowspan表示合并2行
							 ],
						[
							{ align:'center',width:100, title:'合计', 							field:'wCount'},
							{ align:'center',width:100, title:'因人畜破坏',  					field:'ravage'},
							{ align:'center',width:130, title:'因抚育不力', 			field:'lackON'},
							{ align:'center',width:155, title:'因栽植技术',	field:'plantingT'},
							{ align:'center',width:100, title:'因苗木质量',				field:'sQuality'},
							{ align:'center',width:100, title:'因未适地适树',							field:'tANWAdapted'},
							{ align:'center',width:100, title:'因立地条件',							field:'sCondition'},
							{ align:'center',width:100, title:'因灾损毁核销',							field:'cDisaster'},
							{ align:'center',width:100, title:'因造林密度未达标',							field:'bStandard'},
							{ align:'center',width:100, title:'因采伐',							field:'bCutting'},
							{ align:'center',width:100, title:'因病虫害',							field:'bPests'},
							{ align:'center',width:100, title:'因火灾',							field:'bFire'},
							{ align:'center',width:100, title:'因旱灾',							field:'bDrought'},
							{ align:'center',width:100, title:'因冰雪霜冻灾害',							field:'bDisaster'},
							{ align:'center',width:100, title:'因水灾',							field:'bFlood'},
							{ align:'center',width:100, title:'因鼠兔害',							field:'bHarm'},
							{ align:'center',width:100, title:'因其它自然原因',							field:'bNatural'},
							{ align:'center',width:100, title:'因林冠下造林',							field:'iFL'},
							{ align:'center',width:100, title:'因零星植树',							field:'sTP'},
							{ align:'center',width:100, title:'因萌芽更新',							field:'germination'},
							{ align:'center',width:100, title:'因单行林带造林',							field:'bABelt'},
							{ align:'center',width:100, title:'因竹林垦抚',							field:'bFu'},
							{ align:'center',width:100, title:'因藤本草本植物种植',							field:'bAPlanting'},
							{ align:'center',width:100, title:'因以封代造',							field:'sealUp'},
							
							{ align:'center',width:100, title:'合计',							field:'sCount'},
							{ align:'center',width:100, title:'因开垦种地',							field:'cLand'},
							{ align:'center',width:100, title:'因转为牧地',							field:'pastrue'},
							{ align:'center',width:100, title:'因建设占用征收',							field:'eDT'},
							{ align:'center',width:100, title:'因自然灾害',							field:'naturalD'}

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
     
     <div id="addDate" class="easyui-window" style="width: 830px;height: 450px" data-options="modal:true,minimizable:false,maximizable:false,title:'数据写入',resizable:false,draggable:false,shadow:false,iconCls:'icon-add',closed:true">
     	<form id="addForm">
     	
     		<label class="label_name">选择单位:</label>&nbsp;&nbsp;&nbsp;
    	   
	        	<label class="label_name">市名:</label>
					&nbsp;&nbsp;&nbsp;
					<input class="easyui-combobox" name="dname" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
					  		height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  		
				<label class="label_name">县名:</label>
					&nbsp;&nbsp;&nbsp;
					<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
					  		valueField:'id',textField:'cname',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		<br><br><br>
     		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">因人畜破坏</th>
		     		<th style="text-align: center; vertical-align: middle;">因抚育不力</th>
		     		<th style="text-align: center; vertical-align: middle;">因栽植技术</th>
		     		<th style="text-align: center; vertical-align: middle;">因苗木质量</th>
		     		<th style="text-align: center; vertical-align: middle;">因未适地适树</th>
		     		<th style="text-align: center; vertical-align: middle;">因立地条件</th>
		     		<th style="text-align: center; vertical-align: middle;">因灾损毁核销</th>
		     		<th style="text-align: center; vertical-align: middle;">因造林密度未达标</th>
		     		<th style="text-align: center; vertical-align: middle;">因采伐</th>
		     		<th style="text-align: center; vertical-align: middle;">因病虫害</th>
		     		<th style="text-align: center; vertical-align: middle;">因火灾</th>
		     		<th style="text-align: center; vertical-align: middle;">因旱灾</th>
		     		<th style="text-align: center; vertical-align: middle;">因冰雪霜冻灾害</th>
		     		<th style="text-align: center; vertical-align: middle;">因水灾</th>
		     		
		     	</tr>
		     	<tr align="center" class="ttr">
		     		<td><input id="add_JobArea" name="ravage" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_DTCAATTW" name="lackON" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_FileArea" name="plantingT" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_COCLSIA" name="sQuality" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_WBArea" name="tANWAdapted" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_DFArea" name="sCondition" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_VFArea" name="cDisaster" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bStandard" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bCutting" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bPests" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFire" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bDrought" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bDisaster" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFood" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		
		     	</tr>
		     	<tr>
		     		<th></th>
		     	</tr>
		     	<tr>
		     		<th style="text-align: center; vertical-align: middle;">因鼠兔害</th>
		     		<th style="text-align: center; vertical-align: middle;">因其它自然原因</th>
		     		<th style="text-align: center; vertical-align: middle;">因林冠下造林</th>
		     		<th style="text-align: center; vertical-align: middle;">因零星植树</th>
		     		<th style="text-align: center; vertical-align: middle;">因萌芽更新</th>
		     		<th style="text-align: center; vertical-align: middle;">因单行林带造林</th>
		     		<th style="text-align: center; vertical-align: middle;">因竹林垦抚</th>
		     		<th style="text-align: center; vertical-align: middle;">因藤本草本植物种植</th>
		     		<th style="text-align: center; vertical-align: middle;">因以封代造</th>
		     		<th style="text-align: center; vertical-align: middle;">因开垦种地</th>
					<th style="text-align: center; vertical-align: middle;">因转为牧地</th>
					<th style="text-align: center; vertical-align: middle;">因建设占用征收</th>
					<th style="text-align: center; vertical-align: middle;">因自然灾害</th>
		     	</tr>
		     	
		     	<tr>
		     		<td><input id="add_TMArea" name="bHarm" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bNatural" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="iFL" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="sTP" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="germination" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bABelt" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFu" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bAPlanting" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="sealUp" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input  id="add_VTArea" name="cLand" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_VerifiedArea" name="pastrue" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_JobArea" name="eDT" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_DTCAATTW" name="naturalD" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     </table>
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" >添加</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
     	</form>
     	
     </div>
     
     <!-- 修改 -->
     <div id="updateDate" class="easyui-window" style="width: 830px;height: 450px" data-options="modal:true,minimizable:false,maximizable:false,title:'数据修改',resizable:false,draggable:false,shadow:false,iconCls:'icon-add',closed:true">
     	<form id="editFrom">
     	
     		<label class="label_name">选择单位:</label>&nbsp;&nbsp;&nbsp;
    	   
	        	<label class="label_name">市名:</label>
					&nbsp;&nbsp;&nbsp;
					<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
					  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  		
				<label class="label_name">县名:</label>
					&nbsp;&nbsp;&nbsp;
					<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
					  		valueField:'id',textField:'cname',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		<br><br><br>
     		<table id="Finalist_list" class="table table-striped table-bordered table-hover">
		     	<tr align="center">
		     		<th style="text-align: center; vertical-align: middle;">因人畜破坏</th>
		     		<th style="text-align: center; vertical-align: middle;">因抚育不力</th>
		     		<th style="text-align: center; vertical-align: middle;">因栽植技术</th>
		     		<th style="text-align: center; vertical-align: middle;">因苗木质量</th>
		     		<th style="text-align: center; vertical-align: middle;">因未适地适树</th>
		     		<th style="text-align: center; vertical-align: middle;">因立地条件</th>
		     		<th style="text-align: center; vertical-align: middle;">因灾损毁核销</th>
		     		<th style="text-align: center; vertical-align: middle;">因造林密度未达标</th>
		     		<th style="text-align: center; vertical-align: middle;">因采伐</th>
		     		<th style="text-align: center; vertical-align: middle;">因病虫害</th>
		     		<th style="text-align: center; vertical-align: middle;">因火灾</th>
		     		<th style="text-align: center; vertical-align: middle;">因旱灾</th>
		     		<th style="text-align: center; vertical-align: middle;">因冰雪霜冻灾害</th>
		     		<th style="text-align: center; vertical-align: middle;">因水灾</th>
		     		
		     	</tr>
		     	<tr align="center" class="ttr">
		     		<td><input id="add_JobArea" name="ravage" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_DTCAATTW" name="lackON" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_FileArea" name="plantingT" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_COCLSIA" name="sQuality" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_WBArea" name="tANWAdapted" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_DFArea" name="sCondition" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_VFArea" name="cDisaster" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bStandard" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bCutting" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bPests" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFire" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bDrought" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bDisaster" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFood" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		
		     	</tr>
		     	<tr>
		     		<th></th>
		     	</tr>
		     	<tr>
		     		<th style="text-align: center; vertical-align: middle;">因鼠兔害</th>
		     		<th style="text-align: center; vertical-align: middle;">因其它自然原因</th>
		     		<th style="text-align: center; vertical-align: middle;">因林冠下造林</th>
		     		<th style="text-align: center; vertical-align: middle;">因零星植树</th>
		     		<th style="text-align: center; vertical-align: middle;">因萌芽更新</th>
		     		<th style="text-align: center; vertical-align: middle;">因单行林带造林</th>
		     		<th style="text-align: center; vertical-align: middle;">因竹林垦抚</th>
		     		<th style="text-align: center; vertical-align: middle;">因藤本草本植物种植</th>
		     		<th style="text-align: center; vertical-align: middle;">因以封代造</th>
		     		<th style="text-align: center; vertical-align: middle;">因开垦种地</th>
					<th style="text-align: center; vertical-align: middle;">因转为牧地</th>
					<th style="text-align: center; vertical-align: middle;">因建设占用征收</th>
					<th style="text-align: center; vertical-align: middle;">因自然灾害</th>
		     	</tr>
		     	
		     	<tr>
		     		<td><input id="add_TMArea" name="bHarm" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bNatural" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="iFL" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="sTP" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="germination" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bABelt" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bFu" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="bAPlanting" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input id="add_TMArea" name="sealUp" type="text" style="width: 100%;margin-left: -0.2px"></td>
		     		<td><input  id="add_VTArea" name="cLand" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_VerifiedArea" name="pastrue" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_JobArea" name="eDT" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     		<td><input  id="add_DTCAATTW" name="naturalD" type="text" style="width: 100%; margin-left: -0.2px;"></td>
		     	</tr>
		     	
		     </table>
		     <br><br><br>
		     <button style="margin-left: 300px" class="btn btn-primary" type="button" >修改</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
     	</form>
     </div>
     
  </div>
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

$(function(){
	//查询
	$("#search").click(function(){
		$("#searchForm").submit();
		$("#Role_list").datagrid("reload");
	});
	
	//删除
	 $("#search").click(function(){$("#searchForm").submit();});                 $("#delete").click(function(){
		 var check = $("input[name='check']");
		if($(check).is(":checked")){
			layer.confirm('是否删除这个个工程？', {
				  btn: ['是','否'] //按钮
				},function(){
					 var array = new Array(); 
					 for(var i=0;i<check.length;i++){
						 if(check[i].checked==true){
							 //将所有id添加到数值中
							 array.push($(check[i]).val());
						 }
						 //将每个id用“,”隔开
					 }
					 var ids = array.join(",");
					 location.href="/tset/delete?ids="+ids;
					 $("#Role_list").datagrid("reload");
			}); 
		}else{
			alert("请选择要删除的目标");
		}
	 });
	 /*修改*/
	$('#edit').on('click', function(){
		var checks = $("input[name='check']");
		//判断被选中的有几个
		var a=0;
		var id;
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked==true){
				a++;
				id=$(checks[i]).val();
			}
		}
		var params="id:"+id;
		if(a==1){
			//$.post("/spot/check/findByid",params,function(data){
		   layer.open({
	        type: 1,
	        title: '数据修改',
			maxmin: true, 
			shadeClose: true, //点击遮罩关闭层
	        area : ['860px' , '400px'],
	        content:$('#Add_user_style'),		
			btn: ['保存','取消'],
		    yes: function(index, layero){
		    	$("#modifys").submit();
		    	if ($("#city").val()=="-请选择市---"){}  
		     else{
				  layer.alert('修改成功！',{
	               title: '提示框',				
				icon:1,		
				  });
				  alert("添加成功")
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
		  //});
		}else{
			alert("只能选择一个进行修改")
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
