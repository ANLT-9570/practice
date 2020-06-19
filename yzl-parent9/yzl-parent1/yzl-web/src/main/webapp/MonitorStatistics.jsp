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
  
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
  
<script type="text/javascript">
var datas=null;
$(function(){
	$("#epcid").combobox({
		onSelect:function(){
			var e = $("#epcid").combobox('getValue');
			$.ajax({
				url:'${pageContext.request.contextPath }/show_table',
				async:false,
				type:'post',
				dataType:'json',
				data:{'eid':e},
				success:function(data){
					datas=data;
					
					var columns = new Array();
					columns.push({align:'center', colspan:+data.sclist.length, title:'抽查情况',		field:''});
					columns.push({align:'center', colspan:+data.cqlist.length, title:'核查质量',		field:''});
					columns.push({align:'center', colspan:+data.ctlist.length, title:'任务情况',		field:''});
					columns.push({align:'center', colspan:+data.milist.length, title:'管理指标',		field:''});
					
					var cls = new Array();
					
					for(var i = 0; i<data.sclist.length;i++){
						cls.push({align:'center',editor:'text', title:data.sclist[i].scname,		field:'id'+data.sclist[i].id});
					}
					for(var i = 0; i<data.cqlist.length;i++){
						cls.push({align:'center',editor:'text', title:data.cqlist[i].cqname,		field:'id'+data.cqlist[i].id});
					}
					for(var i = 0; i<data.ctlist.length;i++){
						cls.push({align:'center',editor:'text', title:data.ctlist[i].ctname,		field:'id'+data.ctlist[i].id});
					}
					for(var i = 0; i<data.milist.length;i++){
						cls.push({align:'center',editor:'text', title:data.milist[i].miname,		field:'id'+data.milist[i].id});
					}
					
					var column = [columns,cls];
					var cl = column[1];
					if(cl.length == 0){
						$.messager.alert('提示框','<h5>该:'+data.ename+'没有任务</h5>','info');
					}
					
					$("#tab").datagrid({
						fit:true,
			    		url:'',
			    		rownumbers:true,
			    		pagination:true,
			    		frozenColumns:[[
			    			{align:'center', rowspan:2,editor:'text', title:'市区',		field:'ename'},
			    			{align:'center', rowspan:2, title:'县区',		field:'ecode',
			    				/* editor:{ type='combobox',options:{
			    				
			    			}
			    				
			    			} */},
			    			{align:'center', rowspan:2, title:'县区',		field:'ecode',
			    				/* editor:{ type='combobox',options:{
			    				
			    			}
			    				
			    			} */},
			    			{align:'center', rowspan:2,editor:'text', title:'县区',		field:'ecode'},
			    		]],
			    		columns:column,
			    		toolbar:[
			    			{iconCls:'icon-add',text:'添加',handler:add}
			    		],
					});
					var datagrid;
			    	var editIndex;
			    	function add(){
			    		var scc = datas.sclist.length;
				    	var ctt = datas.ctlist.length;
				    	var mii = datas.milist.length;
				    	var cqq = datas.cqlist.length;
				    	
				    	if(editIndex != undefined){
				    		$("#tab").datagrid('endEdit',editIndex);
				    	}
				    	
				    	if(editIndex == undefined){
				    		//添加一行
				    		$("#tab").datagrid('insertRow',{
				    			
				    			index:0,
				    			row:{
				    				
				    			}
				    		});
				    		//将新插入的行进入编辑状态
				    		$("#tab").datagrid("beginEdit",0);
				    		editIndex = 0;
				    	}
			    	}
				},
				error:function(){
					$.messager.alert('错误提示','<h3>出错了 ! ! !</h3>','error');
				}
			});
		}
	});
})
	
</script>
<title>xxx</title>
</head>

<body>

