<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/additional-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
<style type="text/css">
.datagrid-body{
style:"width:625px"
}
span.searchbox{
margin-bottom:-8px;
}
.layout-split-west{/* panel layout-panel layout-panel-west  */
width:155px !important; 
}
.layout-panel-center{
left: 156px !important;
}
.datagrid{
margin-top: -15px;
margin-left: -10px;
height: 530px;
width: 1148px;
}
#uu ul ul{
display: none;
position: absolute;
top: 25px;
font-size: 12px;
}
</style>
</head>
<body id="bod" class="easyui-layout" >

	    <div data-options="region:'west',title:'',split:true,border : false" style="width:142px;margin-left: 5px;height: 550px;">
	    
		    <ul id="tt" class="easyui-tree">
		    
		    </ul>
	    </div>
		<div id="ce" data-options="region:'center',title:'',border : false" style="width:1000px,padding:5px;background:#eee;">
			
			<div id="tabs" class="easyui-tabs" data-options="fit:true" > 

				<div title="森林景观改造" style="padding:20px;">
					<table id="landscapeRenovation" ></table>
				</div>
				
				<div title="森林质量提升" data-options="" style="overflow:auto;padding:20px;">
					<table id="qualityPromotion"></table>
				</div> 
				
				<div title="汇总" data-options="" style="padding:20px;"><table id="collect"></table>333
				
				<a id="dd" href="javascript:void(0)">Click here</a>
				
				
				</div> 
			
			</div> 

		</div>
</body>
<script type="text/javascript">
var sname = null;
var puptime = null;
var stime = null;
var flag = null;
var qccty = null;

var list = new Array();
$(".nav  li").hover(function(){
	$(this).find('ul').css('display','block');
},function(){
	$(this).find('ul').css('display', 'none');
});
//当前登录的用户
var usr = "${sessionScope.user.username}";

//展示树
$("#tt").tree({
		url:'${pageContext.request.contextPath}/show_dis',
		animate:true,
		onClick:click
});
//当点击树的时候触发
function click(){
	
}

$('#dd').tooltip({
    position: 'right',
    content: '<span style="color:#fff">This is the tooltip message.</span>',
    onShow: function(){
		$(this).tooltip('tip').css({
			backgroundColor: '#666',
			borderColor: '#666'
		});
    }
});

