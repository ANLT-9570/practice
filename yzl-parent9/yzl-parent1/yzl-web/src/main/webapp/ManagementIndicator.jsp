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
  
<script type="text/javascript">
var reusername="";
var inusername="";
$(function(){
	//用户名校验
	$("#miname").blur(function(){
		inusername=$(this).val();
		if(inusername != null){
			$.post("${pageContext.request.contextPath }/verifyMIname",{"name":inusername},
					function(data){
						//获取到返回来的植
						//var reusername = "";
						if(data != null){
							reusername=data.miname;
						}
						
						var usernameinfo="";
						if(inusername==reusername){
							usernameinfo="";
							usernameinfo="用户名已经存在或不能为空";
							
							$("#nameinfo").css("color","red");
						}else{
							usernameinfo="";
							usernameinfo="用户名可以用";
							
							$("#nameinfo").css("color","green");
						}
						//将提示信息写到输入框后面
						$("#nameinfo").html(usernameinfo);
					},
			"json")
		}
	});
	//添加按钮
	$("#sub").click(function(){
		 var epc=$("#addForm").form('validate');
		 if(reusername!=inusername){
			 //$("#addForm").submit();
			 $("#addForm").form('submit',{
				 success:function(data){
				 if(data != null){
						$.messager.alert("提示框","添加成功 ! !",'info');
					}else if(data == null){
						$.messager.alert("提示框","添加失败 ! !",'info');
					}
					
					//把输入框和提示框清空
					$("#miname").val("");
					$("#nameinfo").html("");
					//隐藏窗口
					$("#win").window('close');
					//刷新页面
					$("#tab").datagrid('reload');
				 }
			 });
		 }
	});
});

</script>
<title>xxx</title>

</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
    
    <br><br>
     <div style="width: 1170px;height: 450px">
	    <table id="tab">
	    	
	    </table>
    </div>
    <!-- 添加窗口 -->
    <div id="win" class="easyui-window" style="height: 250px;width: 310px" data-options="title:'数据写入',closable:false,draggable:false,iconCls:'icon-add',resizable:false,minimizable:false,maximizable:false,closed:true,resizable:false,modal:true,shadow:true">
    	<form id="addForm" action="addManagermentIndex">
    		<label class="label_name">任务名称:</label>
    		<input type="text" name="miname" id="miname" class="easyui-validatebox" required="true"><span id="nameinfo"></span>
    	</form>
    		<br><br><br><br><br><br>
    		<button style="margin-left: 40px" class="btn btn-primary" type="button" id="sub">添加</button>
			<button style="margin-left: 30px" class="btn btn-primary" type="button" id="qx">取消</button>
    </div>
    
    <div id="tb">
    	<shiro:hasPermission name="sys:glzb:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:glzb:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deleter()">删除</a>
    	</shiro:hasPermission>
    	
    	<input id="sear" value="" class="easyui-searchbox" style="width:110px;height:19px;">
    </div>
    
     <script type="text/javascript">
   
     //取消按钮
     $("#qx").click(function(){
    	//把输入框和提示框清空
			$("#miname").val("");
			$("#nameinfo").html("");
    	 $("#win").window('close');
     });
     
     $("#tab").datagrid({
 		url:'${pageContext.request.contextPath}/show_ManagermentIdex',
			nowrap:false,//字符太多是否换行
			fit:true,//自适应
			
			//表头
			columns:[
						[
							{align:'center',width:100, title:'序列号',checkbox:true, 	field:'id'},
							{align:'center',width:300, title:'任务名',			field:'miname'}
						 ]
					], 
			//显示序号rownumbers
			rownumbers:true,
			pagination:true,//显示分页
			toolbar:'#tb'/* [
    			{iconCls:'icon-add',text:'添加',handler:add},
    			{iconCls:'icon-cancel',text:'删除',handler:deleter},
    			{text:'<input id="sear" value="" class="easyui-searchbox" style="width:152px;height:19px;margin-top: -10px">'},
    		] */,
    		//onDblClickRow:dbUpdate
    	});
     $("#sear").searchbox({
    	 searcher:searched
     });
     function searched(value,name){
    	 //重新加载
    	 $("#tab").datagrid('load',{"value":value});
     }
     //添加
     function add(){
    	 $("#win").window('open');
     }
     //删除
     function deleter(){
    	 //获取选择的行
    	var items = $("#tab").datagrid('getSelections');
    	 if(items==""){
    		 $.messager.alert("提示框","必须选择一个!!",'info');
    		 return;
    	 }
    	 var ids=[];
    	 for(var i=0;i<items.length;i++){
    		 ids.push(items[i].id);
    	 }
    	 $.ajax({
    		 type:'post',
    		 traditional: true,
    		 async:false,
    		 url:'${pageContext.request.contextPath}/deleterManagermentIdex',
    		 data:{"ids":ids},
    		 success:function(data){
    			 if(data.data==300){
    				 $.messager.alert("提示框","任务和工程已关联,不可删除哦 ! !",'info');
    			 }
    			 if(data.data==200){
    				 $.messager.alert("提示框","删除成功",'question');
    				 $("#tab").datagrid('reload');
    				 $("#tab").datagrid('unselectAll');
    			 }else if(data.ok==400){
    				 $.messager.alert("提示框","删除失败",'error');
    			 }
    		 },
    		 dataType:"json",
    	})
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
