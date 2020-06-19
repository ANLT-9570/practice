<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--  <link href="assets/css/bootstrap.min.css" rel="stylesheet" /> -->
<!--   <link rel="stylesheet" href="assets/css/font-awesome.min.css" /> -->
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
<!--   <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/> -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath }/js/app/assets/javascripts/jquery-fileupload/jquery.fileupload.js"></script>
<title>xxx</title>
<style type="text/css">
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

/* //提示条样式 */
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

.bcbtn{
border-radius:4px;
color: gree;
}
.qxbtn{
border-radius:4px;
}
/* panel datagrid *//* datagrid-wrap panel-body panel-body-noheader panel-body-noborder */
.panel{001
}
</style>
</head>

<body class="easyui-layout">
		<!-- <div class="easyui-layout" style=" width: 1310px;height: 550px;margin-top: -5px"> -->
		    <div data-options="region:'west',title:'',split:true,border:false" style="height: 550px;width:142px;margin-left: 5px;">
		    
			    <ul id="tt" class="easyui-tree" >
			    
			    </ul>
		    </div>
				
			<div id="ce" data-options="region:'center',title:'',border:false" style="padding:2px 20px 5px 0px;background:#eee;">
				<table id="tab" style="display:none;height: 530px;"></table>
			</div>
    <!-- </div> -->
    
    <div id="tbs" style="height:28px;dispaly:none">
    	
    	<label class="" id="labelName" style="font-size: 12px;">造林类别:</label>
		<input style="width: 100px" id="task" class="easyui-combobox" name="dept" data-options="valueField:'mark',textField:'tname',url:'${pageContext.request.contextPath }/taskWorking/show_tasks'" />&nbsp;&nbsp;
		<shiro:hasPermission name="sys:rwgzz:tj">
      	 	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="submit()">提交</a>
       	</shiro:hasPermission>
       	<shiro:hasPermission name="sys:rwgzz:sh">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="audit()">审核</a>
       	</shiro:hasPermission>
       	
       	<shiro:hasPermission name="sys:rwgzz:th">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="back()">退回</a>
       	</shiro:hasPermission>
       	
       	
       	&nbsp;&nbsp;
       	<label class="" id="labelName" style="font-size: 12px;">年份:</label>
       	<input id=year value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:70px;;height:19px;margin-top: 0px">
       	
   </div>
    
    <script type="text/javascript">
	
    //当前登录的用户
    var usr = "${sessionScope.user.username}";
    var msg;
    var shmsg ;
    var msgth ;
    var msgtj ;
    var lea;//留言
    var sh1 = false;//sh1
    var sh2 = true;//sh2
    var th1 = false;//sh1
    var th2 = true;//sh2
    var tj1 = false;//sh1
    var tj2 = true;//sh2
    var stat;//记录状态  为1的时候执行退回   为2的时候执行提交    为3的时候执行审核
    var muniLength = null;//多少个市
    var coutyLength = null;//多少个县
    var countNode = null;//县的节点
    var cityNode = null;//市的节点
    var disCode;
    var bdgwj = new Array;//保存多个文件
    var taskMark = "";//工程编号
  //退回
    var backRows = null;
    var backNode = null;
    var backTime = null;
    var backCountys = new Array;//所有的县
  //提交
    var submitRows = null;
    var subCountys = new Array;
 	 //审核
 	 var auditRows = null;
 	 var auditCounty = new Array;
 	 
    function back(){
    	var node =$("#tt").tree('getSelected');
    	backNode =$("#tt").tree('getSelected');//获取选择的节点
    	if(backNode!=null){
    		disCode = backNode.id;
    	}
    	stat = 1;
    	backTime = $("#year").val();//获取时间
    	backRows = $("#tab").datagrid('getSelections');//获取选择的行
    	if(backRows.length == 0){
       		$.messager.alert('错误提示','必须选择一个','info');
       		return;
       	}
    	
   		for(var i=0;i<backRows.length;i++){
   			var r = JSON.stringify(backRows[i]);//转成json串
       		//将json字符串转成jsn对象
       		var JsonObj = eval('('+r+')');
       		var states = JsonObj.stat;
       		var county = JsonObj.county;
       		if(states == '3' || states == '0'){
       			$.messager.alert('错误提示','未提交不能退回！！','info');
           		return;
       		}
           	if(county == undefined){
           		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
           		return ;
           	}else{
           		backCountys.push(county);
           	}
   		}
    	
    	var backData = new Array();
    	var backDatas = new Array();
    	if(backRows.length == 1){
    		backDatas.push(JSON.stringify(backRows[0]));
    	}else{
	   		for(var i=0;i<backRows.length;i++){
	       		var ro = JSON.stringify(backRows[i]);
	       		backData.push(ro);
       	}
    }
    	if(th2){
    		$("#upt").window("open");
    	}
    	
    	
    	var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = node.id;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	node = cityNode;
        	disCode = node.id;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        	disCode = node.id;
        }
        
    	th1=false;
    	th2=true;
    }
    
    //点击保存后的退回走这里
    function saveBack(){
    	if(th1){
   			var backData = new Array();
   	    	var backDatas = new Array();
   	    	
   	    	if(backRows.length == 1){
   	    		backDatas.push(JSON.stringify(backRows[0]));
   	    	}else{
   	    		for(var i=0;i<backRows.length;i++){
   	        		var ro = JSON.stringify(backRows[i]);
   	        		backData.push(ro);
   	        	}
   	    	}
   	    	
      		$.ajaxSettings.async = false;//取消异步
   	    	$.ajax({
   	    		type:'post',
   	    		traditional: true,
   	    		async:false,
   	    		url:'${pageContext.request.contextPath}/backData',
   	    		data:{'backData':backData,"disCode":disCode,"usr":usr,'backDatas':backDatas,"time":backTime,"lea":lea,"fileNames":bdgwj,"countys":backCountys,"zllb":taskMark,"fileNames":bdgwj},
   				dataType:'json',
   	    		success:function(data){
   					if(data.data==200){
   						$.messager.alert('提示框','成功','info');
   						//$("#tab").datagrid('reload');
   						init();//获取数据表格表头数据
    					inTable();//初始化数据表格
   						$("#tbody").html("");
   						bdgwj = [];
   					}else{
   						$.messager.alert('提示框','出错!!','info');
   					}
   					
   				},
   	    	});
   	    	tj1=false;
           	tj2=true;
    	}
    }
    
    
    function submit(){
    	var node =$("#tt").tree('getSelected');
    	if(node!=null){
    		disCode = node.id;
    	}
    	stat = 2;
    	submitRows = $("#tab").datagrid('getSelections');
    	if(submitRows.length == 0){
    		$.messager.alert('错误提示','必须选择一个','info');
    		return;
    	}
    	
   		for(var i=0;i<submitRows.length;i++){
   			var r = JSON.stringify(submitRows[i]);//转成json串
       		//将json字符串转成jsn对象
       		var JsonObj = eval('('+r+')');
       		var states = JsonObj.stat;
       		var county = JsonObj.county;
       		if(states == '1'){
       			$.messager.alert('错误提示','以提交,不能重复提交！！','info');
           		return;
       		}
           	if(county == undefined){
           		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
           		return ;
           	}else{
           		subCountys.push(county);
           	}
   		}
    	
    	if(tj2){
    		$("#upt").window("open");
    	}
    	
    	var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = node.id;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	node = cityNode;
        	disCode = node.id;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        	disCode = node.id;
        }
        
    	tj1=false;
    	tj2=true;
    }
    
  	 //点击保存后的提交走这里
    function saveSubmit(){
    	if(tj1){
   			var time = $("#year").val();//获取时间
   			
   			var subData = new Array();
   	    	var subDatas = new Array();
   	    	
   	    	if(submitRows.length == 1){
   	    		subDatas.push(JSON.stringify(submitRows[0]));
   	    	}else{
   	    		for(var i=0;i<submitRows.length;i++){
   	        		var ro = JSON.stringify(submitRows[i]);
   	        		subData.push(ro);
   	        	}
   	    	}
   	    	
      		$.ajaxSettings.async = false;//取消异步
   	    	$.ajax({
   	    		type:'post',
   	    		traditional: true,
   	    		async:false,
   	    		url:'${pageContext.request.contextPath}/submit',
   	    		data:{'subData':subData,"disCode":disCode,"usr":usr,'subDatas':subDatas,"time":time,"lea":lea,"zllb":taskMark,"countys":subCountys,"fileNames":bdgwj},
   				dataType:'json',
   	    		success:function(data){
   					if(data.data==200){
   						$.messager.alert('提示框','成功','info');
   						//$("#tab").datagrid('reload');
   						init();//获取数据表格表头数据
    					inTable();//初始化数据表格
   						$("#tbody").html("");
   						bdgwj = [];
   					}else{
   						$.messager.alert('提示框','失败','info');
   					}
   				},
   	    	});
   	    	th1=false;
           	th2=true;
    	}
  	 }

     function audit(){
     	//获取选择的节点
   		var node =$("#tt").tree('getSelected');
     	if(node!=null){
     		disCode = node.id;
     	}
     	stat = 3;
     	auditRows = $("#tab").datagrid('getSelections');
     	
     	var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = node.id;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	node = cityNode;
        	disCode = node.id;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        	disCode = node.id;
        }
     	
     	if(auditRows.length == 0){
     		$.messager.alert('错误提示','必须选择一个','info');
     		return;
     	}
     	
    		for(var i=0;i<auditRows.length;i++){
    			var r = JSON.stringify(auditRows[i]);//转成json串
        		//将json字符串转成jsn对象
        		var JsonObj = eval('('+r+')');
        		var states = JsonObj.stat;
        		var county = JsonObj.county;
        		if(states == '3' || states == '0'){
     			$.messager.alert('错误提示','请先提交！！','info');
         		return;
     		}
            	if(county == undefined){
            		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
            		return ;
            	}else{
            		auditCounty.push(county);
            	}
    		}
     	if(sh2){
     		$("#upt").window("open");
     	}
     	sh1=false;
         sh2=true; 
     }
  
  	//点击保存后的提交走这里
  	function saveAudit(){
  		if(sh1){
  			var time = $("#year").val();//获取时间
			var eids = new Array();
	    	var countys = new Array;
	    	var json = new Array;
	    	
	    	$.ajaxSettings.async = false;//取消异步
			var auditData = new Array();
	    	var auditDatas = new Array();
	    	
	    	if(auditRows.length == 1){
	    		auditDatas.push(JSON.stringify(auditRows[0]));
	    	}else{
	    		for(var i=0;i<auditRows.length;i++){
	        		var ro = JSON.stringify(auditRows[i]);
	        		auditData.push(ro);
	        	}
	    	}
	    	$.ajax({
	    		type:'post',
	    		traditional: true,
	    		async:false,
	    		url:'${pageContext.request.contextPath}/auditData',
	    		data:{'auditData':auditData,"disCode":disCode,'auditDatas':auditDatas,"time":time,"zllb":taskMark,"countys":auditCounty,"lea":lea,"fileNames":bdgwj},
				dataType:'json',
	    		success:function(data){
					if(data.data==200){
						$.messager.alert('提示框','成功','info');
						//$("#tab").datagrid('reload');
						init();//获取数据表格表头数据
    					inTable();//初始化数据表格
						$("#tbody").html("");
						bdgwj = [];
					}else{
						$.messager.alert('提示框','失败','info');
					}
				},
	    	});
		}	
  	}
  	
  //添加固定表头
	var frozenColumnsTab = new Array();
	var columnsTabHead = new Array();
	
  //选择 查看工程触发
    $("#task").combobox({
    	onSelect:changed
    });
  	
  	function changed(){
  		frozenColumnsTab = new Array();
    	columnsTabHead = new Array();
    	
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	
	  	//获取时间
	    var year = $("#year").val();
	    var node =$("#tt").tree('getSelected');//点击的行政编号
	    taskMark = $("#task").combobox('getValue');//工程编号
		var text = $("#task").combobox('getText');//工程名称
		
		var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "GX450"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	//disCode = 
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	node = cityNode;
        	//disCode = 
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        }
        
	  	if(node!=null){
			if(node.id != "GX450"){
				disCode = node.id;
				
				//如果为市
				var pattern2 = new RegExp("[A-Za-z]+");
	    		if(pattern2.test(node.id)){
	    			frozenColumnsTab.push(
	    					[
	    					{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
	    					{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    	    			{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
	    	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
								formatter: function(value,row,index){
	       	    		         if (value == "1"){
	       	    		             return "<p style='color:blue;'>待审核</p>";
	       	    		          } else if (value == "0"){
	       	    		             return "<p style='color:green;'>待提交</p>";
	       	    		          }else if(value == "3"){
	       	    		        	return "<p style='color:red;'>被退回的</p>";
	       	    		          }else{
	       	    		             return "<p style='color:red;'>无状态</p>";
	       	    		          }  			
	       	    		      }}, 
	       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
	    				]);
	    		}
	    		
	    		//验证是否是数字
	    	   	//是说明是县
	    		var pattern3 = new RegExp("[0-9]+");
	    		if(pattern3.test(node.id)){
	    			frozenColumnsTab.push([
	    					{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
	    	    			{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
								formatter: function(value,row,index){
	       	    		         if (value == "1"){
	       	    		             return "<p style='color:blue;'>待审核</p>";
	       	    		          } else if (value == "0"){
	       	    		             return "<p style='color:green;'>待提交</p>";
	       	    		          }else if(value == "3"){
	       	    		        	return "<p style='color:red;'>被退回的</p>";
	       	    		          }else{
	       	    		             return "<p style='color:red;'>无状态</p>";
	       	    		          }  			
	       	    		      }}, 
	       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
	    				]);
	    			$.ajax({
	    				async:false,
	    				url:'${pageContext.request.contextPath}/takWorking/Ddis',
	    				dataType:'json',
	    				data:{"dcode":node.id},
	    				success:function(data){
	    					disCode = data.anumber;
	    				},
	    				
	    			});
	    		}
	    		
			}else{
				frozenColumnsTab.push(
						[
							/* {field:'',title:'',checkbox="true",align:'center'}, */
							{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
							{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
		    			/* {field:'county',title:'县',width:100,rowspan:'2',align:'center'}, */
					]);
			}
		}else{
			frozenColumnsTab.push(
					[
						/* {field:'',title:'',checkbox="true",align:'center'}, */
						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    			/* {field:'county',title:'县',width:100,rowspan:'2',align:'center'}, */
				]);
		}
		
		$.ajax({
			type:'post',
			traditional: true,
			async:false,
			url:'${pageContext.request.contextPath}/taskWorking/taskTab',
			data:{"year":year,"zllb":taskMark,"disCode":disCode},
			dataType:'json',
			success:function(data){
				
		    	for(var i=0;i<data.length;i++){
					columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
					
					var epc = data[i].list;
					
					if(data[i].list.length > 0){
						for(var j=0;j<epc.length;j++){
							var ename = epc[j].ename;
							var field = epc[j].field;
							columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
							columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:100,align:'center'},
												 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:100,align:'center'},
												 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:100,align:'center'});
						}
					}
				}
		    	columnsTabHead.push(columnsOneTab);
				columnsTabHead.push(columnsTowTab);
				columnsTabHead.push(columnsThreeTab);
		    	
				inTable();
			},
		})
		
  	}
  
    function derive(){
    	
    	var node =$("#tt").tree('getSelected');//获取选择的节点
    	//获取时间
    	var year = $("#year").val();
    	var id = null;
    	if(node != null){
    		id = node.id;
    	}
    	window.location.href="${pageContext.request.contextPath}/completionTask/derive?nid="+id+'&year='+year;
    }
	
    //页面加载
    $(function(){
    	$.ajaxSettings.async = false;
    	//初始化树
        $("#tt").tree({
       		url:'${pageContext.request.contextPath}/district/queryTreeNode',
       		animate:true,
       		onClick:click,
       		onLoadSuccess:success
       	});
    	init();//获取数据表格表头数据
    	inTable();//初始化数据表格
    });
  	function success(node,data){
  		//获取所有的市
  		var children = data[0].children;
  		//console.info(children);
  		
  		//多少个市
  		 muniLength = children.length;
  		//console.info(muniLength);
  		
  		//获取第一个市下的县
  		var second = children[0].children;
  		//console.info(second);
  		
  		//多少个县
  		 coutyLength = second.length;
  		//console.info(coutyLength);
  		var a = second.target;
  		//条件满足说明为县
  		if(muniLength == 1 && coutyLength == 1){
  			//disCode = second[0].id;
  			
  			var tar = $("#tt").tree("find",second[0].id);
  			countNode = tar;
  			$.ajax({
				async:false,
				url:'${pageContext.request.contextPath}/takWorking/Ddis',
				dataType:'json',
				data:{"dcode":tar.id},
				success:function(dataCounty){
					if(dataCounty != null){
						disCode = dataCounty.anumber;
						console.info(disCode+"==========");
					}
					
				},
				
			});
  		}
  		if(muniLength == 1 && coutyLength > 1){
  			var tar = $("#tt").tree("find",children[0].id);
  			cityNode = tar;
  			$.ajax({
				async:false,
				url:'${pageContext.request.contextPath}/takWorking/CityFlag',
				dataType:'json',
				data:{"flag":tar.id},
				success:function(dataCity){
					if(dataCity != null){
						disCode = dataCity.citycode;
					}
				},
				
			});
  		}
  	}
    //页面加载时初始化
    function init(){
    	var year = $("#year").val();
    	frozenColumnsTab = new Array();
    	columnsTabHead = new Array();
    	
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	var disCode = "45";
    	
    	var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "45"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = node.id;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	var replaceCityNode = Object.assign({},cityNode);
        	node = replaceCityNode;
        	disCode = node.id;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        }
    	
    	var node =$("#tt").tree('getSelected');//获取选择的节点
    	var zllb = $("#task").combobox('getValue');//获取选择的工程编号
        var _iframe = window.parent;//获取父页面对象 iframe
        var statu = _iframe.$("#iframe").attr("value");//获取要查询的状态
    	if(node != null && node.id != "45"){
    			disCode = node.id;
    	}
		//如果为市
  		if(disCode.length == 4){
  			frozenColumnsTab.push([
 						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
 						{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
  	    				{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
  	    				{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
  	    				formatter: function(value,row,index){	
       	    		         if (value == "1"){
        	    		             return "<p style='color:blue;'>待审核</p>";
        	    		          } else if (value == "0"){
        	    		             return "<p style='color:green;'>待提交</p>";
        	    		          }else if (value == "3"){
        	    		             return "<p style='color:red;'>被退回</p>";
        	    		          }else{
        	    		             return "<p style='color:gray;'>无状态</p>";
        	    		          }  			
        	    		      }},
        	    		    {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
  			]);
   		
		}
    	if(disCode.length == 2){
    		frozenColumnsTab.push([
				{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
				{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
			]);
    	}
			
		//县
		if(disCode.length == 6){
			frozenColumnsTab.push([
				{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
				{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
				{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
				{field:'stat',title:'状态',width:100,rowspan:'2',align:'center',
	    				formatter: function(value,row,index){	
   	    		         if (value == "1"){
    	    		             return "<p style='color:blue;'>待审核</p>";
    	    		          } else if (value == "0"){
    	    		             return "<p style='color:green;'>待提交</p>";
    	    		          }else if (value == "3"){
    	    		             return "<p style='color:red;'>被退回</p>";
    	    		          }else{
    	    		             return "<p style='color:gray;'>无状态</p>";
    	    		          }  			
    	    		      }},
		]);
		}
		//获取数据库中的数据表头
		$.ajax({
			async:false,
			url:'${pageContext.request.contextPath }/backlogXb/getTableHeader',
			dataType:'json',
			data:{"year":year,"disCode":disCode,"zllb":zllb,"statu":statu},
			success:function(data){
				
				for(var i=0;i<data.length;i++){
					columnsOneTab.push({field:'',title:data[i].tname,width:100*data[i].list.length,colspan:data[i].list.length*3,align:'center'});
					
					var epc = data[i].list;
					
					if(data[i].list.length > 0){
						for(var j=0;j<epc.length;j++){
							var ename = epc[j].ename;
							var field = epc[j].field;
							columnsTowTab.push({title:''+ename+'',width:100*3,align:'center',colspan:3});//field:''+field+'',
							columnsThreeTab.push({field:'jh'+data[i].mark+"Y"+epc[j].mark,title:'计划',width:100,align:'center'},
												 {field:'wc'+data[i].mark+"Y"+epc[j].mark,title:'完成',width:100,align:'center'},
												 {field:'zjh'+data[i].mark+"Y"+epc[j].mark,title:'占计划%',width:100,align:'center'});
						}
					}
				}
				
			},
		});
		
		columnsTabHead.push(columnsOneTab);
		columnsTabHead.push(columnsTowTab);
		columnsTabHead.push(columnsThreeTab);
	}
    
    //初始化表格
    function inTable(){

    var disCode = "45";
    var node =$("#tt").tree('getSelected');//获取选择的节点
    var oldNode = null;
	var id = null;
	if(node != null && node.id != "45"){
			oldNode = node;
			id = oldNode.id;
	}
	
	//判断是否只有一个市和一个县/为县管理员
    if(muniLength == 1 && coutyLength == 1){
    	node = countNode;
    	disCode = node.id;
    }
	
	//muniLength=1和coutyLength大于1说明是市管理员
    if(muniLength == 1 && coutyLength > 1){
    	var replaceCityNode = Object.assign({},cityNode);
    	node = replaceCityNode;
    	disCode = node.id;
    }
    var pattern3 = new RegExp("[0-9]+");
    if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
    	node = oldNode;
    }
	
	//获取时间
    	var year = $("#year").val();
        var _iframe = window.parent;
        var statu = _iframe.$("#iframe").attr("value");//获取要查询的状态
        console.log("statu="+statu);
        var statu1 = $(".Current_page").val();
        var statu2 = $(".Current_page").attr("value");
        if(node != null && node.id != "45"){
			disCode = node.id;
	}
        //var url = HttpUtility.UrlEncode('${pageContext.request.contextPath}/takWorking/epcTaskData?year='+year+'&disCode='+disCode+'&usr='+usr+'&zllb='+taskMark);
    	var url = encodeURI('${pageContext.request.contextPath}/backlogXb/queryTaskData?year='+year+'&disCode='+disCode+'&usr='+usr+'&zllb='+taskMark+'&statu='+statu);
    	$("#tab").datagrid({
    		fit:true,
			url:url,
    		frozenColumns:frozenColumnsTab,
    		columns:columnsTabHead,
    		rownumbers:true,
    		border : false,
    		striped : true,
    		pagination:true,
    		fitColumns:false,
    		toolbar:'#tbs',
    		onClickCell: function (rowIndex, field, value){
    			if("particulars" == field){
    				var nodeLog =$("#tt").tree('getSelected');//获取选择的节点
    				//var logCode = nodeLog.id;
    				var time = year;//时间
    				var coy ;//县
    				
    				var data = new Array();
    				
    				var rows = $("#tab").datagrid("getRows");//获取表格索引数据
    				var row = rows[rowIndex]; //根据索引获取指定行的值对象
    				var r = JSON.stringify(row);//转成json串
    				var JsonObj = eval('('+r+')');//将json串转成json对象
    				delete JsonObj.particulars;
    				//遍历json对象
    				for(var key in JsonObj){
    					var T = key.indexOf("T");//判断是否包含这个字母（是代表是工程和造林类别）
    					if(T != -1){
    						data.push(key);
    					}
    					var county = key.search("county");//是否为县
    					if(county != -1){
    						coy = JsonObj[key];
    					}
    				}
    				var js = data.join();
            		$("#opt").window("open");
            		var datefilename;//用来存储文件的时间+名称
            		var logurl = encodeURI('${pageContext.request.contextPath}/takWorking/findLog?row='+data.join()+'&time='+time+'&county='+coy+'&zllb='+taskMark);
            		/* 操作详情的表格 */
            	    $("#optab").datagrid({
            	    	fit:true,
            	    	url:logurl,
            	    	columns:[[
            	    		{field:'stat',title:'工作步骤',width:100,align:'center',
            	    			formatter: function(value){
            	    				if(value=="1" || value == '0'){
            	    					return "任务工作中";
            	    				}else if(value == '2'){
            	    					return "造林任务完成验收";
            	    				}
            	    			}},
            	    		{field:'name',title:'用户名',width:100,align:'center'},
            	    		{field:'operate',title:'执行的操作',width:80,align:'center'},
            	    		{field:'operateTime',title:'操作的时间',width:160,align:'center'},
            	    		{field:'dcode',title:'县名称',width:100,align:'center'},
            	    		{field:'gclb',title:'工程类别',align:'center',//width:140,
            	    			},
            	    		{field:'year',title:'工程的时间',width:100,align:'center'},
            	    		{field:'filename',title:'文件',width:118,align:'center'},
            	    		{field:'lea',title:'留言',width:200,align:'center'},
            	    		{field:'hi',title:'hi',width:100,align:'center',hidden:'true'},
            	    	]],
            	    	border : false,
            			striped : true,
            	    	//显示分页栏
            	    	pagination:true,
            	    	rownumbers:true,
            	    	onLoadSuccess:function(data){
            	    		console.info(data);
            	    	},
            	    	onClickCell: function (rowIndex, field, value){
            	    		if("filename" == field){
            	    			var time = year;//时间
                				var coy ;//县
                				
                				var data = new Array();
                				
                				var rows = $("#optab").datagrid("getRows");//获取表格索引数据
                				var row = rows[rowIndex]; //根据索引获取指定行的值对象
                				var r = JSON.stringify(row);//转成json串
                				var JsonObj = eval('('+r+')');//将json串转成json对象
                				var file = JsonObj.hi;
                				//遍历json对象
                				var dcurl = encodeURI("${pageContext.request.contextPath}/takWorking/deriveFile?file="+file);
            	    			window.location.href=dcurl;
            	    		}
            	    	}
            	    });
    			}
    			
        	}
    	});
    	
    }
    
    $("#year").numberspinner({
    	"onChange":function(){
    		
    		var node =$("#tt").tree('getSelected');
    		
    		if(node==null){
    			init();//获取数据表格表头数据
    			inTable();//初始化数据表格
    		}else{
    			init();//获取数据表格表头数据
   				inTable();//初始化数据表格
    		}
    	}
    });
    

    
   	function click(node){
   		$("#task").combobox('setValue','');//工程编号
   		taskMark = '';
   		init();//加载表头
		inTable();//初始化表格
   	}
    	
    $(function(){
    	
    	/* 文件上传 */
    	$('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
            	console.info(data);
            	var st = data.result.data;
            	
            	var upFilename = data.result.msg;
            	var upFilenames = upFilename.split("/");
            	
            	var fileName=null;
            	if(upFilenames.length > 1){
            		fileName = upFilenames[2];
            	}
            	
            	//$("#upt").window("close");
            	if(data.result.data == 200){
            		$("#spaninfo").css("color","green");
            		$("#spaninfo").html("上传成功");
            		$("#tbody").append("<tr> <td align='center' style='border-color:gray'>"+fileName+"</td> <td align='center' style='border-color:gray'><button class='bcbtn' id='"+data.result.msg+"' onclick='delFileName(this)'>删除</button></td> <td style='display: none'></td> </tr>");
            	}
            	msg = data.result.msg;
            	bdgwj.push(msg);
            }
        });
    	//获取当前浏览器的类型
    	var userAgent = navigator.userAgent;
    	
    	if(userAgent.indexOf("Firefox") > -1){//判断是否是火狐
    		//$("#tet").css("width","335px");
    	$("#upt").css("height","400px");
    	$("#tet").css("width","650px");
    	}
    	
    	if(userAgent.indexOf("Trident") > -1){
    		$("#upt").css("height","400px");
        	$("#tet").css("width","650px");
    	}
    	if(userAgent.indexOf("QQBrowser")){//QQBrowser
    		console.info(userAgent.indexOf("QQBrowser"));
    	}
    })
    
    //保存
   	function save(){
    	$("#upt").window("close");
   		//获取文本框内容
   		lea = $("#tet").val();
   		
   		if(stat == 1){////状态  为1的时候执行退回   为2的时候执行提交    为3的时候执行审核
       		msgth = msg;
       		th1=true;
           	th2=false;
           	saveBack();
       	}
		if(stat == 2){
			msgtj = msg;
			tj1=true;
           	tj2=false;
           	saveSubmit();   		
		   }
		if(stat == 3){
			shmsg = msg;
			sh1=true;
           	sh2=false;
           	saveAudit();
		}
		$("#spaninfo").html("");
    	$("#tet").val(null);
   	}
    
    //删除文件
    function delFile(){
    	$.ajax({
				async:false,
				url:'${pageContext.request.contextPath}/takWorking/delFile',
				dataType:'json',
				data:{"msg":msg},
				success:function(data){
					var d = data.data;
				/* 	if(d == 200){
						$("#tab").datagrid('load');
					}
					if(d == 400){
						$("#tab").datagrid('load');
					} */
				},
				
			});
    }
    function cancel(){
    	$("#upt").window("close");
    	$("#spaninfo").html("");
    	$("#tet").val(null);
    	$("#tbody").html("");
    	delFile();
    }
    //删除指定行
    function delFileName(obj){
    	msg = obj.id;
    	//删除数组中的文件
    	for(var i=0;i<=bdgwj.length;i++){
    		if(msg == bdgwj[i]){
    			var f = bdgwj.splice(i);
    			console.info(f);
    		}
    	}
    	
    	delFile();
    	
    	$(obj).parents("tr").remove();
    }
    </script>
   
    <!-- 详情弹出窗口 -->
    <div id="opt" class="easyui-window" title="操作详情" style="width:1052px;height:400px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
		
		<table id="optab"></table>

	</div>

    <!-- 上传窗口 -->
    <div id="upt" class="easyui-window" title="上传窗口" style="width:850px;height:450px" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
		
		<p style="margin-top: 20px;margin-left: 45px;position: absolute;">情况说明:</p>
		<textarea rows="7" cols="100" id="tet" style="margin-top: 20px;margin-left: 100px"></textarea><br><br>
		<!-- <a href="javascript:;" class="file">选择文件 -->
		<a href="javascript:;" class="a-upload" style="margin-left: 100px;margin-top: 10px"><!-- //style="margin-left: 100px; -->
		<input id="fileupload" type="file" name="fileName" data-url="${pageContext.request.contextPath}/taskWorking/upFile" >点击这里上传文件
		</a><span id="spaninfo"></span>
		
		<br>
		
		<!-- <table style="margin-left: 100px;" border="1"  bordercolor=	#FFFFFF  cellspacing="0" cellpadding="0">
			<thead >
				<tr>
					<th width="351px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray;"><font color="gray">文件名</font></th>
					<th width="300px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray"><font color="gray">操作</font></th>
					<th width="100px" align="center" style="display: none;border-color:gray">id</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table> -->
		
		<table style="margin-left: 100px;" border="1"  bordercolor=	#FFFFFF  cellspacing="0" cellpadding="0">
			<thead >
				<tr>
					<th width="351px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray;"><font color="gray">文件名</font></th>
					<th width="300px" align="center" cellspacing="0" cellpadding="0" style="border-color:gray"><font color="gray">操作</font></th>
					<th width="100px" align="center" style="display: none;border-color:gray">id</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
		
		<br><br>
		
<!-- 		<input style="margin-left: 340px; margin-top: 50px; " type="button" value="确定" onclick="save()" class="bcbtn"> 
		<input style="margin-left: 70px" type="button" value="取消" onclick="cancel()" class="qxbtn"> -->
		<input style="margin-left: 340px; margin-top: 50px; " type="button" value="确定" onclick="save()" class="bcbtn"> 
		<input style="margin-left: 70px" type="button" value="取消" onclick="cancel()" class="qxbtn">
	</div>
</body>
</html>
