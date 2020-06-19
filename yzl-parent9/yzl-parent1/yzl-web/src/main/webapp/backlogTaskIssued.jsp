<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%@taglib prefix="c" uri="/jakarta-taglibs-standard-1.1.0-B1/tld/c-1_0-rt.tld" %> --%>
<%-- <%@taglib prefix="fmt" uri="/jakarta-taglibs-standard-1.1.0-B1/tld/c-rt.tld" %> --%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="WEB-INF/tld/c.tld" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%-- 

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  --%>
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
.datagrid-body{
	tyle:"width:1000px"
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
margin-top:35px;/* -3 */
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
margin-left:50px;
margin-top:-21px;
z-index: 1000
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
.nav-list li.active>a:after{
    border-right-color: #318c22;
}
.navbar{
    background: #ffffff;/* #318c22#eee */
}
.sidebar-shortcuts-large{
    background-color: #318c22;
}
.ace-nav>li>a{
color: #211f1f;
}
.nav-list>li{
    border-bottom: 1px solid #e5e5e5;
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

.datagrid-toolbar{
	width:100%;
}
</style>

</head>

<body class="easyui-layout" >

		<div data-options="region:'west',title:'',split:true,border:false" style="width:140px;margin-left: 5px;">
		    <ul id="tree" class="easyui-tree">
		    
		    </ul>
	    </div> 
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
    
     <div id="toolbar" style="width:100%;dispaly:none">
    	<label style="font-size: 12px;" class="" id="labelName">工程类别:</label>
    	<!-- <label style="" class="label_name">工程类别:</label> -->
		<input id="gclb" class="easyui-combobox" name="project" />&nbsp;&nbsp;
    	
    	<label style="font-size: 12px;" class="" id="labelName">造林类别:</label> 
		<input id="zllb" class="easyui-combobox" name="dept" /> &nbsp;&nbsp;
    	      	
       	<!-- <input id=searchKey value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: 20px"> -->
		
		<label style="padding-left:5px;margin-top: -23px;font-size: 12px" class="label_name">年份:</label>
       	<input id="year" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:90px;;height:19px;margin-top: 0px">
       	
   </div>
  
   <!-- 文件上传窗口 -->
   <!-- 文件上传窗口 -->
     <div id="uploadFileDiv" class="easyui-window" title="上传窗口" style="width:850px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
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
	
<!-- 	<div style="margin-left:46%;margin-top:25%;mapadding:5px">
	    <a href="javascript:void(0);" class="easyui-linkbutton" onclick="closedForm()">关闭</a>
	</div> -->
</div>
</body>
<script type="text/javascript">
/* OpenFileDialog ofd = new OpenFileDialog();
ofd.ShowDialog();
console.info(ofd); */
$("#zllb").combobox({
	onLoadSuccess:load
});
function load(){
 	var as = $("#zllb").next();
	as.attr("id","zl");
}

//关闭文件上传窗口
function closedForm(){
	$("#uploadFileDiv").window('close');
} 

	//关闭提示
	$("#read").click(function(){
			console.log("---");
	 	    $.post("${pageContext.request.contextPath}/message/read",function(){
	 	    	$("#hint").load(location.href + " #hint");//在此刷新页面
	    	//去掉定时器的方法  
	    	//window.setTimeout(timerc); 
	    	$("#toptips").hide();//隐藏消息提示的div
	    });  
	 }); 

//当前登录的用户
var usr = "${sessionScope.user.username}";
var databtn = true;
$("#btn").upload({
	action:'${pageContext.request.contextPath}/TaskIssued/toLead',
	
	name:'excelName',
	onComplete: function(data) {
		
		if(databtn){
			databtn=false;
			if(data == 1){
	    		$.messager.alert("提示信息","数据导入成功！","info");
	    		$("#grid").datagrid("reload");
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

//$("#dataDr").prev("div").attr("id","test");
	$("#dataDr").parent().prop("id","dat");
	$("#dat").css({"margin-left":"615px"});
	$("#dat").css({"margin-top":"-27px"});
	$("#dat").css({"line-height":"15px"});
	$("#dat").css({"height":"20px"});
	$("#dat").css({"width":"105px"});
/* $("#dataDr").css({"margin-left":"100px"}); */
//if(par!=null){
///	console.info(par+"11111111");
//}
	$("#btn").parent().prop("id","bnt");
	$("#bnt").css({"margin-left":"720px"});
	$("#bnt").css({"margin-top":"-20px"});
	$("#bnt").css({"line-height":"15px"});
	$("#bnt").css({"height":"50px"});
	$("#bnt").css({"width":"78px"});
    
	var areaCode = "45";
	var ZLLB;
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
	   	var root =$("#tree").tree('getRoot');//获取顶级父节点
	   	var targetNode=root.id;//默认为父节点id
	   	var node =$("#tree").tree('getSelected');//获取当前被点击的树节点
	   	if(node!=null&&node.id != null){
	   		targetNode = node.id;
	   	}
	   	if(targetNode=='45'){
	   		//添加需要固定表头的数据
	   		frozenColumnsTab.push([	   		
	   			{align:'center',width:100,title:'序号',  rowspan:'3',field:'id',checkbox:true},
				{align:'center',width:100,title:'市名', 'rowspan':3,	field:'city'},
			]);
			//添加固定表头
			columnsOneTab.push({align:'center',width:100,title:'文件',  'rowspan':3,	field:'filesUrl'});
	   	}else{
	   		//添加固定表头的数据
	   		frozenColumnsTab.push([
	   					{align:'center',width:100,title:'序号',  rowspan:'3',field:'id',checkbox:true},
	   					{align:'center',width:100,title:'市名', 'rowspan':3,	field:'city',editor:{ type:'combobox', options:{
	   								data:district, valueField: "city", textField: "city",}}},
	   					{align:'center',width:100,title:'县名',	 'rowspan':3,	field:'county',editor:{ type:'combobox', options:{
	   					data:district ,valueField: "anumber",textField: "county"
	   					}}},
	   		 ]);
			//添加表头
			columnsOneTab.push(  
				{align:'center',width:100,title:'文件',  'rowspan':3,	field:'filesUrl'});
	   	}
	    var year = $("#year").val();//获取当前选取的年份
	    ZLLB = $("#zllb").combobox('getValue');//工程field
		var GCLB = $("#gclb").combobox('getValue');//工程field
		var zllbText = $("#zllb").combobox('getText');//获取工程名称
		$.post("${pageContext.request.contextPath}/backlogTaskIssued/getTableHeader",{"year":year,"disCode":targetNode,"GCLB":GCLB,"ZLLB":ZLLB},function(data){
			for(var i=0;i<data.length;i++){
				//拼接一级表头数据
				columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
				var epc = data[i].list;
				if(data[i].list.length > 0){
					for(var j=0;j<epc.length;j++){
						var ename = epc[j].ename;
						var field = epc[j].field;
						//拼接二级表头数据
						columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
						//拼接三级表头数据
						columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:100,align:'center',editor:{ type:'text', options:{}}},
											 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:100,align:'center'},
											 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:100,align:'center'});
					}
				}
			}
			header.push(columnsOneTab);
			header.push(columnsTowTab);
			header.push(columnsThreeTab);
		});

	}

    var currentOnClickCellIdenx;//当前点击单元格的索引值
    //页面加载 初始化
    $(function(){
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
								<tr><td align="center" ><a href="javascript:void(0);" class="download_uploadFile"  target="_blank">'+ fileNameAndFileUrl[1] +'</a>\
									<input name="fileUrl" value="'+ files[i] +'"  type="hidden"></input></td>\
										<td align="center" ><input  class="delete_uploadFile button-style" type="button" value="删除"/></td></tr>');//文件删除按钮
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

		
    
  /*    function toLead(){
    	$("#btn").upload({
    		action:'${pageContext.request.contextPath}/TaskIssued/toLead',
    		name:'excelName',
    	});
  }  */

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
    		url:encodeURI('${pageContext.request.contextPath}/backlogTaskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&GCLB="+GCLB),
    		//rownumbers:true,
			fit:true,
    		//url:'${pageContext.request.contextPath}/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&usr="+usr,
 
    		pagination:true,
    		checkOnSelect:false,
    		fitColumns:false,
    		border : false,
			striped : true,
    		columns:header,
    		frozenColumns:frozenColumnsTab,
       		rownumbers:true,
    		toolbar:'#toolbar',
    		//结束编辑 触发
    	});
		
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
	
	var editing;
    var flag=undefined; ////判断新增和修改方法
    </script>
</html>
