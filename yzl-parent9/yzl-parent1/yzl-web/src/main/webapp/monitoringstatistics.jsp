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
</style>
</head>
<body id="bod" class="easyui-layout" >

	    <div data-options="region:'west',title:'',split:true,border : false" style="width:142px;margin-left: 5px;">
	    
		    <ul id="tt" class="easyui-tree">
		    
		    </ul>
	    </div>
		<div id="ce" data-options="region:'center',title:'',border : false" style="width:1300px,padding:5px;background:#eee;">
			
		</div>
</body>
<script type="text/javascript">
var sname = null;
var puptime = null;
var stime = null;
var flag = null;
var qccty = null;

var list = new Array();
//当前登录的用户
var usr = "${sessionScope.user.username}";
    var muniLength = null;//多少个市
    var coutyLength = null;//多少个县
    var countNode = null;//县的节点
    var cityNode = null;//市的节点
    $(function(){
    	$.ajaxSettings.async=false;
    	//展示树
        $("#tt").tree({
       		url:'${pageContext.request.contextPath}/show_dis',
       		animate:true,
       		onClick:click,
       		onLoadSuccess:success
       	});
    	
    	click();
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
  		//var a = second.target;
  		//条件满足说明为县
  		if(muniLength == 1 && coutyLength == 1){
  			//disCode = second[0].id;
  			
  			var tar = $("#tt").tree("find",second[0].id);
  			countNode = tar;
  			//var bo = $("#tt").tree("select",tar);
  			//$("#tt").tree("setValue",disCode);
  			//click(second);
  		}
  		if(muniLength == 1 && coutyLength > 1){
  			var tar = $("#tt").tree("find",children[0].id);
  			cityNode = tar;
  		}
  	}
    
    
    var date = new Date();
    //得到现在的时间年份
    var tt = date.getFullYear();
    var tts;
    
    function doSearch(){
    	//获取搜索框输入的内容
    	 sname = $("#search").searchbox('getValue');
    	var times = $("#ss").numberspinner('getValue');//获取时间
    	stime = times;
    	$("#tbMunicipality").datagrid('load',{"sname":sname,"stime":times,"usr":usr});
    }
    function doSearchs(){
    	//获取搜索框输入的内容
    	 sname = $("#searchs").searchbox('getValue');
    	var times = $("#sss").numberspinner('getValue');//获取时间
    	stime = times;
    	$("#tbCity").datagrid('load',{"sname":sname,"stime":times,"usr":usr});
    }
    function doSearchx(){
    	//获取搜索框输入的内容
    	 sname = $("#searchx").searchbox('getValue');
    	var times = $("#ssx").numberspinner('getValue');//获取时间
    	stime = times;
    	$("#tbCounty").datagrid('load',{"sname":sname,"stime":times,"usr":usr});
    }
    
    
  //导出
  function deriveMunicipality(){
	  var node =$("#tt").tree('getSelected');//点击的行政编号
	  var discod;
	  var pattern2 = new RegExp("[A-Za-z]+");
	  var pattern3 = new RegExp("[0-9]+");
	  
	  if(node == null || node.id == "GX450"){
		  discod = 1;
	  }else if(pattern2.test(node.id)){
		  discod = 2;
	  }else if(pattern3.test(node.id)){
		  discod = 3;
	  }
	  
	  console.info(node);
	  window.location.href="${pageContext.request.contextPath}/deriveMunicipality?discod="+discod+"&sname="+sname+"&stime="+stime+"&puptime="+puptime+"&qccty="+qccty;
  }
    var mak;
    var zzq;
    var onAfterRender = $.fn.datagrid.defaults.view;
    $.extend($.fn.datagrid.defaults.view, {
    	onAfterRender :function(){
    		if(flag == 0){
    			tts = tt;
    		}
    		//自治区触发时间事件
    	    $("#ss").numberspinner({
    	    	value:tts,//初始化时间
    	    	onSpinUp:function(){
    	    		var cityCode = $("#cityCombobox").combobox("getValue");
    	    		var mu_zllb = $("#zllb").combobox("getValue");
    	    		var mu_gclb = $("#gclb").combobox("getValue");
    	    		flag=flag+1;
    	    		var times = $("#ss").numberspinner('getValue');//获取时间
    	    		tts = times;
    	    		stime = times;
            			$("#ss").numberspinner('setValue',times);
            		
    	    		$("#tbMunicipality").datagrid('load',{"stime":times,"cityCode":cityCode,"zllb":mu_zllb,"gclb":mu_gclb});
    	    		
    	    	},
    	    	onSpinDown:function(){
    	    		var cityCode = $("#cityCombobox").combobox("getValue");
    	    		var mu_zllb = $("#zllb").combobox("getValue");
    	    		var mu_gclb = $("#gclb").combobox("getValue");
    	    		flag=flag+1;
    	    		var times = $("#ss").numberspinner('getValue');//获取时间
    	    		tts = times;
    	    		stime = tts;
    	    		$("#tbMunicipality").datagrid('load',{"stime":times,"cityCode":cityCode,"zllb":mu_zllb,"gclb":mu_gclb});
    	    	},
    	    	
    	    });
    	  //自治区搜索框
    	    $("#search").searchbox({
    	    	//prompt:'请输入...',
    	    	searcher:doSearch
    	    })
    	    //自治区导出
    	    $('#btn').linkbutton({
    	    	iconCls: 'icon-undo',
    	    	plain:true,
    	    }); 

    	  
   	  //市区触发时间事件
   	    $("#sss").numberspinner({
   	    	value:tts,//初始化时间
	    	onSpinUp:function(){
	    		var cou = $("#countyCombobox").combobox("getValue");
	    		var cou_zllb = $("#zllbCounty").combobox("getValue");
	    		var cou_gclb = $("#gclbCounty").combobox("getValue");
	    		
	    		flag=flag+1;
	    		var times = $("#sss").numberspinner('getValue');//获取时间
	    		tts = times;
	    		stime = times;
        			$("#sss").numberspinner('setValue',times);
        		
	    		$("#tbCity").datagrid('load',{"stime":times,"cityCode":cou,"zllb":cou_zllb,"gclb":cou_gclb});
	    		
	    	},
	    	onSpinDown:function(){
	    		var cou = $("#countyCombobox").combobox("getValue");
	    		var cou_zllb = $("#zllbCounty").combobox("getValue");
	    		var cou_gclb = $("#gclbCounty").combobox("getValue");
	    		flag=flag+1;
	    		var times = $("#sss").numberspinner('getValue');//获取时间
	    		tts = times;
	    		stime = tts;
	    		$("#tbCity").datagrid('load',{"stime":times,"cityCode":cou,"zllb":cou_zllb,"gclb":cou_gclb});
	    	},
   	    });
   	  
   		//市区搜索框
	    $("#searchs").searchbox({
	    	//prompt:'请输入...',
	    	searcher:doSearchs
	    })
	    //市区导出
	    $('#btns').linkbutton({
	    	iconCls: 'icon-undo',
	    	plain:true,
	    }); 
   	  
   	
	 	 //县触发时间事件
   	    $("#ssx").numberspinner({
   	    	value:tts,//初始化时间
	    	onSpinUp:function(){
	    		var gclbMin = $("#gclbMin").combobox("getValue");
	    		var zllbMin = $("#zllbMin").combobox("getValue");
	    		flag=flag+1;
	    		var times = $("#ssx").numberspinner('getValue');//获取时间
	    		tts = times;
	    		stime = times;
        			$("#ssx").numberspinner('setValue',times);
        		
	    		$("#tbCounty").datagrid('load',{"stime":times,"zllb":zllbMin,"gclb":gclbMin});
	    		
	    	},
	    	onSpinDown:function(){
	    		var gclbMin = $("#gclbMin").combobox("getValue");
	    		var zllbMin = $("#zllbMin").combobox("getValue");
	    		flag=flag+1;
	    		var times = $("#ssx").numberspinner('getValue');//获取时间
	    		tts = times;
	    		stime = tts;
	    		$("#tbCounty").datagrid('load',{"stime":times,"zllb":zllbMin,"gclb":gclbMin});
	    	},
   	    });
   	  
   	//县区搜索框
	    $("#searchx").searchbox({
	    	//prompt:'请输入...',
	    	searcher:doSearchx
	    })
	    //县区导出
	    $('#btnx').linkbutton({
	    	iconCls: 'icon-undo',
	    	plain:true,
	    }); 
   	  
    	}
    	
    });
    //function succ(){
    	
    //}
    
   	function click(){
   		var node =$("#tt").tree('getSelected');//获取节点
   		var root = $("#tt").tree('getRoot');
   		var rootid = root.id;
    	var old_node=null;
   		var id = null;
    	if(node != null && node.id != 'GX450'){
    		old_node = node;
    		id = node.id;
    	}
    	//条件满足说明为县
  		if(muniLength == 1 && coutyLength == 1){
  			node = countNode;
  		}
  		var pattern3 = new RegExp("[0-9]+");
  		//条件满足说明为市
  		if(muniLength == 1 && coutyLength > 1 && !pattern3.test(id)){
  			node = cityNode
  		}
   		//如果为自治区
   		if(node == null || node.id=="GX450"){
   			flag=0;
   			//data-options='valueField:cityCode,textField:city,url:${pageContext.request.contextPath }/monitoringstatistics/AllCity'
   			$("#bod").append("<div id='zzqTabs' style='display:none'>"
   			+"&nbsp;市：<input style='width: 100px' id=cityCombobox class=easyui-combobox name=dept  />"
   			+"&nbsp;工程类别：<input style='width: 100px' id=gcllb class=easyui-combobox name=dept  /> "
   			+"&nbsp;造林类别：<input style='width: 100px' id=zllb class=easyui-combobox name=dept  /> "
   			+"&nbsp;<label class='label_name'>时间:</label>  <input id='ss' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'>"
   			+"&nbsp;<input class='sear' id = 'search' style='width: 100px;margin-bottom: -10px'> "
   			+"&nbsp;<a id='btn' href='javascript:void(0)' onclick='deriveMunicipality()'>导出</a>"
   			+" </div>");
   			$("#ce").empty();
   			//<a href='javascript:void(0)' id='btn' class='easyui-linkbutton' data-options='iconCls:'icon-undo',plain:'false'' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a>
			puptime = tt;
   			$("#ce").append("<table id='tbMunicipality' style='displae:none'></table>");
   			var zurl = encodeURI('${pageContext.request.contextPath}/show_xbMunicipality?time='+tt+"&usr="+usr);
		    $("#tbMunicipality").datagrid({
		    	url:zurl,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				//onLoadSuccess:succ,
				//data:data,
				frozenColumns:[
					[
						{align:'center',width:100,title:'序号',  rowspan:'2',	field:'ck',checkbox:true},
						{align:'center',rowspan:2,width:100,title:'市',		field:'city'},
						/* {align:'center',rowspan:2,width:100,title:'县',		field:'county'}, */
						{align:'center',rowspan:2,width:100,title:'工程类别',		field:'ename'},
						{align:'center',rowspan:2,width:100,title:'造林类别',		field:'tname'},
						{align:'center',rowspan:2,width:100,title:'时间',		field:'time'},
					]
				],
				columns:[[
	    			{align:'center',colspan:11,width:1100,title:'抽查情况',		field:''},
	    			{align:'center',colspan:3,width:300,title:'核查质量情况',		field:''},
	    			{align:'center',colspan:7,width:700,title:'任务完成情况',		field:''},
	    			{align:'center',colspan:9,width:700,title:'管理指标',		field:''},
	    		], [
	    			
					{align:'center',width:100,title:'自检上报面积',		field:'inspectionreportarea'},
					{align:'center',width:100,title:'核实面积',		field:'verifyarea'},
					{align:'center',width:100,title:'核实合格面积',		field:'certifiedarea'},
					{align:'center',width:100,title:'作业设计面积',		field:'jobdesignarea'},
					{align:'center',width:100,title:'按作业设计施工面积',		field:'designconstructionaccordingoperation'},
					{align:'center',width:100,title:'有档案面积',		field:'filesize'},
					{align:'center',width:100,title:'开展县级自检面积',		field:'countyinspectionarea'},
					{align:'center',width:100,title:'设计育林面积',		field:'designforestarea'},
					{align:'center',width:100,title:'核实育林面积',		field:'verifyforestarea'},
					{align:'center',width:100,title:'抚育面积',		field:'barearea'},
					{align:'center',width:100,title:'管护面积',		field:'managementarea'},
					
					{align:'center',width:100,title:'面积核实率',		field:'areaverificationrate'},
					{align:'center',width:100,title:'核实面积合格率',		field:'verifyareapassrate'},
					{align:'center',width:100,title:'上报面积合格率',		field:'reportareaqualificationrate'},
					
					{align:'center',width:100,title:'当年上报面积保存率',		field:'reportretentionrateyear'},
					{align:'center',width:100,title:'造林当年上报合格面积',		field:'afforestationreportseligibleyear'},
					{align:'center',width:100,title:'全县自检合格面积',		field:'countyselfinspectionarea'},
					{align:'center',width:100,title:'推算核实面积',		field:'calculatedverificationarea'},
					{align:'center',width:100,title:'推算完成合格面积',		field:'calculatequalifiedarea'},
					{align:'center',width:100,title:'计划任务',		field:'scheduledtask'},
					{align:'center',width:100,title:'任务完成率',		field:'taskcompletionrate'},
					
					{align:'center',width:100,title:'作业设计率',		field:'jobdesignrate'},
					{align:'center',width:100,title:'按设计施工率',		field:'accordingdesignconstructionrate'},
					{align:'center',width:100,title:'建档率',		field:'byinputtingrate'},
					{align:'center',width:100,title:'自查率',		field:'selfcheckingrate'},
					{align:'center',width:100,title:'自检率',		field:'selfcheckingsrate'},
					{align:'center',width:100,title:'档案率',		field:'filerate'},
					{align:'center',width:100,title:'抚育率',		field:'raisingrates'},
					{align:'center',width:100,title:'育林率',		field:'afforestationrate'},
					{align:'center',width:100,title:'管护率',		field:'themanagementrate'},
					
	    		]],
	    		toolbar:"#zzqTabs",
	    		onLoadSuccess:function(data){
	    			console.info(data);
	    		}
			});
		    //所有的市
			$.ajaxSettings.async=false;
			$("#cityCombobox").combobox({
		    	url:'${pageContext.request.contextPath}/monitoringstatistics/AllCity', 

		    	valueField:'cityCode', 

		    	textField:'city',
		    	onSelect:onCity
		    });
			//所有的工程
			$.ajaxSettings.async=false;
			$("#gcllb").combobox({
		    	url:'${pageContext.request.contextPath}/monitoringstatistics/Allgclb', 

		    	valueField:'mark', 

		    	textField:'ename',
		    	onSelect:onGclb
		    });
			//所有的造林类别
			$.ajaxSettings.async=false;
			$("#zllb").combobox({
		    	url:'${pageContext.request.contextPath}/taskWorking/show_tasks', 

		    	valueField:'mark', 

		    	textField:'tname',
		    	onSelect:onZllb
		    });
   			return;
   		}
   		var cityCode= null;
   		var zllb = null;
   		var gclb = null;
   		//造林类别
		function onZllb(record){
			zllb = record.mark;
			$("#tbMunicipality").datagrid('reload',{"cityCode":cityCode,"zllb":zllb,"gclb":gclb});
   		}
		//工程类别
   		function onGclb(record){
   			gclb = record.mark;
   			$("#tbMunicipality").datagrid('reload',{"cityCode":cityCode,"zllb":zllb,"gclb":gclb});
   		}
   		//市
   		function onCity(record){
   			cityCode = record.citycode;
   			$("#tbMunicipality").datagrid('reload',{"cityCode":cityCode,"zllb":zllb,"gclb":gclb});
   		}
   		
	   	//验证是否是英文
	   	//是说明是市
		var pattern2 = new RegExp("[A-Za-z]+");
		if(pattern2.test(node.id)){
			var cyid = node.id;
			flag=0;
			
			$("#bod").append("<div id='sTabs' style='display:none'> "
					+"&nbsp;县：<input style='width: 100px' id=countyCombobox class=easyui-combobox name=dept  />"
					+"&nbsp;工程类别：<input style='width: 100px' id=gclbCounty class=easyui-combobox name=dept  /> "
		   			+"&nbsp;造林类别：<input style='width: 100px' id=zllbCounty class=easyui-combobox name=dept  /> "
					+"&nbsp;<label class='label_name'>时间:</label> <input id='sss' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'>"
					+"&nbsp;<input id = 'searchs'  style='width:100px;'> "
					+"&nbsp;<a href='javascript:void(0)' id='btns' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a>  "
					+" </div>");
			
			$("#ce").empty();
			
			$("#ce").append("<table id='tbCity' style='displae:none'></table>");
			if(node.id != mak && mak != null){//如果不是同一个地区重新加载表格
				$("#tbCity").datagrid('load');
		    }
			puptime = tt;
			qccty = node.id;
			var cityurl = encodeURI('${pageContext.request.contextPath}/show_xbCity?flag='+node.id+'&time='+tt+"&usr="+usr);
		    $("#tbCity").datagrid({
		    	url:cityurl,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				//data:data,
				frozenColumns:[
					[
						{align:'center',rowspan:2,width:100,title:'序号',	field:'ck',checkbox:true},
						{align:'center',rowspan:2,width:100,title:'县',		field:'county'},
						{align:'center',rowspan:2,width:100,title:'工程类别',		field:'ename'},
						{align:'center',rowspan:2,width:100,title:'造林类别',		field:'tname'},
						{align:'center',rowspan:2,width:100,title:'时间',		field:'time'},
					]
				],
				columns:[[
	    			{align:'center',colspan:11,width:1100,title:'抽查情况',		field:''},
	    			{align:'center',colspan:3,width:300,title:'核查质量情况',		field:''},
	    			{align:'center',colspan:7,width:700,title:'任务完成情况',		field:''},
	    			{align:'center',colspan:9,width:700,title:'管理指标',		field:''},
	    		], [
	    			
					{align:'center',width:100,title:'自检上报面积',		field:'inspectionreportarea'},
					{align:'center',width:100,title:'核实面积',		field:'verifyarea'},
					{align:'center',width:100,title:'核实合格面积',		field:'certifiedarea'},
					{align:'center',width:100,title:'作业设计面积',		field:'jobdesignarea'},
					{align:'center',width:100,title:'按作业设计施工面积',		field:'designconstructionaccordingoperation'},
					{align:'center',width:100,title:'有档案面积',		field:'filesize'},
					{align:'center',width:100,title:'开展县级自检面积',		field:'countyinspectionarea'},
					{align:'center',width:100,title:'设计育林面积',		field:'designforestarea'},
					{align:'center',width:100,title:'核实育林面积',		field:'verifyforestarea'},
					{align:'center',width:100,title:'抚育面积',		field:'barearea'},
					{align:'center',width:100,title:'管护面积',		field:'managementarea'},
					
					{align:'center',width:100,title:'面积核实率',		field:'areaverificationrate'},
					{align:'center',width:100,title:'核实面积合格率',		field:'verifyareapassrate'},
					{align:'center',width:100,title:'上报面积合格率',		field:'reportareaqualificationrate'},
					
					{align:'center',width:100,title:'当年上报面积保存率',		field:'reportretentionrateyear'},
					{align:'center',width:100,title:'造林当年上报合格面积',		field:'afforestationreportseligibleyear'},
					{align:'center',width:100,title:'全县自检合格面积',		field:'countyselfinspectionarea'},
					{align:'center',width:100,title:'推算核实面积',		field:'calculatedverificationarea'},
					{align:'center',width:100,title:'推算完成合格面积',		field:'calculatequalifiedarea'},
					{align:'center',width:100,title:'计划任务',		field:'scheduledtask'},
					{align:'center',width:100,title:'任务完成率',		field:'taskcompletionrate'},
					
					{align:'center',width:100,title:'作业设计率',		field:'jobdesignrate'},
					{align:'center',width:100,title:'按设计施工率',		field:'accordingdesignconstructionrate'},
					{align:'center',width:100,title:'建档率',		field:'byinputtingrate'},
					{align:'center',width:100,title:'自查率',		field:'selfcheckingrate'},
					{align:'center',width:100,title:'自检率',		field:'selfcheckingsrate'},
					{align:'center',width:100,title:'档案率',		field:'filerate'},
					{align:'center',width:100,title:'抚育率',		field:'raisingrates'},
					{align:'center',width:100,title:'育林率',		field:'afforestationrate'},
					{align:'center',width:100,title:'管护率',		field:'themanagementrate'},
					
	    		]],
	    		toolbar:'#sTabs',
			});
		  //某个市下的所有县
			$.ajaxSettings.async=false;
			$("#countyCombobox").combobox({
		    	url:'${pageContext.request.contextPath}/monitoringstatistics/AllCounty?cityCode='+cyid, 

		    	valueField:'anumber', 
		    	textField:'county',
		    	onSelect:onCounty
		    });
			//所有的工程
			$.ajaxSettings.async=false;
			$("#gclbCounty").combobox({
		    	url:'${pageContext.request.contextPath}/monitoringstatistics/Allgclb', 

		    	valueField:'mark', 

		    	textField:'ename',
		    	onSelect:onGclbCounty
		    });
			//所有的造林类别
			$.ajaxSettings.async=false;
			$("#zllbCounty").combobox({
		    	url:'${pageContext.request.contextPath}/taskWorking/show_tasks', 

		    	valueField:'mark', 

		    	textField:'tname',
		    	onSelect:onZllbCounty
		    });
		}
		var zllbCounty = null;
		var gclbCounty = null;
		var county = null;
		//造林类别
		function onZllbCounty(record){
			zllbCounty = record.mark;
			$("#tbCity").datagrid('reload',{"cityCode":county,"zllb":zllbCounty,"gclb":gclbCounty});
   		}
		//工程类别
   		function onGclbCounty(record){
   			gclbCounty = record.mark;
   			$("#tbCity").datagrid('reload',{"cityCode":county,"zllb":zllbCounty,"gclb":gclbCounty});
   		}
   		//市
   		function onCounty(record){
   			county = record.anumber;
   			$("#tbCity").datagrid('reload',{"cityCode":county,"zllb":zllbCounty,"gclb":gclbCounty});
   		}
		
	   	//验证是否是数字
	   	//是说明是县
		//var pattern3 = new RegExp("[0-9]+");
		if(pattern3.test(node.id)){
			flag=0;
			$("#bod").append("<div id='xTabs' style='display:none'> "
					+"&nbsp;工程类别：<input style='width: 100px' id=gclbMin class=easyui-combobox name=dept  /> "
		   			+"&nbsp;造林类别：<input style='width: 100px' id=zllbMin class=easyui-combobox name=dept  /> "
					+"&nbsp;<label class='label_name'>时间:</label><input id='ssx' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'> "
					+"&nbsp;<input id = 'searchx'  style='width:100px;'> "
					+"&nbsp;<a href='javascript:void(0)' id='btnx' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a> "
					+"</div>");
			
			if(node.id != mak){//如果不是同一个地区重新加载表格
		    	 $("#tbCounty").datagrid('load');
		    }
			
			$("#ce").empty();//删除指定div下子元素
		    
		    $("#ce").append("<table id='tbCounty' style='displae:none'></table>");
		    puptime = tt;
		    qccty = node.id;
		    var countyurl = encodeURI('${pageContext.request.contextPath}/show_xbCounty?dcode='+node.id+'&time='+tt+"&usr="+usr);
		    $("#tbCounty").datagrid({
		    	url:countyurl,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				columns:[[
					{align:'center',width:100,title:'序号',  rowspan:2,	field:'ck',checkbox:true},
					{align:'center',rowspan:2,width:100,title:'工程类别',		field:'ename'},
					{align:'center',rowspan:2,width:100,title:'造林类别',		field:'tname'},
					{align:'center',rowspan:2,width:100,title:'时间',		field:'time'},
	    			{align:'center',colspan:11,width:1100,title:'抽查情况',		field:''},
	    			{align:'center',colspan:3,width:300,title:'核查质量情况',		field:''},
	    			{align:'center',colspan:7,width:700,title:'任务完成情况',		field:''},
	    			{align:'center',colspan:9,width:700,title:'管理指标',		field:''},
	    		], [
	    			
					{align:'center',width:100,title:'自检上报面积',		field:'inspectionreportarea'},
					{align:'center',width:100,title:'核实面积',		field:'verifyarea'},
					{align:'center',width:100,title:'核实合格面积',		field:'certifiedarea'},
					{align:'center',width:100,title:'作业设计面积',		field:'jobdesignarea'},
					{align:'center',width:100,title:'按作业设计施工面积',		field:'designconstructionaccordingoperation'},
					{align:'center',width:100,title:'有档案面积',		field:'filesize'},
					{align:'center',width:100,title:'开展县级自检面积',		field:'countyinspectionarea'},
					{align:'center',width:100,title:'设计育林面积',		field:'designforestarea'},
					{align:'center',width:100,title:'核实育林面积',		field:'verifyforestarea'},
					{align:'center',width:100,title:'抚育面积',		field:'barearea'},
					{align:'center',width:100,title:'管护面积',		field:'managementarea'},
					
					{align:'center',width:100,title:'面积核实率',		field:'areaverificationrate'},
					{align:'center',width:100,title:'核实面积合格率',		field:'verifyareapassrate'},
					{align:'center',width:100,title:'上报面积合格率',		field:'reportareaqualificationrate'},
					
					{align:'center',width:100,title:'当年上报面积保存率',		field:'reportretentionrateyear'},
					{align:'center',width:100,title:'造林当年上报合格面积',		field:'afforestationreportseligibleyear'},
					{align:'center',width:100,title:'全县自检合格面积',		field:'countyselfinspectionarea'},
					{align:'center',width:100,title:'推算核实面积',		field:'calculatedverificationarea'},
					{align:'center',width:100,title:'推算完成合格面积',		field:'calculatequalifiedarea'},
					{align:'center',width:100,title:'计划任务',		field:'scheduledtask'},
					{align:'center',width:100,title:'任务完成率',		field:'taskcompletionrate'},
					
					{align:'center',width:100,title:'作业设计率',		field:'jobdesignrate'},
					{align:'center',width:100,title:'按设计施工率',		field:'accordingdesignconstructionrate'},
					{align:'center',width:100,title:'建档率',		field:'byinputtingrate'},
					{align:'center',width:100,title:'自查率',		field:'selfcheckingrate'},
					{align:'center',width:100,title:'自检率',		field:'selfcheckingsrate'},
					{align:'center',width:100,title:'档案率',		field:'filerate'},
					{align:'center',width:100,title:'抚育率',		field:'raisingrates'},
					{align:'center',width:100,title:'育林率',		field:'afforestationrate'},
					{align:'center',width:100,title:'管护率',		field:'themanagementrate'},
					
	    		]],
	    		toolbar:"#xTabs",
			});
		  //所有的工程
			$.ajaxSettings.async=false;
			$("#gclbMin").combobox({
		    	url:'${pageContext.request.contextPath}/monitoringstatistics/Allgclb', 

		    	valueField:'mark', 

		    	textField:'ename',
		    	onSelect:onGclbCountyMin
		    });
			//所有的造林类别
			$.ajaxSettings.async=false;
			$("#zllbMin").combobox({
		    	url:'${pageContext.request.contextPath}/taskWorking/show_tasks', 

		    	valueField:'mark', 

		    	textField:'tname',
		    	onSelect:onZllbCountyMin
		    });
			var zllbCountyMin = null;
			var gclbCountyMin = null;
			function onGclbCountyMin(obj){
				gclbCountyMin = obj.mark;
				$("#tbCounty").datagrid('reload',{"zllb":zllbCountyMin,"gclb":gclbCountyMin});
			}
			function onZllbCountyMin(obj){
				zllbCountyMin = obj.mark;
				$("#tbCounty").datagrid('reload',{"zllb":zllbCountyMin,"gclb":gclbCountyMin});
			}
		}
   	}
    </script>
</html>