$("#tabs").tabs({
	onSelect:select
});
function select(title,index){
	
	//景观改造
	if(index==0){
		$("#landscapeRenovation").datagrid({
			url:'${pageContext.request.contextPath}/LandscapeRenovation/findall',
			fit:true,
			border : false,
			striped : true,
			rownumbers:true,
			pagination:true,
			columns:[
				[
					{align:'center',colspan:10,width:620,title:'合计',		field:''},
					{align:'center',colspan:9,width:620,title:'新造林',		field:''},
					{align:'center',colspan:9,width:620,title:'补植开花彩叶树种',		field:''},
				],[
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'designarea'},
					{align:'center',rowspan:2,width:80,title:'自检合格面积',		field:'iqr'},
					{align:'center',colspan:2,width:120,title:'核实面积',		field:''},
					{align:'center',colspan:2,width:120,title:'核实合格面积',		field:''},
					{align:'center',colspan:2,width:120,title:'抚育面积',		field:''},
					{align:'center',colspan:2,width:120,title:'管护面积',		field:''},
					
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'newdesignarea'},
					{align:'center',rowspan:2,width:60,title:'自检合格面积',		field:'newiqr'},
					{align:'center',rowspan:2,width:60,title:'核实面积',		field:'newverifyarea'},
					{align:'center',rowspan:2,width:60,title:'核实合格面积',		field:'newvqa'},
					{align:'center',rowspan:2,width:60,title:'抚育面积',		field:'newnurturearea'},
					{align:'center',rowspan:2,width:60,title:'管护面积',		field:'newmanagearea'},
					{align:'center',rowspan:2,width:60,title:'核实面积合格率',		field:'newvrqr'},
					{align:'center',rowspan:2,width:60,title:'抚育率',		field:'newnurture'},
					{align:'center',rowspan:2,width:60,title:'管护率',		field:'newmanage'},
					
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'patchdesignarea'},
					{align:'center',rowspan:2,width:60,title:'自检合格面积',		field:'patchiqr'},
					{align:'center',rowspan:2,width:60,title:'核实面积',		field:'patchverifyarea'},
					{align:'center',rowspan:2,width:60,title:'核实合格面积',		field:'patchvqa'},
					{align:'center',rowspan:2,width:60,title:'抚育面积',		field:'patchnurturearea'},
					{align:'center',rowspan:2,width:60,title:'管护面积',		field:'patchmanagearea'},
					{align:'center',rowspan:2,width:60,title:'核实面积合格率',		field:'patchvrqr'},
					{align:'center',rowspan:2,width:60,title:'抚育率',		field:'patchnurture'},
					{align:'center',rowspan:2,width:60,title:'管护率',		field:'patchmanage'},
				],[
					{align:'center',width:60,title:'计',		field:'verifyareat'},
					{align:'center',width:60,title:'核实率',		field:'verifyarea'},
					{align:'center',width:60,title:'计',		field:'vqat'},
					{align:'center',width:60,title:'合格率',		field:'vqaqr'},
					{align:'center',width:60,title:'计',		field:'nurtureareat'},
					{align:'center',width:60,title:'抚育率',		field:'nanr'},
					{align:'center',width:60,title:'计',		field:'manageareat'},
					{align:'center',width:60,title:'管护率',		field:'manageareamr'},
				]
				],
			toolbar:"",
		});
	}
	
	//质量提升
	if(index==1){
		$("#qualityPromotion").datagrid({
			url:'${pageContext.request.contextPath}/qualityPromotion/findall',
			fit:true,
			border : false,
			striped : true,
			rownumbers:true,
			pagination:true,
			columns:[
				[
					{align:'center',colspan:10,width:620,title:'合计',		field:''},
					{align:'center',colspan:9,width:620,title:'桉树纯林提升',		field:''},
					{align:'center',colspan:9,width:620,title:'其他低效林提升',		field:''},
				],[
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'designarea'},
					{align:'center',rowspan:2,width:80,title:'自检合格面积',		field:'iqr'},
					{align:'center',colspan:2,width:120,title:'核实面积',		field:''},
					{align:'center',colspan:2,width:120,title:'核实合格面积',		field:''},
					{align:'center',colspan:2,width:120,title:'抚育面积',		field:''},
					{align:'center',colspan:2,width:120,title:'管护面积',		field:''},
					
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'newdesignarea'},
					{align:'center',rowspan:2,width:60,title:'自检合格面积',		field:'newiqr'},
					{align:'center',rowspan:2,width:60,title:'核实面积',		field:'newverifyarea'},
					{align:'center',rowspan:2,width:60,title:'核实合格面积',		field:'newvqa'},
					{align:'center',rowspan:2,width:60,title:'抚育面积',		field:'newnurturearea'},
					{align:'center',rowspan:2,width:60,title:'管护面积',		field:'newmanagearea'},
					{align:'center',rowspan:2,width:60,title:'核实面积合格率',		field:'newvrqr'},
					{align:'center',rowspan:2,width:60,title:'抚育率',		field:'newnurture'},
					{align:'center',rowspan:2,width:60,title:'管护率',		field:'newmanage'},
					
					{align:'center',rowspan:2,width:60,title:'设计面积',		field:'patchdesignarea'},
					{align:'center',rowspan:2,width:60,title:'自检合格面积',		field:'patchiqr'},
					{align:'center',rowspan:2,width:60,title:'核实面积',		field:'patchverifyarea'},
					{align:'center',rowspan:2,width:60,title:'核实合格面积',		field:'patchvqa'},
					{align:'center',rowspan:2,width:60,title:'抚育面积',		field:'patchnurturearea'},
					{align:'center',rowspan:2,width:60,title:'管护面积',		field:'patchmanagearea'},
					{align:'center',rowspan:2,width:60,title:'核实面积合格率',		field:'patchvrqr'},
					{align:'center',rowspan:2,width:60,title:'抚育率',		field:'patchnurture'},
					{align:'center',rowspan:2,width:60,title:'管护率',		field:'patchmanage'},
				],[
					{align:'center',width:60,title:'计',		field:'verifyareat'},
					{align:'center',width:60,title:'核实率',		field:'verifyarea'},
					{align:'center',width:60,title:'计',		field:'vqat'},
					{align:'center',width:60,title:'合格率',		field:'vqaqr'},
					{align:'center',width:60,title:'计',		field:'nurtureareat'},
					{align:'center',width:60,title:'抚育率',		field:'nanr'},
					{align:'center',width:60,title:'计',		field:'manageareat'},
					{align:'center',width:60,title:'管护率',		field:'manageareamr'},
				]
				],
			toolbar:"",
		});
	}
	
}
</script>
</html>