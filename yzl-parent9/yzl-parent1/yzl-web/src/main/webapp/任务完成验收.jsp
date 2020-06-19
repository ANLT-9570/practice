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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css" />

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
/* #labelName{
position: absolute;
margin-left:415px;
margin-top:11px;
z-index: 1000
}
.combo{
position: absolute;
margin-left:475px;
margin-top:8px;
z-index: 1000
} */
</style>
</head>

<body class="easyui-layout" >
	<!-- <div class="easyui-layout" style=" width: 1280px;height: 550px;margin-top: -5px"> -->
		    <div data-options="region:'west',title:'',split:true,border : false" style="width:143px;margin-left: 5px;">
			    <ul id="tree" class="easyui-tree">
			    
			    </ul>
		    </div>

		    
		    <%-- <label class="" id="labelName" style="font-size: 12px;">造林类别:</label>
			<input id="task" class="easyui-combobox" name="dept" data-options="valueField:'mark',textField:'tname',url:'${pageContext.request.contextPath }/taskWorking/show_tasks'" />&nbsp;&nbsp;
			 --%>
			<div id="ce" data-options="region:'center',title:'',border : false" style="width:1400px;padding:5px;background:#eee;">
				<table id="tab" style="display:none;height:530px"></table>
			</div>
    <div id="tbs" style="height:28px;dispaly:none">

    	<label class="" id="labelName" style="font-size: 12px;">造林类别:</label>
		<input id="zllb" class="easyui-combobox" name="dept" data-options="valueField:'mark',textField:'tname',url:'${pageContext.request.contextPath }/taskWorking/show_tasks'" />&nbsp;&nbsp;
			
       	<!-- <input id=searchKey value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: 20px"> -->
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="derive()">导出</a>
       	
       	<shiro:hasPermission name="sys:rwwc:th">
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="back()">退回</a>
       	</shiro:hasPermission>
       	&nbsp;&nbsp;
       	<label class="" id="labelName" style="font-size: 12px;">年份:</label>
       	<input id=year value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="width:70px;;height:19px;margin-top: 0px">
       	<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="submit()">提交</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="back()">退回</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="audit()">审核</a> -->
   </div>
    
    <script type="text/javascript">
  //当前登录的用户
    var usr = "${sessionScope.user.username}";
    var msg;
    var lea;
    var sh1 = false;//sh1
    var sh2 = true;//sh2
    var disCode;
    var zllb = null;
    var muniLength = null;//多少个市
    var coutyLength = null;//多少个县
    var countNode = null;//县的节点
    var cityNode = null;//市的节点
    var bdgwj = new Array;
    var clickZllb = null;
    var backCountys = new Array;
    //退回
    function back(){
    	var node =$("#tree").tree('getSelected');//获取选择的节点
    	if(node!=null){
    		disCode = node.id;
    	}else{
    		node = "GX45";
    	}
    	var time = $("#year").val();//获取时间
    	var rows = $("#tab").datagrid('getSelections');//获取选择的行
    	
    	if(rows.length == 0){
       		$.messager.alert('错误提示','必须选择一个','info');
       		return;
       	}
    	for(var i=0;i<rows.length;i++){
   			var r = JSON.stringify(rows[i]);//转成json串
       		//将json字符串转成jsn对象
       		var JsonObj = eval('('+r+')');
       		var states = JsonObj.stat;
       		var county = JsonObj.county;
       		/* if(states == '3' || states == '0'){
       			$.messager.alert('错误提示','未提交不能退回！！','info');
           		return;
       		} */
           	if(county == undefined){
           		$.messager.alert('错误提示','<h3>不能退回市！！</h3>','info');
           		return ;
           	}else{
           		backCountys.push(county);
           	}
   		}
    	
    	var backData = new Array(); //退回单条数据	
    	var backDatas = new Array();	//退回多条数据
    	if(rows.length == 1){
    		backDatas.push(JSON.stringify(rows[0]));
    	}else{
    		for(var i=0;i<rows.length;i++){
        		var ro = JSON.stringify(rows[i]);
        		backData.push(ro);
        	}
    	}
    	
    	if(sh2){
    		$("#upt").window("open");
    	}
    	if(sh1){
		   			var backData = new Array();
		   	    	var backDatas = new Array();
		   	    	
		   	    	if(rows.length == 1){
		   	    		backDatas.push(JSON.stringify(rows[0]));
		   	    	}else{
		   	    		for(var i=0;i<rows.length;i++){
		   	        		var ro = JSON.stringify(rows[i]);
		   	        		backData.push(ro);
		   	        	}
		   	    	}
		   	    	
		   	    	$.ajaxSettings.async = false;//取消异步
		   	    	$.ajax({
		   	    		type:'post',
		   	    		traditional: true,
		   	    		async:false,
		   	    		url:'${pageContext.request.contextPath}/completionTask/backData',
		   	    		data:{'backData':backData,"disCode":disCode,"usr":usr,'backDatas':backDatas,"time":time,"lea":lea,"fileNames":bdgwj,"zllb":zllb,"countys":backCountys},
		   				dataType:'json',
		   	    		success:function(data){
		   					if(data.data==200){
		   						$.messager.alert('提示框','成功','info');
		   						//$("#tab").datagrid('load');
		   						init();
		   				    	inTable();
		   						$("#tbody").html("");
		   						bdgwj = [];
		   					}else{
		   						$.messager.alert('提示框','失败','info');
		   					}
		   				},
		   	    	});
		   		sh1=false;
            	sh2=true;
    	}
    }
    
    //选择 查看工程触发
