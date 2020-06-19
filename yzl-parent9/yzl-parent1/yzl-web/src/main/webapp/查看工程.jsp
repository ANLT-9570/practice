<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
 <title>xxx</title>
</head>

<body>
 <!--用户管理-->
 <div class="user_Manager_style">
	  <div class="Manager_style add_user_info" style="margin-left: 20px;height: 6px;margin-top: 10px">
	    
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
				  		
		</div>
    <br>
    <div style=" width: 1170px;height: 450px;margin-left: 20px" >
    <div style=" width: 1170px;height: 500px " >
	    <table id="tableId">
	    </table>
    </div>
    
    <div id="tb" style="display: none">
    	<shiro:hasPermission name="sys:ckgc:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:ckgc:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="modify()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:ckgc:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="remove()">删除</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:ckgc:bc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="save()">保存</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:ckgc:qxbj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="cancelEdit()">取消编辑</a>
    	</shiro:hasPermission>
    	
    	<input id=searchAndecode value="" class="easyui-searchbox" style="width:110px;height:19px;margin-top: -4px">
    	
    	<shiro:hasPermission name="sys:ckgc:dc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="dcExcel()">导出</a>
    	</shiro:hasPermission>
    	
    	<br>
    	<shiro:hasPermission name="sys:ckgc:dr">
    		<a href="javascript:void(0)" id="dr" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="drExcel()">导入</a><!-- onclick="drExcel() -->
    	</shiro:hasPermission>
    	
    </div>
    
    <div id="tbs" style="dispaly:none">
    	<input id="search" style="width:110px;height:19px;margin-top: -12px" class="easyui-searchbox"  style="width:150px"/>
    </div>
    
    <script type="text/javascript">
    
    var editing ; //判断用户是否处于编辑状态
    var flag=undefined; ////判断新增和修改方法
 	 //导入
    function drExcel(){
    	$("#dr").upload({
    		action:'${pageContext.request.contextPath}/dr',
    		name:'exceName'
    	});
    }
    
    /**添加*/
	function add(){ 
		  $("#tableId").datagrid('endEdit',editing);
 		  //	$("#addDiv").window('open');
 		  $('#tableId').datagrid('unselectAll');
 		  editing=0;
 		  $("#tableId").datagrid("insertRow", {index: 0, row: {}});
 			//将新插入的那一行开户编辑状态
 			$("#tableId").datagrid("beginEdit", 0);
 			flag='add';
	}
	/*修改*/
	function modify(){
		  var rows = $('#tableId').datagrid('getSelections');
		  if(rows.length!=1){
		  	$.messager.alert('提示','只能选择一条数据进行修改','warning');
		  }else{
			  if(editing == undefined){
				  //获取要修改行索引
				  flag='edit';
			 	 editing = $('#tableId').datagrid('getRowIndex',rows[0]);
			 	 $('#tableId').datagrid('beginEdit',editing);
			  }
		 }
	}
	
	/*批量删除*/
	function remove(){
		//获取全部选中的行
		var rows = $('#tableId').datagrid('getSelections');
		//判断是否有选中的行
		if(rows.length>0){
			//批量删除
		$.messager.confirm('警告','你确定要删除当前已发布的任务吗？',function(r){
			if(r){
			for(var i=0;i<rows.length;i++){
					console.log(rows[i]);
					editing = $('#tableId').datagrid('getRowIndex',rows[i]);
					console.log("索引="+editing);
					$('#tableId').datagrid('deleteRow',editing);
					var deleted = $('#tableId').datagrid('getChanges',deleted);
					var paramList = new Array();
					for(var key in deleted){
						paramList.push(deleted[key].id+"#"+deleted[key].createtime+"#"+deleted[key].dcode);
					}	
/*  						$.post("${pageContext.request.contextPath}/delete/progress",paramList,function(){
								
						});  */
  						   $.ajax({
							   //判断是添加还是修改，请求不同的url
								url: "${pageContext.request.contextPath}/delete/progress",
								dataType: "json",
								type: "post",
								data: { "paramList": paramList },//使	用这种数组方式的，得加下一句才可以，使用传统方式
 								traditional: true, 
								success: function (data) {
										$("#tableId").datagrid('reload');
		      					 }
		  					 });
					
				}
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
		$("#tableId").datagrid('endEdit',editing);
		editing = undefined;
	}
	/*取消编辑状态*/
	function cancelEdit(){
		//回滚事物
        $('#tableId').datagrid('rejectChanges');  
        editing = undefined;  
	}
	
	$("#searchAndecode").searchbox({
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
  	        		console.log(district);
  	        	}else{
  	        		$.messager.show({title:'警告',msg:'地区数据加载失败！',timeout:8000,});
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
						{align:'center',frozen:true,width:100,title:'序号',  'rowspan':2,	field:'id',checkbox:true},
							  {align:'center',width:100,title:'市名', 'rowspan':2,	field:'city',editor:{ type:'combobox', options:{
     								 data:district, valueField: "city", textField: "city",
     								onSelect:function(data){
     							    	//获取市名的文本值
										//设置县级联动
										var rowIndex=0;//当前编辑行索引,默认为0
										 //如果是添加获取第一行，行号
										if(flag=='add'){
											rowIndex=0;
										}else{
											//是修改，获取当前选择行，行号
	                                        var row = $('#tableId').datagrid('getSelected');  
	                                        rowIndex = $('#tableId').datagrid('getRowIndex',row);//获取行号  
										}
										var target = $("#tableId").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
										//清空县级列表数据
										target.combobox('clear');
     									//县级发送远程服务，根据市名查询所有县/区
										var url='${pageContext.request.contextPath}/district/queryCountyByCityName?cityName='+data.city;
										//县级下拉框列表重载数据
										target.combobox('reload',url);
     								}
     							  }}},
						{align:'center',width:100,title:'县名',	 'rowspan':2,	field:'county',editor:{ type:'combobox', options:{
						data:district ,valueField: "county",textField: "county"
						}}}
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
        		      	   	 $("#search").searchbox({
        		       	    	 searcher:searched
        		       	     });
        		       	     function searched(value,name){
        		       	    	 //重新加载
        		       			$("#tableId").datagrid('load',{"searchKey":value});
        		       	     }

    		});
    	});
	
    	
    	//初始化不指定工程的数据
		function InitTables(area,year){
	    	//页面加载初始化表格
    		$("#tableId").datagrid({
	    		fit:true,
	    		url:'${pageContext.request.contextPath}/progress/pageQuery?area='+area+'&year='+year,
	    		rownumbers:true,
	    		pagination:true,
	    		fitColumns:false,
	    		columns:header,
	    		toolbar:'#tbs',
	    		onDblClickRow:dbUpdate
	    	});
	    	
		}
		
    	function dbUpdate(rowIndex, rowData){
    		$("#updateDate").window('open');
    		$("#updateForm").form('load',rowData);
    	}
    	
    	
    	var columnsAlls = new Array();
    	//动态根据工程id取表头数据
    	$("#year").change(function(){
			var ecode = $("#queryTask").combobox('getValue');    
			var area = $("#area").val();
			var year = this.value;
			if(ecode!=null&&ecode!=""){
    			headerInit(ecode,year,area)
    		}else{
    			InitTables(area,year);
    		}
    	});
    	$("#area").change(function(){
			var ecode = $("#queryTask").combobox('getValue');    
			var year = $("#year").val();
			var area = this.value;
			if(ecode!=null&&ecode!=""){
    			headerInit(ecode,year,area)
    		}else{
    			InitTables(area,year);
    		}
    	});
    	
   	    $('#queryTask').combobox({
   	        onChange:function(ecode,oldValue){
   	        	console.log("district="+district);
   	        	var year = $("#year").val();
   	        	var area = $("#area").val();
   	        	headerInit(ecode,year,area);
   	        }
   	    });

   	    //初始化表头数据 和 数据加载
   	    function headerInit(ecode,year,area){
	        	$.post("${pageContext.request.contextPath}/task/queryByEpcEcode",{"Ecode":ecode}
	        			,function(data){
       			 	 var columnsAll = new Array();
	        		//添加固定表头
	        		columnsAll.push(  
	        						  {align:'center',width:100,title:'序号',	field:'id',checkbox:true},
	     							  {align:'center',width:100,title:'市名', 	field:'city',editor:{ type:'combobox', options:{
	     								 data:district, valueField: "city", textField: "city",
	     								onSelect:function(data){
	     							    	//获取市名的文本值
										//var thisTarget = $("#tableId").datagrid('getEditor',{'index':0,'field':'city',type:'combobox'}).target;
										//var value = thisTarget.combobox('getValue');
										//设置县级联动
										 var rowIndex;//当前编辑行索引
										 //如果是添加获取第一行，行号
										if(flag=='add'){
											rowIndex=0;
										}else{
											//是修改，获取当前选择行，行号
	                                        var row = $('#tableId').datagrid('getSelected');  
	                                        rowIndex = $('#tableId').datagrid('getRowIndex',row);//获取行号  
										}
										var target = $("#tableId").datagrid('getEditor',{'index':rowIndex,'field':'county',type:'combobox'}).target;
										//清空县级列表数据
										target.combobox('clear');
	     									//县级发送远程服务，根据市名查询所有县/区
										var url='${pageContext.request.contextPath}/district/queryCountyByCityName?cityName='+data.city;
										//县级下拉框列表重载数据
										target.combobox('reload',url);
	     								}
	     							  }}},
	 								  {align:'center',width:100,title:'县名',		field:'county',editor:{ type:'combobox', options:{
	 									data:district ,valueField: "dcode",textField: "county"
	 								  }}},
	     							{align:'center',width:125,title:'任务下发时间',		field:'createtime'},
	     							{align:'center',width:125,title:'任务发布人',		field:'creator'}
	     			);
	        		//判断是否取到数据
	        		if(data.status == 200 && data.data != null){
	        			$("#tbs").css("display","none");
	        			//取表头数据
	        			var rows = data.data;
	        			for(var i=0;i<rows.length;i++){	
	  	        			var col={ 'field':rows[i].field+"", 'title' : rows[i].tname,'align' : 'center','width':100,editor:{ type:'text', options:{}}};
							columnsAll.push(col);      	
	        			} 
	        			//初始表格数据
	        			initTable(columnsAll,ecode,area,year);
	        			}else{
	        				$.messager.alert('提示',"该工程暂时还没有任何表头数据",'warning');
	        		}
	        	});
   	    }
   	    
   	    
   	    //初始化表格并加载数据
   	    function initTable(columnsAll,ecode,area,year){
   		  	$("#tableId").datagrid({
   	    		fit:true,
   	    		//求根据工程id分页数据，columns参数做分页总记录数计算
   	    		url:'${pageContext.request.contextPath}/progress/queryProgressByEpc?ecode='+ecode+'&area='+area+'&year='+year,
   	    		rownumbers:true,
   	    		pagination:true,
	    		toolbar:'#tb',
   	    		columns:[ columnsAll ],
    	    	onAfterEdit:function(index , record){
    	    		var EpcId = $("#queryTask").combobox('getValue');
    	    		//console.log("cc="+record.field);
					var params = new Array();
					//判断数组是否有k和v的值
    	    		for(var key in record){
    	    			if(key != "city"){
							params.push(key+"="+record[key]);
    	    			}
					}
				   $.ajax({
					   //判断是添加还是修改，请求不同的url
						url: flag=='add'?"${pageContext.request.contextPath}/add/taskProgress?epcode="+EpcId:"${pageContext.request.contextPath}/update/taskProgress",
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