<div class="page-content">
 <!---->
 <div class="user_Manager_style" style="">
 <form id="form1" name="form1" method="post" action="">
  <div class="Manager_style add_user_info">
    <div class="title_name">CC操作</div>
    
		<label class="label_name">工程名:</label>
								&nbsp;&nbsp;&nbsp;
		<input id="epcid" class="easyui-combobox" data-options="url:'${pageContext.request.contextPath }/show_epcs',
		  		valueField:'ecode',textField:'ename',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		
		<label class="label_name">时间:</label>
		<input class="easyui-combobox" value="请选择时间" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
		  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		
		<button class="btn btn-primary" id="search" type="submit">查询</button>
		</div>
		</form>
        <br>
     <div class="" style=" width: 1179px;height: 460px;">
	    <table id="tab">
	    	
	    </table>
    </div>
    <script type="text/javascript">
    	
    	datagrid = $("#tab").datagrid({
    		fit:true,
    		url:'',
    		rownumbers:true,
    		pagination:true,
    		columns:[[
    			{align:'center',width:100,title:'序号',	field:'xh',checkbox:true},
				{align:'center',width:200,title:'市名',editor:'text',		field:'dname'},
				{align:'center',width:200,title:'县名',editor:'text',		field:'cname'},
				{align:'center',width:200,title:'封山育林',editor:'text',		field:'fsyl'},
				{align:'center',width:200,title:'人工造林',	editor:'text',	field:'hsdzl'},
				{align:'center',width:200,title:'时间',	editor:'text',	field:'time'}
    		]],
    		toolbar:[
    			{iconCls:'icon-add',text:'添加',handler:add}
    		],
    		onDblClickRow:dbUpdate,
    	});
    	var datagrid;
    	var editIndex;
    	function add(){
	    	
	    	if(editIndex != undefined){
	    		$("#tab").datagrid('endEdit',editIndex);
	    	}
	    	
	    	if(editIndex == undefined){
	    		//添加一行
	    		$("#tab").datagrid('insertRow',{
	    			
	    			index:0,
	    			row:{
	    				
	    			}
	    		});
	    		//将新插入的行进入编辑状态
	    		$("#tab").datagrid('beginEdit',0);
	    		editIndex = 0;
	    	}
    	}
    	function dbUpdate(rowIndex, rowData){
    		$("#updateDate").window('open');
    		$("#updateForm").form('locad',rowData);
    	}
    </script>
     
     <div id="addDate" class="easyui-window" style="height: 300px;width: 850px" data-options="title:'数据写入',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="addForm">
     		
     		<label class="label_name">市名:</label>
								&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		
			<label class="label_name">县名:</label>
			<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		
			<label class="label_name">时间:</label>
			<input class="easyui-combobox" value="请选择时间" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		
     		<br><br><br>
    		<table class="table table-striped table-bordered table-hover">
    			<tr>
    				<th style="width:40px;text-align: center; vertical-align: middle;">封山育林</th>
    				<th style="width:40px;text-align: center; vertical-align: middle;">人工造林</th>
    			</tr>
    			<tr>
    				<td><input width="100%" type="text" name="fsyl" ></td>
    				<td><input width="100%" type="text" name="hsdzl"></td>
    			</tr>
    		</table>
    		<br><br>
    		<button style="margin-left: 300px" class="btn btn-primary" type="button" >添加</button>
			<button style="margin-left: 50px" class="btn btn-primary" type="button" id="qx">取消</button>
    	</form>
    </div>
    
    <div id="updateDate" class="easyui-window" style="height: 300px;width: 850px" data-options="title:'数据修改',draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
     	<form id="updateForm">
     		
     		<label class="label_name">市名:</label>
								&nbsp;&nbsp;&nbsp;
			<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		
			<label class="label_name">县名:</label>
			<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  		
			<label class="label_name">时间:</label>
			<input class="easyui-combobox" value="请选择时间" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
			  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     		
     		<br><br><br>
    		<table class="table table-striped table-bordered table-hover">
    			<tr>
    				<th style="width:40px;text-align: center; vertical-align: middle;">封山育林</th>
    				<th style="width:40px;text-align: center; vertical-align: middle;">人工造林</th>
    			</tr>
    			<tr>
    				<td><input width="100%" type="text" name="fsyl" ></td>
    				<td><input width="100%" type="text" name="hsdzl"></td>
    			</tr>
    		</table>
    		<br><br>
    		<button style="margin-left: 300px" class="btn btn-primary" type="button" >添加</button>
			<button style="margin-left: 50px" class="btn btn-primary" type="button" id="uqx">取消</button>
    	</form>
    </div>
     
 </div>
 </div>
<script type="text/javascript">
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