/*     $("#zllb").combobox({
    	onSelect:changed
    }); */
  	//存储添加固定表头的数据
	var frozenColumnsTab = new Array();
	var columnsTabHead = new Array();
  	function changed(){
  		frozenColumnsTab = new Array();
    	columnsTabHead = new Array();
    	
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	
	  	//获取时间
	    var year = $("#year").val();
	    var node =$("#tt").tree('getSelected');//点击的行政编号
	    zllb = $("#zllb").combobox('getValue');//工程编号
		var text = $("#task").combobox('getText');//工程名称
		
		var oldNode = null;
    	var id = null;
    	if(node != null && node.id != "45"){
    			oldNode = node;
    			id = oldNode.id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	node = cityNode;
        }
        var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength > 1 && pattern3.test(id)){
        	node = oldNode;
        }
        
	  	if(node!=null){
			if(node.id != "45"){
				disCode = node.id;
				
				//如果为市
				var pattern2 = new RegExp("[A-Za-z]+");
	    		if(pattern2.test(node.id)){
	    			frozenColumnsTab.push(
	    					[
	    						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
	    					{field:'city',title:'市',width:100,rowspan:'2',align:'center'}, 
	    	    			{field:'county',title:'县',width:100,rowspan:'2',align:'center'},
	       	    		   {field:'particulars',title:'操作详情',width:60,rowspan:'2',align:'center'},
	    				]);
	    		}
	    		
	    		//验证是否是数字
	    	   	//是说明是县
	    		var pattern3 = new RegExp("[0-9]+");
	    		if(pattern3.test(node.id)){
	    			frozenColumnsTab.push(
	    					[
	    						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
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
			url:'${pageContext.request.contextPath}/Completion/taskTab',
			data:{"year":year,"zllb":zllb,"disCode":disCode},
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
		});
		
  	}
    
	//数据导出
    function derive(){
		var disCode = "45";
    	var node =$("#tree").tree('getSelected');//获取选择的节点
    	//获取时间
    	var year = $("#year").val();
		var oldNode = null;
    	var id ="1";
    	if(node != null && node.id != "45"){
    			oldNode = node;
    			id = oldNode.id;
    			disCode = id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = countNode.id;
        	id = disCode;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	//node = cityNode;
        	disCode = cityNode.id;
        	id = disCode;
        }
        //var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength  > 1 && id.length >= 6){
        	disCode = node.id;
        	id = disCode;
        }
    	window.location.href="${pageContext.request.contextPath}/completionTask/derive?nid="+id+'&year='+year+"&disCode="+disCode+"&zllb="+zllb;
    }

	
    //页面加载
    $(function(){
    	$.ajaxSettings.async=false;//取消异步
    	//初始化树
    	//初始化树
    $("#tree").tree({
   		url:'${pageContext.request.contextPath}/district/queryTreeNode',
   		animate:true,
   		onClick:click,
   		onLoadSuccess:success
   	});
        /* $("#tt").tree({
       		url:'${pageContext.request.contextPath}/show_dis',
       		animate:true,
       		onClick:click,
       		onLoadSuccess:success
       	}); */
    	init();
    	inTable();
    	//文件上传
    	$('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
            	
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
            		$("#tbody").append("<tr> <td style='border-color:gray' align='center'>"+fileName+"</td> <td style='border-color:gray' align='center'><button class='bcbtn' id='"+data.result.msg+"' onclick='delFileName(this)'>删除</button></td> <td style='display: none'></td> </tr>");
            	}
            
            	msg = data.result.msg;
            	bdgwj.push(msg);
            	
               /*  $.each(data.result.files, function (index, file) { */
                    /* $('<p/>').text(file.name).appendTo(document.body); */
                 /*    console.info(index);
                    console.info(file);
                }); */
            }
        });
    })
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
  			
  			var tar = $("#tree").tree("find",second[0].id);
  			countNode = tar;
  			//var bo = $("#tt").tree("select",tar);
  			//$("#tt").tree("setValue",disCode);
  			//click(second);
  		}
  		if(muniLength == 1 && coutyLength > 1){
  			var tar = $("#tree").tree("find",children[0].id);
  			cityNode = tar;
  		}
  	}
	

    //页面初始化时 加载表头数据
    function init(){
    	var year = $("#year").val();
    	frozenColumnsTab = new Array();
    	columnsTabHead = new Array();
    	
    	var columnsOneTab = new Array();
    	var columnsTowTab = new Array();
    	var columnsThreeTab = new Array();
    	
    	var node =$("#tree").tree('getSelected');//获取选择的节点
    	 zllb = $("#zllb").combobox('getValue');//造林类别
    	var disCode = "GX45";//地区编号
    	
   		var oldNode = null;
  		var id = "1";
  		if(node != null && node.id != "45"){
  			oldNode = node;
  			id = oldNode.id;
  			disCode = id;
  		}
  		//判断是否只有一个市和一个县/为县管理员
      	if(muniLength == 1 && coutyLength == 1){
      		node = countNode;
      	}
  	
	  	//muniLength=1和coutyLength大于1说明是市管理员
	     if(muniLength == 1 && coutyLength > 1){
	     	node = cityNode;
	     }
	    // var pattern3 = new RegExp("[0-9]+");
	     if(muniLength == 1 && coutyLength > 1 && id.length >= 6){// > 1 && pattern3.test(id)
	     	node = oldNode;
	     }
    	
   		if(node!=null && node.id != "45"){//判断是否是自治区  GX450==自治区
   			disCode = node.id;
   			//如果为市
      			frozenColumnsTab.push([
     					{align:'center',width:100,title:'序号',  rowspan:'3',frozen:true,field:'id',checkbox:true},
      					{field:'city',title:'市',width:100,rowspan:'3',align:'center'}, 
      	    			{field:'county',title:'县',width:100,rowspan:'3',align:'center'},
      	    			{field:'particulars',title:'操作详情',width:60,rowspan:'3',align:'center'},
      			]);
   		}else{//自治区进行初始化
   			
   			frozenColumnsTab.push([{align:'center',width:100,title:'序号',  rowspan:'3',frozen:true,	field:'id',checkbox:true},{field:'city',title:'市',width:100,rowspan:'3',align:'center'}]);
   		
   		}
    	
		
		//获取数据库中的数据表头
		$.ajax({
			async:false,
			url:'${pageContext.request.contextPath }/completionTask/taskTab',
			dataType:'json',
			data:{"year":year,"disCode":disCode,"usr":usr,"zllb":zllb},
			success:function(data){
				for(var i=0;i<data.length;i++){				
					if(node == null || node.id == "45"){
						columnsOneTab.push({field:data[i].mark+"zl",title:data[i].tname,width:100,rowspan:3,align:'center'});
					}
				}
				for(var i=0;i<data.length;i++){
	    			//frozenColumnsTab.push({field:'county',title:'县',width:100,rowspan:'2',align:'center'});
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
	//选择 查看工程触发
    $("#zllb").combobox({
    	onSelect:changed
    });
	
		function changed(){
			init();//获取数据表格表头数据
			inTable();//初始化数据表格
	    }
    
    //初始化表格
    //初始化表格数据
    function inTable(){
    	
	var node =$("#tree").tree('getSelected');//获取选择的节点
    	
    	var disCode = "GX45";
    	/* var zllb = $("#zllb").combobox('getValue');//造林类别
		if(node != null && node.id != null){
			disCode = node.id;//地区行政编号
		} */
		var oldNode = null;
    	var id = "1";
    	if(node != null && node.id != "45"){
    			oldNode = node;
    			id = oldNode.id;
    			disCode = id;
    	}
    	//判断是否只有一个市和一个县/为县管理员
        if(muniLength == 1 && coutyLength == 1){
        	node = countNode;
        	disCode = countNode.id;
        }
    	
    	//muniLength=1和coutyLength大于1说明是市管理员
        if(muniLength == 1 && coutyLength > 1){
        	//node = cityNode;
        	disCode = cityNode.id;
        }
        //var pattern3 = new RegExp("[0-9]+");
        if(muniLength == 1 && coutyLength  > 1 && id.length >= 6){
        	disCode = node.id;
        }
    	//获取时间
    	var year = $("#year").val();
    	var url = encodeURI('${pageContext.request.contextPath}/completionTask/epcTaskData?year='+year+'&disCode='+disCode+'&usr='+usr+'&zllb='+zllb);
    	$("#tab").datagrid({
    		fit:true,
			url:url,
    		frozenColumns:frozenColumnsTab,
    		columns:columnsTabHead,
    		checkbox:true,
    		rownumbers:true,
    		border: false,
			striped: true,
    		pagination:true,
    		fitColumns:false,
    		toolbar:'#tbs',
    		onClickCell: function (rowIndex, field, value){
    			if("particulars" == field){
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
    					if(key.indexOf("T") != -1){//判断是否包含Y字母（包含代表是工程和造林类别）
    						data.push(key);
    					}
    					if(key.indexOf("county") != -1){//判断是否为县key
    						coy = JsonObj[key];//获取县的值
    					}
    				}
            		$("#opt").window("open");
            		var datefilename;//用来存储文件的时间+名称
            		var logurl = encodeURI('${pageContext.request.contextPath}/takWorking/findLog?row='+data.join()+'&time='+time+'&county='+coy+'&zllb='+zllb);
            		//$.ajaxSettings.async = false;
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
            	    		{field:'gclb',title:'工程类别',align:'center'},
            	    		{field:'year',title:'工程的时间',width:100,align:'center'},
            	    		{field:'filename',title:'文件',align:'center'},
            	    		{field:'lea',title:'留言',width:200,align:'center'},
            	    		{field:'hi',title:'hi',width:100,align:'center',hidden:'true'},
            	    	]],
            	    	border : false,
            			striped : true,
            	    	//显示分页栏
            	    	pagination:true,
            	    	rownumbers:true,
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
    }
    
    
    $("#year").numberspinner({
    	"onChange":function(){
    		var node =$("#tree").tree('getSelected');
   			init();//获取数据表格表头数据
   			inTable();//初始化数据表格
    	}
    });
    
    	function click(node){
    		$("#zllb").combobox('clear');//清楚工程列表
    		init();//加载表头
			inTable();//初始化表格
    	}
    	//保存
       	function save(){
       		//获取文本框内容
       		lea = $("#tet").val();
       		$("#upt").window("close");
       		sh1=true;
        	sh2=false;
       		back();
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
    <div id="opt" class="easyui-window" title="操作详情" style="width:1052px;height:400px;" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true"> 
		
		<table id="optab"  ></table>

	</div>

    <!-- 上传窗口 -->
    <div id="upt" class="easyui-window" title="上传窗口" style="width:850px;height:450px;" data-options="maximizable:false,minimizable:false,iconCls:'icon-save',modal:true,closed:true,closable:false,resizable:false"> 
		<p style="margin-top: 20px;margin-left: 45px;position: absolute;">情况说明:</p>
		<textarea rows="7" cols="100" id="tet" style="margin-top: 20px;margin-left: 100px" ></textarea><br><br>
		<!-- <a href="javascript:;" class="file">选择文件 -->
		<a href="javascript:;" class="a-upload" style="margin-left: 100px;margin-top: 10px"><!-- //style="margin-left: 100px; -->
		<input id="fileupload" type="file" name="fileName" data-url="${pageContext.request.contextPath}/taskWorking/upFile" >点击这里上传文件
		</a><span id="spaninfo"></span>
		
		<br>
		
		<table style="margin-left: 100px;" border="1" bordercolor=	#FFFFFF cellspacing="0" cellpadding="0">
			<thead >
				<tr>
					<th width="351px" align="center" style='border-color:gray'><font color="gray">文件名</font></th>
					<th width="300px" align="center" style='border-color:gray'><font color="gray">操作</font></th>
					<th width="100px" align="center" style="display: none">id</th>
				</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
		
		<input style="margin-top: 50px;margin-left: 340px;" type="button" value="确定" onclick="save()" class="bcbtn">
		<input style="margin-left: 70px" type="button" value="取消" onclick="cancel()" class="qxbtn">
	</div>
</body>
</html>
