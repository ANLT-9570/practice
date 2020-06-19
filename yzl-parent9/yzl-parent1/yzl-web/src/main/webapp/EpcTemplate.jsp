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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
	<style type="text/css">
.button1 {
    background-color: #4CAF50; /* Green */
    color: white;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}

	.button2 {
    background-color: red; /* Green */
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:50px;
    height:50px;
}
.button3 {
    background-color: #4CAF50; /* Green */
    color: white;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    width:80px;
    height:31px;
}
</style>
<script type="text/javascript">

//添加模板
$(function(){
	$("#addMel").click(function(){
		//工程的编号
		var eid=$("#epci").combobox('getValue');
		//任务的编号
		var di=$("#taski").combobox('getValues');
		var tids=[];
		for(var i=0;i<di.length;i++){
			tids.push(di[i]);
		}
		if(eid == "" || di.length == 0){
			$.messager.alert("提示框","不能少选或者不选哦 ! !",'info');
			return;
		}
		$.ajax({
			url:'${pageContext.request.contextPath}/addTemplate',
			traditional: true,
			data:{"eid":eid,"tids":tids},
			success:function(data){
					if(data.data==200){
						$.messager.alert("提示框","添加成功",'question');
						
					}else if(data.data==400){
						$.messager.alert("提示框","添加失败",'question');
					}else if(data.data==300){
						$.messager.alert("提示框","列表已存在该工程 ! !",'question');
					}
					//刷新页面
					$("#tab").datagrid('reload');
					$("#epci").combobox('setValue','');
					$("#taski").combobox('setValues','');
				},
			dataType:"json"
				});
	})
})
</script>
<title>xxx</title>

