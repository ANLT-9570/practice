<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
   <%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%--  <%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %>  --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
 
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/kindeditor-4.1.10/plugins/code/prettify.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script><%-- 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/file-download.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.fileDownload.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/kindeditor-4.1.10/plugins/code/prettify.js"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.fileupload.js"></script>
<title>xxx</title>
<style type="text/css">
/* .datagrid-body{
	width: 1045px !important;
} */
.datagrid-body{
style:"width:625px"
}
span.searchbox{
	margin-bottom:-6px;
}
.layout-split-west{/* panel layout-panel layout-panel-west  */
	width:155px !important; 
}
.layout-panel-center{
	left: 156px !important;
}
/* .layout-body{
width: 143px !important;
} */
/* .panel-body-noheader{
width: 143px !important;
} */
#iframe{
	white-space: nowrap;
}
.tree{
	overflow-y: hidden;

}
 
table.uploadFileTablle {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.uploadFileTablle th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.uploadFileTablle td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}

/* #labelName{
position: absolute;
margin-left:150px;
margin-top:35px; -3 
z-index: 1000
} */
.zl{/* //.combo */
	position: absolute;
	margin-left:210px;
	margin-top:33px;/* -6px */
	height:18px !important;
	z-index: 1000
}


#autore{
	position: absolute;
	margin-left:225px;
	margin-top:10px;
	z-index: 1000
}
.label_name{
	position: absolute;
	margin-left:5px;
	margin-top:-9px;
	z-index: 1000
}
/* #year{
position: absolute;
margin-left:10px;
margin-top:-10px;
z-index: 1000
} */
.button-style{
	border-radius:4px;
	color: gree;
}
.spinner{
width:68px !important;
position: absolute;
margin-left:60px;
margin-top:28px;
z-index: 100
}

/*a  upload */
.a-upload {
    padding: 4px 10px;
    height: 20px;
    line-height: 20px;
    position: relative;
    cursor: pointer;
    color: #888;
    background: #fafafa;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1
}

.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.a-upload:hover {
    color: #444;
    background: #eee;
    border-color: #ccc;
    text-decoration: none
}



/* //提示条的样式 */
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 28px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
        border-right-color: rgb(254, 182, 84);
        border-right-style: solid;
        border-right-width: 1px;
        border-left-color: rgb(254, 182, 84);
        border-left-style: solid;
        border-left-width: 1px;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}
/* body {
    margin: 0px;
    padding: 0px;
} */
.top_box {
    line-height: 26px;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips .icon_tip1 {
    background-position: 0 -4px;
    height: 30px;
    margin: 0px 10px 0 10px;
    width: 18px;

/*     background-position: 0 -26px;
    height: 30px;
    margin: 3px 10px 0 10px;
    width: 15px; */
}
.top_tips a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
}
a {
    text-decoration: none;
    cursor: pointer;
    color: #333;
    blr: expression(this.onFocus=this.blur());
    outline: none;
}
.left {
    float: left;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
        background-position-x: 0px;
        background-position-y: 0px;
}
.top_tips .icons {
    background: url("${pageContext.request.contextPath}/images/icon_toptips.png"); no-repeat scroll 0 0;
}
.right {
    float: right;
}
.top_tips .icon_close {
    background-position: 0 -26px;
    height: 28px;
    margin: 3px 10px 0 10px;
    width: 15px;
    text-indent: -999em;
}
body {
    font-family: Verdana,Arial,simsun,sans-serif,"Microsoft YaHei",Mingliu,Verdana,Helvetica,Lucida;
    font-size: 12px;
    color: #333;
}
.top_box {
    margin: 0 auto;
    width: 300px;
    line-height: 21px;
    overflow: hidden;
    font-size: 12px;
    white-space: nowrap;
}
.top_tips {
    width: 100%;
    min-width: 1100px;
    height: 20px;
    background-color: #fff5d3;
    border: 1px solid #feb654;
    border-left: 0 solid #feb654;
    border-right: 0 solid #feb654;
}

</style>

</head>

