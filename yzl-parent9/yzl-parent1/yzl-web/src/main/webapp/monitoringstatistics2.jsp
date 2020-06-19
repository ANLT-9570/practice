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
  
<!-- 导入jquery核心类库 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>

<style type="text/css">
.datagrid-body{
style:"width:615px"
}
</style>
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath }js/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }js/ztree/jquery.ztree.exedit-3.5.js"></script> --%>
<script type="text/javascript">
	function selected(){
		var eid = $("#epcode").combobox("getValue");
		$("#tpcode").combobox({
			url:'${pageContext.request.contextPath }/findByEcode?epcode='+eid+'',
			valueField:'tcode',
			textField:'tname',
			height:30,
			width:120,
		});
	}
	function searched(){
		
		var did = $("#did").combobox("getValue");
		var epcode = $("#epcode").combobox("getValue");
		var tpcode = $("#tpcode").combobox("getValue");
		var time = $("#time").combobox("getValue");
		
		$("#tab").datagrid('load',{"did":did,"epcode":epcode,"tpcode":tpcode,"time":time});
	}
	function clean(){
		$("#did").combobox("setValue","");
		$("#epcode").combobox("setValue","");
		$("#tpcode").combobox("setValue","");
		$("#time").combobox("setValue","");
	}
</script>
<style type="text/css">
span.searchbox{
margin-bottom:-8px;
}
span.l-btn-left{
margin-bottom:-8px;
}
</style>
</head>