</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
 
 <shiro:hasPermission name="sys:gcmb:tj">
  <div class="Manager_style add_user_info">
  <%-- <form id="form1" name="form1" method="post" action="${pageContext.request.contextPath }/addTemplate"> --%>
    <div class="title_name">crud操作</div>
    
    <!-- <label class="label_name">地区名:</label>
		&nbsp;&nbsp;&nbsp;
		<input type="text" style="width:200px; margin-left:10px">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		
		<label class="label_name">工程名:</label>
		&nbsp;&nbsp;&nbsp;
		<input class="easyui-combobox" id="epci" type="text" style="width: 205px; height: 30px;" data-options="valueField:'ecode',textField:'ename',url:'${pageContext.request.contextPath }/show_epcs',panelHeight: 200,editable:false">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
	<label class="label_name">任务名:</label>
		&nbsp;&nbsp;&nbsp;
		<input class="easyui-combobox" id="taski" style="width: 400px; height: 30px;" data-options="valueField:'tcode',textField:'tname',url:'${pageContext.request.contextPath }/show_tasks',panelHeight: 200,multiple:true,editable:false">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<button class="button3" id="addMel" type="submit">添加模板</button>
	<!-- </form> -->
	
    </div>
    </shiro:hasPermission>
    
    <br><br>
     <div style="width: 1180px;height: 450px">
	    <table id="tab">
	    	
	    </table>
    </div>
    <!-- 数据修改 -->
    <div id="editWindow" class="easyui-window" title="数据修改" style=" width:800px;height:415px;" data-options="minimizable:false,collapsible:true,modal:true,closed:true,closable:false,iconCls:'icon-edit'">
		<%-- <form id="editForm" action="${pageContext.request.contextPath }/updateUser" method="post"> --%><br><br>
			<table id="tba" class="table-edit" width="95%" align="center" data-options="">
           		<tr class="title"><td colspan="4"></td></tr>
           		
	           	<tr>
	           		<td width="60px"><label class="label_name">工程名:</label></td>
	           		<td><input id="ecode" class="easyui-combobox" style="width: 205px; height: 25px;" data-options="valueField:'ecode',textField:'ename',url:'${pageContext.request.contextPath }/show_epcs',panelHeight: 200,editable:false"></td>
				</tr>
				
				<tr class="title"><td colspan="4"></td></tr>
				
	           	<tr>
	           		<td>任务:</td>
	           		<td id="tId" height="100px" align="left" colspan="3"></td>
	           	</tr>
	          </table>
	          <br><br>
		<!-- </form> -->
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button1" id="edits">修改</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2" id="cancel">取消</button>
	</div>
	
	<div id="tb">
    	<shiro:hasPermission name="sys:gcmb:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="viw()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:gcmb:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleter()">删除</a>
    	</shiro:hasPermission>
    	
    	<input id="sear" value="" class="easyui-searchbox" style="width:110px;height:19px;">
    </div>
	
    <script type="text/javascript">
    var oename="";
   //修改按钮
     $("#edits").click(function(){
    	 var edname = $("#ecode").combobox('getValue');
    	 var inpus = $("#tId").find('input');
    	 
    	 if(oename!=edname){
    		 var asd=$.messager.alert("提示框",'<br><h1>不能修改工程哦 ! !</h1>','info');
    		 edname = $("#ecode").combobox('setValue',oename);
    		 return;
    	 }
    	 var ids=[];
    	 for(var i=0;i<inpus.length;i++){
    		 
    		 if(inpus[i].checked==true){
    			 ids.push(inpus[i].id);
    		 }
    	 }
    	 
    	 $.ajax({
    			 url:'${pageContext.request.contextPath }/updateTemplate',
    			 traditional: true,
    			 type:'post',
    			 data:{"edname":edname,"ids":ids},
    			 success:function (data){
    				 if(data.data==200){
    					 $.messager.alert("提示框",'<br><h1>修改成功! !</h1>','question');
    				 }
    				 $("#editWindow").window('close');
    				 $("#tab").datagrid('reload');
    			 },
    			 error:function(){
    				 $.meaasger.alert("提示框",'<br><h1>出错了 ! !</h1>','info');
    			 },
    			 dataType:'json',});
     });
     //取消按钮
     $("#cancel").click(function(){
    	 $("#editWindow").window('close');
    	 //取消后把所有选种的状态都取消
    	 var cheks=$('input[type=checkbox]');
    	 for(var c=0;c<cheks.length;c++){
    		 cheks[c].checked=false;
    	 }
     });
     
     $("#tab").datagrid({
 		url:'${pageContext.request.contextPath}/show_template',
			nowrap:false,//字符太多是否换行
			fit:true,//自适应
			
			//表头
			columns:[
						[
							{align:'center',width:100, title:'序列号',checkbox:true, 	field:'ecode'},
							{align:'center',width:170, title:'工程名',			field:'ename'},
							{width:850, title:'任务名',			field:'tcode',formatter:function(value,row,index){
																							var names=[];
																							for(var i=0;i<row.list.length;i++){
																								names.push(row.list[i].tname);
																							}
																							return names.join("，");
																							 /* "<input type='checkbox'>+"row.list."+</input>"; */
							}}
						 ]
					], 
			//显示序号rownumbers
			rownumbers:true,
			pagination:true,//显示分页
			pageList:[10,15],
			toolbar:'#tb'/* [
    			{iconCls:'icon-edit',text:'修改',handler:viw},
    			{iconCls:'icon-cancel',text:'删除',handler:deleter},
    			{text:'<input id="sear" value="" class="easyui-searchbox" style="width:100px;height:19px;margin-top: -10px">'},
    		] */,
    	});
     $("#sear").searchbox({
    	 searcher:searched
     });
     //修改查看
     function viw(){
    	 var oe = $("#tab").datagrid('getSelected');
    	 var se = $("#tab").datagrid('getSelections');
    	 //alert(se);
    	 if(se == 0 || se.length > 1){
    		 $.messager.alert('提示框','<br><h1>只能选择一个或不能不选哦 ! !</h1>','info');
    		 return;
    	 }
    	 $("#editForm").form('load',oe);
    	 $("#editWindow").window('open');
    	 $("#ecode").combobox('setValue',oe.ename);
    	 
    	 for(var i=0;i<oe.list.length;i++){
    		 $("#"+oe.list[i].tcode).prop('checked',true);
    	 }
    	 oename=oe.ename;
     }
     //删除
     function deleter(){
    	 var dis=$("#tab").datagrid('getSelections');
    	 if(dis == null || dis.length==0){
    		 $.messager.alert('提示框','<br><h1>至少选择一个哦 ! !</h1>','info');
    		 return;
    	 }
    	 var etdis=[];
    	 for(var i=0;i<dis.length;i++){
    		 etdis.push(dis[i].ecode);
    	 }
    	 $.ajax({
    		 url:'${pageContext.request.contextPath }/deleterTemplate',
    		 traditional: true,
    		 data:{"etdis":etdis},
    		 success:function(data){
    			 //alert(data.data);
    			 if(data.data==200){
    				 $.messager.alert('提示框','<br><h1>删除成功 ! !</h1>','question');
    			 }else if(data.data==300){
  					$.messager.alert("提示框","进度表中已存在有数据不可删 ! !","info");
  				}else if(data.data==400){
 					$.messager.alert("提示框","删除出错 ! !","info");
 				}
    			 $("#tab").datagrid('reload');
    		 },
    		 error:function(){
    			 $.messager.alert('提示框','<br><h1>删除失败 ! !</h1>','question');
    		 },
    		 dataType:'json'
    	 });
     }
     
     //加载所有的任务
     $(function(){
    	 $.post(
    			 '${pageContext.request.contextPath }/show_tasks',
    			 function(data){
    				 //alert(data);
    				 for(var i=0;i<data.length;i++){
    					 var tid=data[i].tcode;
    					 var tname=data[i].tname;
    					 $("#tId").append('<input type="checkbox" id="'+tid+'" name="tIds" value="'+tid+'" >&nbsp;<label style="padding-bottom: 5px" for="'+tid+'">'+tname+'</label></input>，');
    				 }
    			 },
    			 'json'
    	 );
     })
     function searched(value,name){
    	 $("#tab").datagrid('load',{"value":value});
     }
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