<body class="easyui-layout" >


		<!-- 左边的树 -->
		<div data-options="region:'west',title:'',split:true,border:false" style="width:140px;margin-left: 0px;">
		    <ul id="tree" class="easyui-tree">
		    
		    </ul>
	    </div> 

	    <!-- 右边的表格 -->
		<div id="DataTemplateDiv" data-options="region:'center',title:'',border:false" style="padding:2px 20px 25px 0px;background:#eee;">
				 		<c:if test="${sessionScope.updateDataNumber > 0}">
			<div class="top_tips" id="toptips">
				<div class="top_box">
					<div style="display: block;" class="top_news">
						<div class="icons icon_tip1 left"></div>
						<div  id="hint" class="left">
								<a target="_blank"  onClick='topRead();' href="javascript:void(0);">温馨提示：当前有<font style="color:red;">${sessionScope.updateDataNumber}条</font>下发任务已更新</a>
						</div>
						<a href="javascript:void(0);" title="关闭提示，不再提醒" id="read" class="icons icon_close right">关闭提示，不再提醒</a>
					</div>
				</div>
			</div>
		</c:if>
					<table  id='DataTemplateTable' style="displae:none;"></table>
		</div>
		
     <div id="toolbar" style="height:50px;dispaly:none">
    	<label style="padding-left:23px;margin-top: 26px;font-size: 12px" class="label_name">年份:</label>
       	<input id="year" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:90px;;height:19px;margin-top: 0px">
       	
    	<label style="font-size: 12px;margin-left: 5px;" class="" id="labelName">造林类别:</label> 
		<input id="zllb" class="easyui-combobox" name="dept" style="width: 100px" /> &nbsp;&nbsp;
    	
    	<label style="font-size: 12px;" class="" id="labelName">工程类别:</label>
    	<!-- <label style="" class="label_name">工程类别:</label> -->
		<input id="gclb" class="easyui-combobox" style="width: 100px" name="project" data-options="valueField:'mark',textField:'ename',url:'${pageContext.request.contextPath}/epc/queryAll'" />&nbsp;&nbsp;
       	
       	<!-- <input id=searchKey value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: 20px"> -->
       	<shiro:hasPermission name="sys:rwxf:tj">
       		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
       	</shiro:hasPermission>
       	
       	<shiro:hasPermission name="sys:rwxf:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="modify()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:bc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="save()">保存</a>    
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="remove()">删除</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:qxbj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="cancelEdit()">取消</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:dc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="derive()">导出</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwxf:wndr">
    	 <!-- id="dataDr" --><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="ddd(this)" >往年数据导入</a>
    	</shiro:hasPermission>
    	
		<shiro:hasPermission name="sys:rwxf:dr">
			<a  href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="toLead()">导入</a><!-- //onclick="toLead()"id="btn" -->
		</shiro:hasPermission>
		
   </div>
  <input type="file" name="excelName" id="file" onchange="dataDrs()" style="display:none"><!-- onchange="fileUpload()" -->
  <input type="file" name="excelNameing" id="fileing" onchange="dataDrsing()" onclick="dataclick()" style="display:none">
  <!-- <div id="p" class="easyui-progressbar" style="width:400px;"></div> -->
  
   <!-- 文件上传窗口 -->
   <!-- 文件上传窗口 -->
    <div id="uploadFileDiv" class="easyui-window" title="上传窗口" style="margin-top:0px;width:850px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>
			        <div id="uploadFileButton">
	<!-- 						<input multiple="multiple"  type="file" id="uploadFile"  name="uploadFile" class="easyui-linkbutton picFileUpload"></input>
						<span id="spaninfo"></span> -->
						<a id="uploadFileAlabel" href="javascript:void(0);" class="a-upload" style="margin-left: 15%;margin-top: 10px;"><!-- //style="margin-left: 100px; -->
						<input id="uploadFile" multiple="multiple" type="file" name="uploadFile" class="easyui-linkbutton picFileUpload">点击这里上传文件
						</a><span id="spaninfo"></span>
					</div>   
			<div class="pics"  style="margin-top:3px;margin-left:15%;text-align:center;">
				<br/>
  				<table class="uploadFileTablle" border="1">
					<tr>
						<th width="351px" cellspacing="0" cellpadding="0" style="text-align:center;border-color:gray;"><font  color="gray">文件名</font></th>
						<th width="300px"  cellspacing="0" cellpadding="0" style="text-align:center;border-color:gray"><font  color="gray">操作</font></th>				   
				    </tr>
			    </table>
			</div>
	    </table>
	    
	    <input type="hidden" name="itemParams"/>
	</form>
	<input style="margin-left:46%;margin-top:25%;mapadding:5px" type="button" value="关闭" onclick="closedForm()" class="button-style"> 

</div>