<body id="bod">
 <!--用户管理-->
     <div class="easyui-layout" style=" width: 1330px;height: 550px;margin-top: -5px">
	    <div data-options="region:'west',title:'',split:true,border : false," style="width:185px;">
	    
		    <ul id="tt" class="easyui-tree">
		    
		    </ul>
	    </div>
		<div id="ce" data-options="region:'center',title:'',border : false" style="padding:5px;background:#eee;">
			
		</div>
    </div>
    
    <script type="text/javascript">
    
    $(function(){
    	var node =$("#tt").tree('getSelected');//获取节点
    	click(node);
    })
    var date = new Date();
    //得到现在的时间年份
    var tt = date.getFullYear();
    var tts;
    
    function doSearch(){
    	//获取搜索框输入的内容
    	var sname = $("#search").searchbox('getValue');
    	var times = $("#ss").numberspinner('getValue');//获取时间
    	$("#tbMunicipality").datagrid('load',{"sname":sname,"stime":times});
    }
    function doSearchs(){
    	//获取搜索框输入的内容
    	var sname = $("#searchs").searchbox('getValue');
    	var times = $("#sss").numberspinner('getValue');//获取时间
    	$("#tbCity").datagrid('load',{"sname":sname,"stime":times});
    }
    function doSearchx(){
    	//获取搜索框输入的内容
    	var sname = $("#searchx").searchbox('getValue');
    	var times = $("#ssx").numberspinner('getValue');//获取时间
    	$("#tbCounty").datagrid('load',{"sname":sname,"stime":times});
    }
    
    
  //导出
  function deriveMunicipality(){
	  window.location.href="${pageContext.request.contextPath}/deriveMunicipality";
  }
    //展示树
    $("#tt").tree({
   		url:'${pageContext.request.contextPath}/show_dis',
   		animate:true,
   		onClick:click
   	});
    var mak;
    var zzq;
    var flag;
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
    	    		flag=flag+1;
    	    		var times = $("#ss").numberspinner('getValue');//获取时间
    	    		tts = times;
            			$("#ss").numberspinner('setValue',times);
            		
    	    		$("#tbMunicipality").datagrid('load',{"stime":times});
    	    		
    	    	},
    	    	onSpinDown:function(){
    	    		flag=flag+1;
    	    		var times = $("#ss").numberspinner('getValue');//获取时间
    	    		tts = times;
    	    		$("#tbMunicipality").datagrid('load',{"stime":times});
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
	    		flag=flag+1;
	    		var times = $("#sss").numberspinner('getValue');//获取时间
	    		tts = times;
        			$("#sss").numberspinner('setValue',times);
        		
	    		$("#tbCity").datagrid('load',{"stime":times});
	    		
	    	},
	    	onSpinDown:function(){
	    		flag=flag+1;
	    		var times = $("#sss").numberspinner('getValue');//获取时间
	    		tts = times;
	    		$("#tbCity").datagrid('load',{"stime":times});
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
	    		flag=flag+1;
	    		var times = $("#ssx").numberspinner('getValue');//获取时间
	    		tts = times;
        			$("#ssx").numberspinner('setValue',times);
        		
	    		$("#tbCounty").datagrid('load',{"stime":times});
	    		
	    	},
	    	onSpinDown:function(){
	    		flag=flag+1;
	    		var times = $("#ssx").numberspinner('getValue');//获取时间
	    		tts = times;
	    		$("#tbCounty").datagrid('load',{"stime":times});
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
    
   	function click(node){
   		
   		//如果为自治区
   		if(node == null || node.id=="GX450"){
   			flag=0;
   			$("#bod").append("<div id='zzqTabs' style='display:none'>  <a id='btn' href='javascript:void(0)' onclick='deriveMunicipality()'>导出</a>   <label class='label_name'>时间:</label>   <input id='ss' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'>  <input class='sear' id = 'search' style='width: 100px;margin-bottom: -10px'> </div>");
   			$("#ce").empty();
   			//<a href='javascript:void(0)' id='btn' class='easyui-linkbutton' data-options='iconCls:'icon-undo',plain:'false'' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a>
			
   			$("#ce").append("<table id='tbMunicipality' style='displae:none'></table>");
		    $("#tbMunicipality").datagrid({
		    	url:'${pageContext.request.contextPath}/show_xbMunicipality?time='+tt,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				//data:data,
				frozenColumns:[
					[
						{align:'center',rowspan:2,width:100,title:'市',		field:'city'},
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
	    		toolbar:"#zzqTabs",
			});
   			return;
   		}
   		
   		
	   	//验证是否是英文
	   	//是说明是市
		var pattern2 = new RegExp("[A-Za-z]+");
		if(pattern2.test(node.id)){
			flag=0;
			
			$("#bod").append("<div id='sTabs' style='display:none'>  <a href='javascript:void(0)' id='btns' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a>     <label class='label_name'>时间:</label>      <input id='sss' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'><input id = 'searchs'  style='width:100px;'> </div>");
			
			$("#ce").empty();
			
			$("#ce").append("<table id='tbCity' style='displae:none'></table>");
			if(node.id != mak && mak != null){//如果不是同一个地区重新加载表格
				$("#tbCity").datagrid('load');
		    }
		    
		    $("#tbCity").datagrid({
		    	url:'${pageContext.request.contextPath}/show_xbCity?flag='+node.id+'&time='+tt,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				//data:data,
				frozenColumns:[
					[
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
		}
		
	   	//验证是否是数字
	   	//是说明是县
		var pattern3 = new RegExp("[0-9]+");
		if(pattern3.test(node.id)){
			flag=0;
			$("#bod").append("<div id='xTabs' style='display:none'>  <a href='javascript:void(0)' id='btnx' onclick='deriveMunicipality()'><label class='label_name'>导出</label></a>  <label class='label_name'>时间:</label>   <input id='ssx' class='easyui-numberspinner' value='' style='width:80px;'required='required' data-options='min:2012,max:2090,editable:false'>  <input id = 'searchx'  style='width:100px;'>  </div>");
			
			if(node.id != mak){//如果不是同一个地区重新加载表格
		    	 $("#tbCounty").datagrid('load');
		    }
			
			$("#ce").empty();//删除指定div下子元素
		    
		    $("#ce").append("<table id='tbCounty' style='displae:none'></table>");
		    
		    $("#tbCounty").datagrid({
		    	url:'${pageContext.request.contextPath}/show_xbCounty?dcode='+node.id+'&time='+tt,
		    	fit:true,
				pagination:true,
				border : false,
				striped : true,
				rownumbers:true,
				columns:[[
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
		}
   	}
   	
    </script>
    
    
 
<script type="text/javascript">
$("#si").click(function(){
	$("#issue").window('close');
});
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
