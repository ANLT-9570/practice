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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/linkage.js"></script>
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
<title>xxxx</title>
</head>

<body>
<div style="width:1099px;" class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
 <form id="form1" name="form1" method="post" action="">
  <div class="Manager_style add_user_info" style="width: 1273px;">
    <div class="title_name">cC操作</div>
    
	<label class="label_name">市名:</label>
					&nbsp;&nbsp;&nbsp;
	<input class="easyui-combobox" value="请选择市" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
	  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  		
	<label class="label_name">县名:</label>
		&nbsp;&nbsp;&nbsp;
	<input class="easyui-combobox" value="请选择县" data-options="url:'${pageContext.request.contextPath }/json/城市.json',
	  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	<label class="label_name">工程:</label>
	<input class="easyui-combobox" value="请选工程" data-options="url:'${pageContext.request.contextPath }/json/工程.json',
	  		valueField:'id',textField:'name',height:30,width:190">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
		<button class="btn btn-primary" id="search" type="submit">查询</button>
		
        <br>
        <br>
        <br>
  </div>
  <br><br>
  
  <div style="height: 500px;width: 1300px">
    <table id="tab"></table>
    </div>
    <script type="text/javascript">
    		$("#tab").datagrid({ 
    			singleSelect:true,//只能选择一个
    			border:2,//边框大小
    			nowrap:false,//字符太多是否换行
    			fit:true,//自适应
    			url:'${pageContext.request.contextPath}/json/data.json',
    			frozenColumns:[[	//冻结在左边
    							{title:'市名',align:'center',field:'dname',width:60},
    							{title:'县名',align:'center',field:'cname',width:80}
    						]],
    			
    			//表头
    			columns:[
    						[
    							{align:'center',width:700,colspan:7,title:'合计', 			field:''},//colspan表示合并7个单元格 
    							{align:'center',width:100,rowspan:2,title:'工程名',			field:'eName'},//rowspan表示合并2行
    							{align:'center',width:100,rowspan:2,title:'工程计划时间',	field:'planTime'},
    							{align:'center',width:100,rowspan:2,title:'工程实施时间',	field:'implTime'},
    							{align:'center',width:100,rowspan:2,title:'封山育林',		field:'closFors'},
    							{align:'center',width:100,rowspan:2,title:'人工造林',		field:'forPlant'},
    							{align:'center',width:100,rowspan:2,title:'荒山荒地造林计划任务',		field:'barrenFor'},
    							{align:'center',width:100,rowspan:2,title:'后续产业用材林经济林造林',	field:'followUp'},
    							{align:'center',width:100,rowspan:2,title:'荒山荒地人工造林',		field:'artiFor'},
    							{align:'center',width:100,rowspan:2,title:'迹地人工更新造林',		field:'reForestations'},
    							{align:'center',width:100,rowspan:2,title:'林下种植中药材',			field:'herbsForests'},
    							{align:'center',width:100,rowspan:2,title:'低效林改造',				field:'ineFor'},
    							{align:'center',width:100,rowspan:2,title:'补植补造',				field:'reComplement'},
    							{align:'center',width:100,rowspan:2,title:'新造',					field:'newCreate'},
    							{align:'center',width:100,rowspan:2,title:'低改',					field:'lowCreate'} 
    						 ],
    					[
    						{rowspan:1, align:'center',width:100, title:'计', 				field:'subtotal'},
    						{rowspan:1, align:'center',width:100, title:'荒山人工造林',  	field:'artAff'},
    						{rowspan:1, align:'center',width:100, title:'封山育林', 			field:'closFor'},
    						{rowspan:1, align:'center',width:100, title:'迹地人工更新造林',	field:'reForestation'},
    						{rowspan:1, align:'center',width:100, title:'低效林改造',		field:'ineFor'},
    						{rowspan:1, align:'center',width:100, title:'退耕还林',			field:'returnFor'},
    						{rowspan:1, align:'center',width:100, title:'林下种植中药材',	field:'herbsForest'}
    					]], 
    			//显示序号rownumbers
    			rownumbers:true,
    			pagination:true//显示分页
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