<div id="mask" class="datagrid-mask" style="display:none;width: 100%"></div>
<div id= "msg" class="datagrid-mask-msg" style="display:none;"></div>
</body>
<script type="text/javascript">
function ddd(){
	$("#file").trigger('click');
}
function dataDrs(){
	$("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("<img  class ='img1' /> 数据导入中，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	//创建formdata对象
	var formData = new FormData();
	//给formData对象添加<input>标签,注意与input标签的ID一致
	formData.append('excelName', $('#file')[0].files[0]);
	//$("#mask").css({display: 'block',height: $(window).height()});
	//$("#msg").html("加载中，请稍候。。。").css({display: 'block',left: ($(document.body).outerWidth(true) - 190) / 2,top: ($(window).height() - 45) / 2});

	    
	//console.info(22222);
	//$.ajaxSettings.async=false;
	$.ajax({
		async:true,
		url:'${pageContext.request.contextPath}/TaskIssued/toLead/dataDr',
		type : 'POST',
		data : formData,
		contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
		processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
		dataType: 'json',//这里是返回类型，一般是json,text等
		clearForm: true,//提交后是否清空表单数据
		success:function(data){
			$(".datagrid-mask").remove();
	        $(".datagrid-mask-msg").remove();
			//$.messager.progress('close');
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    		//$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
			
		},
		error:function(data){
			//alert("搓搓！");
		},
		beforeSend: function () {
	    },
	    complete: function () {
	    }
	});

}
//弹出加载层
function load() {
	console.info(1111111);
	 $("<div class='datagrid-mask'></div>").css({
	        display: 'block',
	        width: '100%',
	        height: $(window).height()
	    }).appendTo("body");
	    $("<div class='datagrid-mask-msg'></div>").html("加载中，请稍候。。。").appendTo("body").css({
	        display: "block",
	        left: ($(document.body).outerWidth(true) - 190) / 2,
	        top: ($(window).height() - 45) / 2
	    });
	    
	console.info(22222);
}

//取消加载层  
function disLoad() {
  $(".datagrid-mask").remove();
  $(".datagrid-mask-msg").remove();
}   
$("#zllb").combobox({
	onLoadSuccess:load
});
function load(){
 	var as = $("#zllb").next();
	as.attr("id","zl");
}

//当前登录的用户
var usr = "${sessionScope.user.username}";
var databtn = true;

function toLead(){
	$("#fileing").trigger('click');
}
function dataclick(){
	
}
function dataDrsing(){
	$("<div class=\"datagrid-mask\" id='123' value = '333'></div>").css({ display: "block", width: "100%", height: "auto !important" }).appendTo("body");
	$("<div class=\"datagrid-mask-msg\" id='345' value='777'></div>").html("<img  class ='img1' /> 正在运行，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });
	//创建formdata对象
	var formData = new FormData();
	//给formData对象添加<input>标签,注意与input标签的ID一致
	formData.append('excelNameing', $('#fileing')[0].files[0]);
	    
	//$.ajaxSettings.async=false;
	
	$.ajax({
		async:true,
		url:'${pageContext.request.contextPath}/TaskIssued/toLead',
		type : 'POST',
		data : formData,
		contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
		processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
		dataType: 'json',//这里是返回类型，一般是json,text等
		clearForm: true,//提交后是否清空表单数据
		success:function(data){
			$(".datagrid-mask").remove();
	        $(".datagrid-mask-msg").remove();
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    		//$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
			
		},
	});
}

$("#btn").upload({
	action:'${pageContext.request.contextPath}/TaskIssued/toLead',
	
	name:'excelName',
	onComplete: function(data) {
		
		if(databtn){
			databtn=false;
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		//$("#grid").datagrid("reload");
	    		initHeader();//取表头数据
    			initTables();//加载数据表格数据
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
		}
		
 },// 请求完成时 调用函数
}); 

function pre(){
	$.messager.progress({
		title:'Please waiting',
		msg:'Loading data...'
		});
}

var dataDr = true;
/* $("#dataDr").upload({
	action:'${pageContext.request.contextPath}/TaskIssued/toLead/dataDr',
	name:'excelName',
	onComplete: function(data) {
		
		console.info(data);
		if(dataDr){
			
			$.messager.progress('close');
			dataDr = false;
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		$("#grid").datagrid("reload");
	    	}else{
	    		$.messager.alert("错误提示",'导入失败',"error");
	    	}
		}
		
 },// 请求完成时 调用函数
}); */





//$("#dataDr").prev("div").attr("id","test");
$("#dataDr").parent().prop("id","dat");
$("#dat").css({"margin-left":"730px"});
	$("#dat").css({"margin-top":"-25px"});
	$("#dat").css({"line-height":"15px"});
	$("#dat").css({"height":"20px"});
	$("#dat").css({"width":"105px"});
/* $("#dataDr").css({"margin-left":"100px"}); */
//if(par!=null){
///	console.info(par+"11111111");
//}
$("#btn").parent().prop("id","bnt");
$("#bnt").css({"margin-left":"838px"});
$("#bnt").css({"margin-top":"-25px"});
$("#bnt").css({"line-height":"15px"});
$("#bnt").css({"height":"50px"});
$("#bnt").css({"width":"78px"});

var areaCode = "45";
var ZLLB;
    //导出
    function derive(){
    	var node =$("#tree").tree('getSelected');//点击的行政编号
    	var year = $("#year").val();//获取时间
    	var click = null;
    	if(node != null){
    		areaCode = node.id;
    		click = node.id;
    	}else{
    		click = 45;
    	}
    	window.location.href="${pageContext.request.contextPath}/derive?year="+year+'&click='+click+"&areaCode="+areaCode+"&ZLLB="+ZLLB;
    }
    

    
    var header = new Array();//存储表头数据
  	//存储添加固定表头的数据
	var frozenColumnsTab = new Array();
    var district="";
    //初始化表格表头
    function initHeader(){
    	//初始化存放表头数据的变量
    	frozenColumnsTab = new Array();//初始化固定表头的数据
    	$.ajaxSettings.async=false;
    	header=new Array();//初始化 存储表头数据
    	var columnsOneTab = new Array();	//一级表头数据
    	var columnsTowTab = new Array();	//二级表头数据
    	var columnsThreeTab = new Array();	//三级表头数据
       	//查询所有地区
       	$.ajaxSettings.async = false;
		$.post("${pageContext.request.contextPath}/district/queryDistinctCity",function(data){//获取地区数据
  	        	if(data.status==200){
  	        		//获取地区数据
  	        		district=data.data;
  	        	}else{
  	        		$.messager.show({title:'警告',msg:'地区数据加载失败！',timeout:8000,});
  	        	}
		}); 
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var targetNode=root.id;//默认为父节点id
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	if(node!=null&&node.id != null){
	   		targetNode = node.id;
	   	}
	   	if(targetNode=='45'){
	   		//添加需要固定表头的数据
	   		frozenColumnsTab.push([	   		   

				{align:'center',width:100,title:'序号',  rowspan:'4',field:'id',checkbox:true},//frozen:true,
				{align:'center',width:100,title:'市名', rowspan:'4',	field:'city'},
			]);
			//添加固定表头
			columnsOneTab.push({align:'center',width:100,title:'文件',  'rowspan':3,	field:'filesUrl'});
	   	}else{
	   		//添加固定表头的数据
	   		frozenColumnsTab.push([
/* 	   			   		   		   {align:'center',width:12,title:'',  'rowspan':4,	field:'newData',formatter : function(data, row, index) {
		   			   					if(data!=''&&data!=null){
		   			   						$("#DataTemplateTable").datagrid("checkRow",index);
		   			   					}
		   			   					return data;
	   		   					   }}, */
	   		   					{align:'center',width:100,title:'序号',  rowspan:'3',field:'id',checkbox:true},//,frozen:true
	   							  {align:'center',width:100,title:'市名', 'rowspan':3,	field:'city',editor:{ type:'combobox', options:{
	   								data:district, valueField: "city", textField: "city",
	   								onSelect:function(data){
	   							    	//获取市名的文本值
	   									//设置县级联动
	   									var rowIndex=0;//当前编辑行索引,默认为0
	   									 //如果是添加获取第一行，行号c
	   									if(flag=='add'){
	   										rowIndex=0;
	   									}else{
	   										//是修改，获取当前选择行，行号
	   	                                 	var row = $('#DataTemplateTable').datagrid('getSelected');  
	   	                                	rowIndex = $('#DataTemplateTable').datagrid('getRowIndex',row);//获取行号  
	   									}
	   									var target = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
	   									
	   									//清空县级列表数据
	   									target.combobox('clear');
	   									//县级发送远程服务，根据市名查询所有县/区
	   									var url=encodeURI('${pageContext.request.contextPath}/district/queryCountyByCityName?cityName='+data.city);
	   									//县级下拉框列表重载数据
	   									target.combobox('reload',url);
	   								}
	   							  }}},
	   					{align:'center',width:100,title:'县名',	 'rowspan':3,	field:'county',editor:{ type:'combobox', options:{
	   					data:district ,valueField: "anumber",textField: "county"
	   					}}},
	   		 ]);
			//添加表头
			columnsOneTab.push(  
				{align:'center',width:100,title:'文件',nowrap:false,  'rowspan':3,	field:'filesUrl'});
	   	}
	    var year = $("#year").val();//获取当前选取的年份
	    ZLLB = $("#zllb").combobox('getValue');//工程field
		var GCLB = $("#gclb").combobox('getValue');//工程field
		var zllbText = $("#zllb").combobox('getText');//获取工程名称
		if(ZLLB=="" || ZLLB==null){	
			$.post("${pageContext.request.contextPath}/task/getTableHeader",{"year":year,"disCode":targetNode,"GCLB":GCLB},function(data){
				for(var i=0;i<data.length;i++){
					//拼接一级表头数据
					columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
					var epc = data[i].list;
					if(data[i].list.length > 0){
						for(var j=0;j<epc.length;j++){
							var ename = epc[j].ename;
							var field = epc[j].field;
							//拼接二级表头数据
							columnsTowTab.push({title:''+ename+'',width:115*3,align:'center',colspan:3});//field:''+field+'',
							//拼接三级表头数据
							columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:115,align:'center',editor:{ type:'text', options:{}}},
												 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:115,align:'center'},
												 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:115,align:'center'});
						}
					}
				}
				header.push(columnsOneTab);
				header.push(columnsTowTab);
				header.push(columnsThreeTab);
			});
		}else{
			$.post("${pageContext.request.contextPath}/epc/query",{"GCLB":GCLB,"ZLLB":ZLLB,"year":year,"disCode":targetNode},function(data){//查询所有工程
				if(data.length > 0){
				//拼接一级表头数据
				columnsOneTab.push({field:'',title:zllbText,width:100*data.length,colspan:data.length*3,align:'center'});
					for(var i=0;i<data.length;i++){
						var ename = data[i].ename;//获取工程名称
						//拼接二级表头数据
						columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
						//拼接三级表头数据
						columnsThreeTab.push({field:'jh'+ZLLB+"Y"+data[i].mark,title:'计划',width:100,align:'center',editor:{ type:'text', options:{}}},
											 {field:'wc'+ZLLB+"Y"+data[i].mark,title:'完成',width:100,align:'center'},
											 {field:'zjh'+ZLLB+"Y"+data[i].mark,title:'占计划%',width:100,align:'center'});
					}
				}
			header.push(columnsOneTab);
			header.push(columnsTowTab);
			header.push(columnsThreeTab);
		    });
		}
	}

    var currentOnClickCellIdenx;//当前点击单元格的索引值
    //页面加载 初始化
   
    $(function(){
    	$('#dataDr').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.context = $('<p/>').text('Uploading...').appendTo(document.body);
                data.submit();
            },
            done: function (e, data) {
                data.context.text('Upload finished.');
            }
        });

    	/* $("#dataDr").on('click',function(){
    		$("#file").trigger("click");
    	}); */
    	$.ajaxSettings.async=false;//同步
        //初始化树
        $("#tree").tree({
       		url:'${pageContext.request.contextPath}/district/queryTreeNode',
       		animate:true,
       		onClick:TreeNodeEvent
       	});
   		$("#uploadFileAlabel").hide();//隐藏上传文件按钮
    	initHeader();//取表头数据
    	initTables();//加载数据表格数据
    	var record;
     	$("#DataTemplateTable").datagrid({ 
     		onClickRow: function(){
				if(record==1){
					$("#DataTemplateTable").datagrid('unselectAll');
				}
     		},
        	onClickCell: function(index,field,value){
        		record=0;
        		console.log("field="+field+" index="+index);
        		if(field!='filesUrl'){
        			return;
        		}else{
        			record=1;//记录操作状态
        			currentOnClickCellIdenx=index;//当前点击单元格索引值
    				var _eleDiv = $("#uploadFileButton");
					_eleDiv.siblings(".pics").find("table").children().find("td").remove(); //清空显示上传的文件
    				var begin = value.indexOf("value='[");
    				var end = value.indexOf("]'");
    				var filesStr = value.substring(begin,end);
    				//去掉前缀
    				var fileStr = filesStr.split("value='[")[1];
    				var files = fileStr.split(",");
    				//添加到数据库
        			var rows = $('#DataTemplateTable').datagrid('getRows'); 
        			var row = rows[currentOnClickCellIdenx];//获取当前上传文件的行
    				var taskNumbers = new Array();
    	    		//var existFiles = row["fileName"];//获取当前点击行所有文件
    				var year = $("#year").val();//获取年份
    				var countyCode = row["county"];//获取县级行政编号
    				var cityName = row["city"];//获取县级行政编号
    				
    				if($.trim(files).length>0){//判断是否有文件
						for(var i=0;i<files.length;i++){
							//文件显示拼接	
							var fileNameAndFileUrl = files[i].split("==");
							_eleDiv.siblings(".pics").find("table").append('\
								<tr><td style="border-color:gray;" align="center" ><a href="javascript:void(0);" class="download_uploadFile"  target="_blank">'+ fileNameAndFileUrl[1] +'</a>\
									<input name="fileUrl" value="'+ files[i] +'"  type="hidden"></input></td>\
										<td align="center" style="border-color:gray;"><input class="delete_uploadFile  button-style" type="button" value="删除"/></td></tr>');//文件删除按钮
										
										var node =$("#tree").tree('getSelected');
										if(node==null||node.id=='45'){
											$(".delete_uploadFile").hide();
										}
							//删除文件事件
							$(".delete_uploadFile").each(function(i,e){
								$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
								$(e).click(function(){
									var deleteFileName = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
									var rows = $('#DataTemplateTable').datagrid('getRows');    
									var row = rows[i];    // your row data
									for(var key in row){
										console.log("key="+key+" :value="+row[key]);
									}
									$(e).parent().parent().remove();//删除当前页面的元素
									//获取删除后，所有还存在的文件
								    var _eleDiv = $("#uploadFileButton");
									var children = _eleDiv.siblings(".pics").find("table").children();
									var uploadFileUrlArray = [];
									for(var i =0;i<children.length;i++){
										uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
									}
									var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
									$.post("${pageContext.request.contextPath}/taskIssued/delete/uploadFile",{"deleteFileName":deleteFileName,"year":year,"cityName":cityName,"countyCode":countyCode,"uploadFiles":uploadFiles},function(AjaxResult){
										if(AjaxResult.status==200){
											$("#DataTemplateTable").datagrid('reload');
										}else{
											$.messager.alert('提示',AjaxResult.msg);
										}
									}); 
								});
							});
							//绑定下载事件
							$(".download_uploadFile").each(function(i,e){//绑定删除事件
								$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
								$(e).click(function(){
									var fileName = $(e).val();	
									var fileUrl = $(this).siblings("input[name='fileUrl']").val();//获取文件夹根路径
									//文件下载请求
									window.location.href="${pageContext.request.contextPath}/taskIssued/download/uploadFile?fileUrl="+encodeURI(fileUrl);
								});
							});
						}
    				}
    				$("#uploadFileDiv").window('open');
				}
        	}
    	});
		var year = $("#year").val();
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var targetNode=root.id;//默认为父节点id
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	if(node!=null&&node.id != null){
	   		targetNode = node.id;
	   	}
     	$('#zllb').combobox({    
     	    url:'${pageContext.request.contextPath}/task/queryAll?year='+year+"&areaCode="+targetNode,    
     	    valueField:'mark',    
     	    textField:'tname'   
     	});   
     	
     	$('#gclb').combobox({    
     	    url:'${pageContext.request.contextPath}/epc/queryAll?year='+year+"&areaCode="+targetNode,    
     	    valueField:'mark',    
     	    textField:'ename'   
     	});  
     	
    	 
   		//文件上传 
		$('#uploadFile').fileupload({
			dataType: 'json',//返回的格式
			url:encodeURI('${pageContext.request.contextPath}/taskIssued/file/upload'),
			done: function (e, data) { //上传结束的操作 
				//var _ele = $("#uploadFile");
				if (data.result.status!=200) //上传失败时候 弹出消息
				{
					alert(data.result);
				}
				else	
				{
					//添加到数据库
        			var rows = $('#DataTemplateTable').datagrid('getRows'); 
        			var row = rows[currentOnClickCellIdenx];//获取当前上传文件的行
    				var taskNumbers = new Array();
    	    		//var existFiles = row["fileName"];//获取当前点击行所有文件
    				var year = $("#year").val();//获取年份
    				var countyCode = row["county"];//获取县级行政编号
					//文件上传成功，在页面进行回显
					var _eleDiv = $("#uploadFileButton");
    				console.log("data="+data);
    				console.log("data.result="+data.result);
    				console.log("data.result.fileUrl="+data.result.fileUrl);
    				console.log("data.result.fileName="+data.result.fileName);
						_eleDiv.siblings(".pics").find("table").append('\
								<tr><td  align="center" ><a href="javascript:void(0);" class="download_uploadFile"  target="_blank">'+data.result.fileName +'</a>\
								<input name="fileUrl" value="'+data.result.fileUrl+'"  type="hidden"></input></td>\
									<td  align="center" ><input class="delete_uploadFile button-style" type="button" value="删除"/></td></tr>');//文件删除按钮
					    var _eleDiv = $("#uploadFileButton");
						var children = _eleDiv.siblings(".pics").find("table").children();
						var uploadFileUrlArray = [];
						for(var i =0;i<children.length;i++){
							uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
						}
						var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
    	    			// 数据库添加文件
     	   			   $.ajax({
    					   //判断是添加还是修改，请求不同的url
    						url:"${pageContext.request.contextPath}/taskIssued/fileUpload/update",
    						dataType: "json",
    						type: "post",
    						data: {"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式
    						traditional: true,
    						success: function (data) {
    							if(data.status!=200){
    								$.messager.alert("提示",data.msg,"warning");
    							}
    							$("#DataTemplateTable").datagrid('reload');  
    	  					}
    					}); 
						
						
						
						//删除文件事件
						$(".delete_uploadFile").each(function(i,e){
							$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
							$(e).click(function(){
								var deleteFileName = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
								var rows = $('#DataTemplateTable').datagrid('getRows');    
								var row = rows[0];    // your row data
			    				var cityName = row["city"];//获取县级行政编号
			    				var countyCode = row["county"];//获取县级行政编号
								$(e).parent().parent().remove();//删除当前页面的元素
			    				var year = $("#year").val();//获取年份
								//获取删除后，所有还存在的文件
							    var _eleDiv = $("#uploadFileButton");
								var children = _eleDiv.siblings(".pics").find("table").children();
								var uploadFileUrlArray = [];
								for(var i =0;i<children.length;i++){
									uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
								}
								var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
								$.post("${pageContext.request.contextPath}/taskIssued/delete/uploadFile",{"deleteFileName":deleteFileName,"year":year,"cityName":cityName,"countyCode":countyCode,"uploadFiles":uploadFiles},function(AjaxResult){
									if(AjaxResult.status==200){
										$("#DataTemplateTable").datagrid('reload');
									}else{
										$.messager.alert('提示',AjaxResult.msg);
									}
								}); 
							});
						});
						
					//绑定删除事件				
/* 					$(".delete_uploadFile").each(function(i,e){
							$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
							$(e).click(function(){
								var fileUrl = $(this).parent().siblings().children("input[name='fileUrl']").val();//获取文件夹根路径
 								$.post("${pageContext.request.contextPath}/taskIssued/delete/uploadFile",{"fileUrl":fileUrl},function(AjaxResult){
									if(AjaxResult.status==200){
										$(e).parent().parent().remove();//删除当前页面的元素
										//获取所有已存在文件
									    var _eleDiv = $("#uploadFileButton");
										var children = _eleDiv.siblings(".pics").find("table").children();
										var uploadFileUrlArray = [];
										for(var i =0;i<children.length;i++){
											uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
										}
										var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
										//数据库删除文件
										$.post("${pageContext.request.contextPath}/taskIssued/fileUpload/delete"
												,{"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},function(data){
					    							$("#DataTemplateTable").datagrid('reload');  
										}); 
									}else{
										$.messager.alert('提示',AjaxResult.msg);
									}
								}); 
							});
					}); */
					//绑定下载事件
					$(".download_uploadFile").each(function(i,e){//绑定删除事件
						$(e).unbind("click");//每次初始化 都要取消上一次初始化所绑定的事件
						$(e).click(function(){
							var fileName = $(e).val();	
							var fileUrl = $(this).siblings("input[name='fileUrl']").val();//获取文件夹根路径
							//文件下载请求
							window.location.href="${pageContext.request.contextPath}/taskIssued/download/uploadFile?fileUrl="+encodeURI(fileUrl);
						});
					});
				}
			},
     }); 
    
			
    });
  /*    function toLead(){
    	$("#btn").upload({
    		action:'${pageContext.request.contextPath}/TaskIssued/toLead',
    		name:'excelName',
    	});
  }  */
  
  
      function Map() {
          var obj = {};
          this.put = function(key, value) {
              obj[key] = value;//把键值绑定到obj对象上
          }
          //size方法，获取Map容器的个数
          this.size = function() {
              var count = 0;
              for(var attr in obj) {
                  count++;
              }
              return count;
          }
          //get方法，根据key获取value的值
          this.get = function(key) {
              if(obj[key] || obj[key] === 0 || obj[key] === false) {
                  return obj[key]
              } else {
                  return null;
              }
          }
          //remove方法,删除方法
          this.remove = function(key) {
              if(obj[key] || obj[key] === 0 || obj[key] === false) {
                  delete obj[key]
              }
          }
          //each方法,遍历方法
          this.eachMap = function(callBack) {
              for(var attr in obj) {
                  callBack(attr, obj[attr])
              }
          }
      }
  

   ///*  }); */
    //点击年份的时候触发
    $("#year").numberspinner({
    	"onChange":function(){
    		 ZLLB = $("#zllb").combobox('getValue');	
    		//如果工程类别选项不为空 则按照工程类别对应方法进行初始化 表格
    			initHeader();//取表头数据
    			initTables();//加载数据表格数据
    	}
    });
	//Tree节点点击事件
	function TreeNodeEvent(){
		$("#zllb").combobox('clear');//清楚工程列表
		$("#gclb").combobox('clear');//清楚工程列表
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	if(node==null||node.id=="45"){
	   		$("#uploadFileAlabel").hide();//隐藏文件上传标签
	   	}else{
	   		$("#uploadFileAlabel").show();//显示文件上传标签
	   	}
		initHeader();//取表头数据
		initTables();//加载数据表格数据
	}
	var updateData =new Map();//存储需要修改行，修改前的旧数据

	//初始化不指定工程的数据
	function initTables(){
		var node =$("#tree").tree('getSelected');//获取选择的节点
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var areaCode=root.id;//默认为父节点id
    	if(node!=null && node.id != null){
    		areaCode=node.id;	
    	}
		var GCLB = $("#gclb").combobox('getValue');//工程field
    	var ZLLB = $("#zllb").combobox('getValue');//获取当前选择的造林类别;
    	 ZLLB = $("#zllb").combobox('getValue');//获取当前选择的造林类别;
    	//页面加载初始化表格
    	var year = $("#year").val();
		$.ajaxSettings.async = false; 
		$("#DataTemplateTable").datagrid({
    		url:encodeURI('${pageContext.request.contextPath}/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&GCLB="+GCLB),
    		//rownumbers:true,
			fit:true,
    		//url:'${pageContext.request.contextPath}/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&usr="+usr,
    		nowrap:false,
    		rownumbers:true,
    		pagination:true,
    		//checkOnSelect:false,
    		fitColumns:false,
    		border : false,
			striped : true,
    		columns:header,
    		frozenColumns:frozenColumnsTab,
    		toolbar:'#toolbar',
    		//结束编辑 触发
	    	onAfterEdit:function(index , record){
	    		var updated = $("#DataTemplateTable").datagrid('getChanges','updated');
			    for(var onekey in updated){
					console.log("onekey="+onekey);		    	
			    }
	    		var _eleDiv = $("#uploadFileButton");
				var children = _eleDiv.siblings(".pics").find("table").children();
				var uploadFileUrlArray = [];
				for(var i =0;i<children.length;i++){
					uploadFileUrlArray.push($(children[i]).find("input[name='fileUrl']").val());
				}
				var uploadFiles = uploadFileUrlArray.join(",");//把需要上传的文件用","进行拼接
				_eleDiv.siblings(".pics").find("table").children().find("td").remove();  //清空显示上传的文件
				var taskNumbers = new Array();
				//var EpcTaskList = new Array();
				var existFiles="";
				if(flag=='add'){//判斷是否是添加操作
				//判断数组是否有k和v的值
		    		for(var key in record){
						if(key=="fileName"){
							existFiles = record[key];//获取当前点击行已存在的文件
						}
						if(key.indexOf('jh')==0){//判断是否为jh字母开头的  true为任务下发数量的Key
							taskNumbers.push(key+":"+record[key]);//添加到任务基数的数值中
						}
						
					}
				}
				if(uploadFiles==null || uploadFiles==""){
					uploadFiles=existFiles;
				}
				var year = $("#year").val();
				var countyCode = record["county"];//获取县级行政编号
				var messageContent = $("#messageContent").val();
				if(flag!='add'){//判斷是否是修改操作
					taskNumbers = new Array();;
		    		for(var recordKey in record){
						if(recordKey=="fileName"){
							existFiles = record[recordKey];//获取当前点击行已存在的文件
						}
						if(recordKey.indexOf('jh')==-1){//判断是否为jh字母开头的  true为任务下发数量的Key 否則跳過此次循環
		    				continue;
		    			}
		    			updateData.eachMap(function(key ,value) {//遍历旧数据
							if(key==recordKey){//判断Key值是否想等
								var recordVal= record[recordKey];//获取改变行的新数据
								if(recordVal!=value){//旧数据的值和新数据的值不等于，说明数据被改变了
									taskNumbers.push(recordKey+":"+record[recordKey]);//添加到数据传入后台进行修改
								}
							}
						});
					}
				}
				// ajax请求
     			   $.ajax({
				   //判断是添加还是修改，请求不同的url
					url: flag=='add'?"${pageContext.request.contextPath}/taskIssued/add":"${pageContext.request.contextPath}/taskIssued/update",
					dataType: "json",
					type: "post",
					data: { "taskNumbers": taskNumbers ,"disCode":areaCode,"usr":usr,"year":year,"countyCode":countyCode,"uploadFiles":uploadFiles},//使用这种数组方式的，得加下一句才可以，使用传统方式,"disCode":areaCode,"usr":usr
					traditional: true,
					success: function (data) {
						if(data.status!=200){
							$.messager.alert("提示",data.msg,"warning");
						}
						$("#DataTemplateTable").datagrid('reload');  
  					}
				});    
	    	}, 
	    	
    	});
		//获取当前浏览器的类型
    	/* var userAgent = navigator.userAgent;
		//console.info(userAgent);
		if(node != null){
			if(userAgent.indexOf("Trident") > -1){//判断是否是ie浏览器
				$(".datagrid-body").css({"width":"1590px !important"});
			}else if(userAgent.indexOf("Firefox") > -1){
				$(".datagrid-body").css({"width":"848px"});
				console.info(userAgent);
			}
		} */
		
	}
	
	
	
	/*修改*/
	function modify(){
		  var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
		  var rowIndex=$('#DataTemplateTable').datagrid('getRowIndex',$('#DataTemplateTable').datagrid('getSelected'));
		  var rows=$('#DataTemplateTable').datagrid('getRows');
	  	  var row = rows[rowIndex];
	  	  for(var key in row){
	  		  if(key.indexOf("jh")!=-1){
	  			updateData.put(key,row[key]);
	  		  }
	  	  }
		  if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	      $.messager.alert("提示","选择市级以下地区才可以修改!",'warning');
	  	      return
	  	  }
		  var rows = $('#DataTemplateTable').datagrid('getSelections');
		  if(rows.length!=1){
		  	$.messager.alert('提示','只能选择一条数据进行修改','warning');
		  }else{
			  if(editing == undefined){
				  //获取要修改行索引
				  flag='edit';
			 	  editing = $('#DataTemplateTable').datagrid('getRowIndex',rows[0]);
			 	  $('#DataTemplateTable').datagrid('beginEdit',editing);
				  var cityEditor = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'city',type:'combobox'}).target;
				  var countyEditor = $("#DataTemplateTable").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
				  $(cityEditor).combobox('disable');//让市选择框失效
				  $(countyEditor).combobox('disable');//让县选择框失效
			  }
		 }
	}
	

	//选择 查看工程触发
    $("#zllb").combobox({
    	onSelect:zllbchanged
    });
	
	function zllbchanged(){
		initHeader();
		initTables();
    }
	
		//选择 查看工程触发
	    $("#gclb").combobox({
	    	onSelect:gclbchanged
	    });
		function gclbchanged(){
			initHeader();
			initTables();
	    }
	
	$("#searchKey").searchbox({
  		 searcher:searched
	});
	
	function searched(searchKey){
		var url='${pageContext.request.contextPath}/TaskWorking/pageQueryBySearchKey?searchKey='+searchKey;
	    $("#DataTemplateTable").datagrid({	
	    	url:url,
	    	fit:true,
	    	border : false,
			pagination:true,
			rownumbers:true, 
    		checkOnSelect:false,
    		fitColumns:false,
    		border : false,
			striped : true,
			columns:header,
    		toolbar:'#toolbar',
    		
		});
	}
	
	var editing;
    var flag=undefined; ////判断新增和修改方法
	 //导入
   function drExcel(){
	   	$("#dr").upload({
	   		action:'${pageContext.request.contextPath}/dr',
	   		name:'exceName'
	   	});
   }
   

	//关闭文件上传窗口
	function closedForm(){
		$("#uploadFileDiv").window('close');
	} 
   
   
  /**添加*/
	function add(){ 
			$("#DataTemplateTable").datagrid('endEdit',editing);
		   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	  	    if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	    	$.messager.alert("提示","选择市级以下地区才可以添加!",'warning');
	  	      	return
	  	    }
	  	    $('#DataTemplateTable').datagrid('unselectAll');
			editing=0;
			$("#DataTemplateTable").datagrid("insertRow", {index: 0, row: {}});
			//将新插入的那一行开户编辑状态
			$("#DataTemplateTable").datagrid("beginEdit", 0);
			flag='add';
	}
  

  

	
	/*批量删除*/
	function remove(){
		  var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	  	  if(node==null || node.id=='45'){//不包含县级，不能添加数据
	  	      $.messager.alert("提示","选择市级以下地区才可以删除!",'warning');
	  	      return
	  	  }
		var year = $("#year").val();
		//获取全部选中的行
		var rows = $('#DataTemplateTable').datagrid('getSelections');
		//判断是否有选中的行
		if(rows.length>0){
			//批量删除
		$.messager.confirm('警告','你确定要删除当前选中的'+rows.length+'条已发布的任务吗？',function(r){
			if(r){
		    	var params = new Array();
		    	var param="";
		    	//如果要删除的数据只有一条 则 直接传json字符串到后台
		    	if(rows.length==1){
		    		param = JSON.stringify(rows[0]);
		    	}else{
		    	//如果要删除一条以上数据则 则传string[]数组 到后台
		    		for(var i=0;i<rows.length;i++){
		        		var ro = JSON.stringify(rows[i]);
		        		params.push(ro);
		        	}
		    	}
	    		
				$.ajax({
				   //判断是添加还是修改，请求不同的url
					url: "${pageContext.request.contextPath}/delete/taskIssued",
					dataType: "json",
					type: "post",
					data: {"params": params ,"param":param,"year":year},//使	用这种数组方式的，得加下一句才可以，使用传统方式
					traditional: true, 
					success: function (data) {
							$("#DataTemplateTable").datagrid('reload');
	    				}
				});
					
			}
		  });
			//删除完毕 重新设置editing状态
			editing = undefined;
		}else{
			$.messager.alert('提示','请最少选择一行进行删除','warning');
		}
	}
	/*保存*/
	function save(){
		$("#DataTemplateTable").datagrid('endEdit',editing);
		editing = undefined;
	}

	   
	/*取消编辑状态*/
	function cancelEdit(){
		//回滚事物
        $('#DataTemplateTable').datagrid('rejectChanges');  
        var _eleDiv = $("#uploadFileButton");
        _eleDiv.siblings(".pics").find("table").children().find("td").remove(); //清空显示上传的文件
        editing = undefined;  
	}
	
 	$("#read").click(function(){
  	 	    $.post("${pageContext.request.contextPath}/message/read",function(){
  	 	    	$("#hint").load(location.href + " #hint");//在此刷新页面
	    	//去掉定时器的方法  
	    	//window.setTimeout(timerc); 
	    	$("#toptips").hide();//隐藏消息提示的div
	    });  
  	 }); 
    </script>
</html>
