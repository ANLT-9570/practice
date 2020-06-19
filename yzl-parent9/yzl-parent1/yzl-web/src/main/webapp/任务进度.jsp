<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>xxx</title>
</head>

<body>
<div class="page-content">
 <!--用户管理-->
 <div class="user_Manager_style">
 
 <shiro:hasPermission name="sys:rwjd:gc">
	  <div class="Manager_style add_user_info">
	    <div class="title_name">cC操作</div>
	    	<form id="formTable" method="post">
				<label class="label_name" style="margin-top: 5px">工程:</label>
								&nbsp;&nbsp;&nbsp;
				<input id="queryTask" name="epcode" class="easyui-combobox" style="padding-top: 5px" data-options="url:'${pageContext.request.contextPath }/project/query',
				  		valueField:'ecode',textField:'ename',height:30,width:190"/>
				  		
				  		
				  				&nbsp;&nbsp;<label class="label_name" style="margin-top: 5px">地区:</label>
								&nbsp;&nbsp;&nbsp;
				<input id="area" name="area" />
				  				&nbsp;&nbsp;<label class="label_name" style="margin-top: 5px">年份:</label>
								&nbsp;&nbsp;&nbsp;
				<input id="year" name="year" />
			</form>
				  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
 </shiro:hasPermission>

    <div style=" width: 1155px;height: 450px" >

	    <table id="tableId">
	    </table>
	    <div id="addDiv" style="width:500px;height:300px;"  class="easyui-window"  data-options="closed:true">
	    	<table id="addTable"></table>
	    </div>
    </div>
    
    <div id="tb" style="display: none">
    	<shiro:hasPermission name="sys:rwjd:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="modify()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwjd:bc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwjd:qxbj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cancelEdit()">取消编辑</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwjd:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="sub()">提交</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwjd:shtg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="approve()">审核通过</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:rwjd:th">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true" onclick="sendBack()">退回</a>
    	</shiro:hasPermission>
    	
    	<input id="searchKeyAndecode" value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: -4px">
    </div>
    
    <div id="tbs" style="display: none">
    	<input id="searchKey" value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: -4px">
    </div>
    
    
    <script type="text/javascript">

    var editing ; //判断用户是否处于编辑状态
    var flag=undefined; ////判断新增和修改方法
    
    /*修改*/
	function modify(){
		  var rows = $('#tableId').datagrid('getSelections');
		  if(rows.length!=1){
		  	$.messager.alert('提示','只能选择一条数据进行修改','warning');
		  	return 
		  }else{
			  if(editing == undefined){
				  //获取要修改行索引
				  flag='edit';
			 	 editing = $('#tableId').datagrid('getRowIndex',rows[0]);
			 	 $('#tableId').datagrid('beginEdit',editing);
			  }
		 }
	}
	
	/*保存*/
	function save(){
		$("#tableId").datagrid('endEdit',editing);
		editing = undefined;
	}
	/*取消编辑状态*/
	function cancelEdit(){
		//回滚事物
        $('#tableId').datagrid('rejectChanges');  
        editing = undefined;  
	}
	
    //提交
    function sub(){
    	var rows = $("#tableId").datagrid('getSelections');
    	if(rows.length!=1){
			$.messager.alert("提示","请最一行数据进行提交","warning");
			return 
		}
    	var row = $("#tableId").datagrid('getSelected');
    	if(row==null){
			$.messager.alert("提示","请最一行数据进行提交","warning");
			return 
    	}
		$.messager.confirm('提示',"你确定要提交当前数据吗？",function(r){
			if(r){  
				var paramList = new Array();
				for(var key in row){
					paramList.push(key+"="+row[key]);
				}
				if(!paramList.length>0){
					$.messager.alert("提示","提交失败,要提交的数据为空！");
				}
				$.ajax({
					   //判断是添加还是修改，请求不同的url
						url: "${pageContext.request.contextPath}/submit/taskProgressSheet",
						dataType: "json",
						type: "post",
						data: { "paramList": paramList },//使用这种数组方式的，得加下一句才可以，使用传统方式
						traditional: true,
						success: function (data) {
							if(data.status==200){
								$.messager.alert("提示","提交成功！","info");
							}else{
								$.messager.alert("提示","提交失败！","warning");
							}
							$("#tableId").datagrid('reload');
						}	
						
				  });
			}
		});
		
    }
	
  //审核任务进度
    function approve(){
    	var rows = $("#tableId").datagrid('getSelections');
    	if(rows.length!=1){
			$.messager.alert("提示","请选择一行数据进行提交","warning");
			return 
		}
    	var row = $("#tableId").datagrid('getSelected');
    	if(row==null){
			$.messager.alert("提示","请选择一行数据进行提交","warning");
			return 
    	}
		$.messager.confirm('提示',"你确定审核通过此项目？",function(r){
			if(r){  
				var paramList = new Array();
				for(var key in row){
					if(key=='mark'){
						//判断任务是否已经提交   没有提交的任务  标识带有'-'
						if(row[key].length<10){
							$.messager.alert('提示','请先提交完成的任务，在进行审核！','warning');
							return
						}						
					}
					paramList.push(key+"="+row[key]);
				}
				if(!paramList.length>0){
					$.messager.alert("提示","提交失败,要提交的数据为空！");
				}
				$.ajax({
					   //判断是添加还是修改，请求不同的url
						url: "${pageContext.request.contextPath}/approve/taskProgressSheet",
						dataType: "json",
						type: "post",
						data: { "paramList": paramList },//使用这种数组方式的，得加下一句才可以，使用传统方式
						traditional: true,
						success: function (data) {
							if(data.status==200){
								$.messager.alert("提示","审核通过！","info");
								$("#tableId").datagrid('reload');
							}else if(data.status==303){
								$.messager.alert("提示",data.msg,"error");
								$("#tableId").datagrid('reload');
							}
						}	
				  });
			}
		});
    	
    }
    
    //退回任务进度
    function sendBack(){
    	var rows = $("#tableId").datagrid('getSelections');
    	if(rows.length!=1){
			$.messager.alert("提示","请最一行数据进行退回","warning");
			return 
		}
    	var row = $("#tableId").datagrid('getSelected');
    	if(row==null){
			$.messager.alert("提示","请最一行数据进行退回","warning");
			return 
    	}
		$.messager.confirm('提示',"你确定要退回当前数据吗？",function(r){
			if(r){  
				var paramList = new Array();
				for(var key in row){
					paramList.push(key+"="+row[key]);
				}
				if(!paramList.length>0){
					$.messager.alert("提示","退回失败,要退回的数据为空！");
				}
				$.ajax({
					   //判断是添加还是修改，请求不同的url
						url: "${pageContext.request.contextPath}/sendback/taskProgressSheet",
						dataType: "json",
						type: "post",
						data: { "paramList": paramList },//使用这种数组方式的，得加下一句才可以，使用传统方式
						traditional: true,
						success: function (data) {
							if(data.status==200){
								$.messager.alert("提示","退回成功！","info");
								$("#tableId").datagrid('reload');
							}else{
								$.messager.alert("提示","退回失败！","warning");
								$("#tableId").datagrid('reload');
							}
						}	
				  });
			}
		});
    }
    
    $("#searchKeyAndecode").searchbox({
	    	 searcher:searched
	     });
	     function searched(value,name){
	    	 //重新加载
			$("#tableId").datagrid('load',{"searchKey":value});
	     }
    
    
    
    
    $(function(){
        
    	//存放地区数据
       	var district;
		var columnsResult = new Array();
		
		var stairHeader = new Array();
		var header = new Array();
		
       	//查询所有地区

		$.post("${pageContext.request.contextPath}/district/queryDistinctCity",function(data){
  	        	if(data.status==200){
  	        		district=data.data;
  	        	}else{
  	        		$.messager.show({title:'警告',msg:'地区数据加载失败！',timeout:2000,});
  	        	}
		});
 
    	$.post("${pageContext.request.contextPath}/task/findAll",function(data){
    		if(data.status!=200){
    			$.messager.show({title:'警告',msg:'表头数据加载失败！',timeout:2000,});
    		}

    	    $.ajaxSettings.async = false;
    		//取一级表头
    		$.post("${pageContext.request.contextPath}/project_taskNumber/query",function(data){
    			console.log("c="+data.length);
				//添加固定表头
				stairHeader.push(  
						{align:'center',width:100,title:'序号',  'rowspan':2,	field:'id',checkbox:true},
						{align:'center',width:100,title:'市名',  'rowspan':2,	field:'city'},
						{align:'center',width:100,title:'县名',	 'rowspan':2,	field:'county'},
						{align:'center',width:100,title:'状态',	'rowspan':2,	field:'checkMark'}
				);
				//添加表头工程
    			for(var i=0;i<data.length;i++){		
    				var col={'field':'', 'colspan':data[i].taskNumber, 'title':data[i].epc.ename, 'align':'center', 'width':100*data[i].taskNumber};
    				stairHeader.push(col);
    				$.post("${pageContext.request.contextPath}/task/queryByEpcEcode",{"Ecode":data[i].epc.ecode},function(result){
    					if(result.status==200){
    						var rows = result.data;
    						console.log(rows);
							//添加表头任务
    						for(var j=0;j<rows.length;j++){
    							var col={'field':rows[j].field,'title' : rows[j].tname,'align' : 'center','width':100,editor:{ type:'text', options:{}}};
    							columnsResult.push(col);
    						}
    					}

    				});
    			}
        				
        		    		header.push(stairHeader);
        		    		header.push(columnsResult);
        		    		InitTables("","");

       		     	   	 $("#searchKey").searchbox({
       		       	    	 searcher:searched
       		       	     });
       		       	     function searched(value,name){
       		       	    	 //重新加载
       		       			$("#tableId").datagrid('load',{"searchKey":value});
       		       	     }

    		});
    	});
    	

	   	function dbUpdate(rowIndex, rowData){
	   		$("#updateDate").window('open');
	   		$("#updateForm").form('load',rowData);
	   	}
		
    	
        
    	var addColumns = new Array();
    	
    	//动态根据工程id取表头数据
    	$("#year").change(function(){
			var ecode = $("#queryTask").combobox('getValue');    
			var area = $("#area").val();
			var year = this.value;
			if(ecode!=null&&ecode!=""){
    			projectInit(ecode,year,area);
    		}else{
    			InitTables(area,year);
    		}
    	});
    	
    	$("#area").change(function(){
			var ecode = $("#queryTask").combobox('getValue');    
			var year = $("#year").val();
			var area = this.value;
			if(ecode!=null&&ecode!=""){
				projectInit(ecode,year,area);
    		}else{
    			InitTables(area,year);
    		}
    	});
    	
    	//动态根据工程id取表头数据
   	    $('#queryTask').combobox({
   	        onChange:function(ecode,oldValue){
   	        	var year = $("#year").val();
   	        	var area = $("#area").val();
   	        	projectInit(ecode,year,area);
   	        }
   	    });
    	
		function InitTables(area,year){
		   	//页面加载初始化表格
			$("#tableId").datagrid({
		   		fit:true,
		   		url:'${pageContext.request.contextPath}/taskProgressSheet/pageQuery?area='+area+'&year='+year,
		   		rownumbers:true,
		   		pagination:true,
		   		fitColumns:false,
		   		columns:header,
		   		toolbar:'#tbs'/* [ 
			       			{text:'<input id="searchKey" value="" class="easyui-searchbox" style="width:152px;height:19px;margin-top: -4px">'},
			    ] */,
		   		onDblClickRow:dbUpdate
		   	});
		}
   	    //跟指定的工程进行初始表格
       	function projectInit(ecode,year,area){
	   	    $.post("${pageContext.request.contextPath}/task/queryByEpcEcode",{"Ecode":ecode},function(data){
	       		var columnsAll = new Array();
	       		//计算动态表头个数，做分页总记录统计
	       		var headers=0;
	       		//添加固定表头
	       		columnsAll.push(  
	       						  {align:'center',width:100,title:'序号',	field:'id',checkbox:true},
		   							  {align:'center',width:100,title:'市名', 	field:'city'},
									  {align:'center',width:100,title:'县名',		field:'county'},
	    							{align:'center',width:125,title:'任务下发时间',		field:'createtime'},
	    							{align:'center',width:125,title:'状态',		field:'checkMark'}
	    			);
	       		//判断是否取到数据
	       		if(data.status == 200 && data.data != null){
	       			$("#tbs").css("display","none");
	       			//取表头数据
	       			var rows = data.data;
	       			console.log(rows.length);
	       			for(var i=0;i<rows.length;i++){	
		        			var col={ 'field':rows[i].field+"", 'title' : rows[i].tname,'align' : 'center','width':100,editor:{ type:'text', options:{}}};
		        			headers++;
	       				console.log(col);
						columnsAll.push(col);      	
	       			} 
	       			console.log(columnsAll);
	       			//初始表格数据
	       			initTable(columnsAll,headers,ecode,area,year);
	       			addColumns=columnsAll;
	       			}else{
	       				$.messager.alert('提示',"该工程暂时还没有任何数据",'warning');
	       		}
	       	});
   	    }
   	    //初始化表格
   	    function initTable(columnsAll,headers,ecode,area,year){
   		  	$("#tableId").datagrid({
   	    		fit:true,
   	    		//求根据工程id分页数据，columns参数做分页总记录数计算
   	    		url:'${pageContext.request.contextPath}/taskProgressSheet/pageQueryByEcode?ecode='+ecode+'&area='+area+"&year="+year,
   	    		rownumbers:true,
   	    		pagination:true,
	    		toolbar:'#tb',
   	    		columns:[ columnsAll ],
    	    	onAfterEdit:function(index , record){
    	    		var EpcId = $("#formTable").serialize();
    	    		//console.log("cc="+record.field);
					var params = new Array();
					//判断数组是否有k和v的值
    	    		for(var key in record){
    	    			if(key != "city" && key != "county"){
							params.push(key+"="+record[key]);
    	    			}
					}
				   $.ajax({
					   //判断是添加还是修改，请求不同的url
						url: "${pageContext.request.contextPath}/update/taskProgressSheet",
						dataType: "json",
						type: "post",
						data: { "params": params },//使用这种数组方式的，得加下一句才可以，使用传统方式
						traditional: true,
						success: function (data) {
								$("#tableId").datagrid('reload');
      					 }	
  					 });
   	    		}, 
   	    		onDblClickRow:dbUpdate
   	    		
   	    	});
     	   	
   	    }
});
    
    

    
    </script>

 </div>
 </div>

</body>
</html>
