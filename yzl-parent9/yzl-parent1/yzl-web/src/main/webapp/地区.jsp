<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	
<script
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"
	type="text/javascript"></script>
	
<title>用户管理</title>

</head>

<body>
	<br>
	<br>
     <div style="margin-left:20px; width: 1300px;height: 500px">
     
<!--      	        <div class="search-input-wrap clearfix">
            <div class="form-input-wrap f-l">
            <form id="searchForm">
                    <input type="search" id="searchInp" autocomplete="off" name="city" placeholder="请输入关键词" class="input-kw">
                </form>
                <i class="iconfont if-message"></i>
                <i class="iconfont if-close"></i>
            </div>
        </div> -->
     
     
	    <table id="tab">
	    </table>
    </div>
     
     <div id="tb">
     	<shiro:hasPermission name="sys:dq:tj">
	     	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
	    </shiro:hasPermission> 	
	    
	    <shiro:hasPermission name="sys:dq:cx">
	     	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="sear()">查询</a>
	    </shiro:hasPermission>
	    
	    <shiro:hasPermission name="sys:dq:sc">
	     	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="del()">删除</a>
	    </shiro:hasPermission>
	    
	    <shiro:hasPermission name="sys:dq:dr">
	     	<a href="javascript:void(0)" id="button-import" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" >导入</a>
     	</shiro:hasPermission>
     </div>
     
     <script type="text/javascript">
     
     //删除
     function del(){
			//获取所有被选中的对象
			var rows = $("#tab").datagrid('getSelections');
	      	if(rows.length>0){
	      		$.messager.confirm('提示','你确定删除这'+ rows.length +'条数据吗？',function(r){
	      			if(r){
			      		//定义一个数组接收所有id
			      		var array =  new Array();
			      		for(var i=0;i<rows.length;i++){
			      			array.push(rows[i].dcode);
			      		}
			      		var ids = array.join(',');
			      		$.post("${pageContext.request.contextPath}/district/delete",{"ids":ids},function(data){
			      			$("#tab").datagrid('reload');
			      		});
	      			}
	      		});
	      	}else{
	      		$.messager.alert('提示','<br/><h1>请最少选择一行进行删除！</h1>','warning');
	      	}
		}
     
	//查询
	function sear(){
			var searchKey = $("#searchInp").val();
			$.post("${pageContext.request.contextPath}/district/search",{"searchKey":searchKey},function(data){
				if(data.status==200){
					$("#tab").datagrid('load',data.data);
				}else{
					$.messager.alert('提示',data.data,'warning');
				}
			}); 
			$("#searchWindow").window('open');
		}
	//添加
     function add(){
			$("#modifyDiv").window('open');
		}
     $("#tab").datagrid({
 		url:'${pageContext.request.contextPath}/district/pageQuery',
			nowrap:false,//字符太多是否换行
			fit:true,//自适应
			toolbar : '#tb'/* [
						{
						   id : 'search',
						   iconCls : 'icon-search',
			        	   text : '查询',
							handler : function(){
	 							var searchKey = $("#searchInp").val();
								$.post("${pageContext.request.contextPath}/district/search",{"searchKey":searchKey},function(data){
									if(data.status==200){
										$("#tab").datagrid('load',data.data);
									}else{
										$.messager.alert('提示',data.data,'warning');
									}
								}); 
								
								/* $("#searchWindow").window('open');
								
	
							}
						},
						
						{
							id : 'add',
							text : '添加',
							iconCls : 'icon-add',
							handler : function(){
								$("#modifyDiv").window('open');
							}
						},
						{
							id : 'delete',
							text : '删除',
							iconCls : 'icon-cancel',
							handler : function(){
								//获取所有被选中的对象
								var rows = $("#tab").datagrid('getSelections');
						      	if(rows.length>0){
						      		$.messager.confirm('提示','你确定删除这'+ rows.length +'条数据吗？',function(r){
						      			if(r){
								      		//定义一个数组接收所有id
								      		var array =  new Array();
								      		for(var i=0;i<rows.length;i++){
								      			array.push(rows[i].dcode);
								      		}
								      		var ids = array.join(',');
								      		$.post("${pageContext.request.contextPath}/district/delete",{"ids":ids},function(data){
								      			$("#tab").datagrid('reload');
								      		});
						      			}
						      		});
						      	}else{
						      		$.messager.alert('提示','<br/><h1>请最少选择一行进行删除！</h1>','warning');
						      	}
							}
						},
						{
							id : 'button-import',
							text : '导入',
							iconCls : 'icon-redo',
						}
					  ] */ ,
			
			//表头
			columns:[
						[
							{align:'center',width:100, title:'序列化',checkbox:true, 	field:'dcode'},
							{align:'center',width:300, title:'城市',			field:'city'},
							{align:'center',width:300, title:'县/区',	field:'county'}
						 ]
					], 
			//显示序号rownumbers
			rownumbers:true,
			pagination:true//显示分页
 	});
     
		//将表单对象转成Json
		$.fn.serializeJson=function(){  
         var serializeObj={};  
         var array=this.serializeArray();
         $(array).each(function(){  
             if(serializeObj[this.name]){  
                 if($.isArray(serializeObj[this.name])){  
                     serializeObj[this.name].push(this.value);  
                 }else{  
                     serializeObj[this.name]=[serializeObj[this.name],this.value];  
                 }  
             }else{  
                 serializeObj[this.name]=this.value;   
             }  
         });  
         return serializeObj;  
     };
     
     
     $(function(){
 		// 查询分区
 		$('#searchWindow').window({
 	        title: '查询分区',
 	        width: 400,
 	        modal: true,
 	        shadow: true,
 	        closed: true,
 	        height: 400,
 	        resizable:false
 	    });
 	     $("#btn").click(function(){
 			var p = $("#searchForm").serializeJson();
 			console.log(p);
 			$("#tab").datagrid('load',p);
 			$("#searchWindow").window("close");
      });
 	     
 		$("#button-import").upload({
			action:'${pageContext.request.contextPath}/district/importXls',
			name:'districtFile'
		});
     });
     

     </script>
     
     
     	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="searchForm">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="city"/></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="county"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
     
     	<!-- 添加窗口 -->
		<div id="modifyDiv"  class="easyui-window" region="center" data-options="region:'center',closable:false,draggable:false,closed:true,iconCls:'icon-edit',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true" style="overflow:auto;padding:5px;" border="false">
			<form id="modifyForm" action="${pageContext.request.contextPath }/district/addDistrict" method="post">
				<table id="modifyTab" class="table-edit"  width="80%" align="center">
					<tr class="title">
						<td colspan="2">添加地区</td>
					</tr>
					<tr>
						<td>市</td>
						<td>
						<input type="text" name="city" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>县/区</td>
						<td>
							<input id="county" type="text" name="county" data-options="required:true"/><span id="info"></span>
						</td>
					</tr>
				</table>
				<br><br><br>
		     <button style="margin-left: 100px" class="btn btn-primary" type="button" id="addBtn">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelBtn">取消</button>
		     <script type="text/javascript">
		     var flag=true;
		     $(function(){
		    	 
		    	//地区校验
			     $("#county").blur(function(){
			    	 
			    	 //获取到输入框的植
			    	 var county = $(this).val().trim();
			    	 //alert(county);
			    	 if(county!=null && county != ""){
			    		 $.post("${pageContext.request.contextPath }/verifyCounty",{"county":county},
			 					function(data){
			    			 		
			    			 		var infoname="";
			 						
			    			 		//获取到返回来的植
			 						var rescounty="";
			 						if(data != null){
			 							rescounty = data.county;
			 						}
			 						
			 						if(county==rescounty){
			 							flag=false;
			 							infoname="该地区已经存在";
			 							$("#info").css("color","red");
			 						}else{
			 							flag=true;
			 							infoname="该地区名称可以使用";
										
										$("#info").css("color","green");
			 						}
			 						$("#info").html(infoname);
			 					},
			 			"json")
			    	 }else{
			    		 var io = "不能为空";
			    		 $("#info").css("color","red");
			    		 $("#info").html(io);
			    	 }
			     });
		     })
		     
			     //取消
		 		$("#cancelBtn").click(function(){
		 			//关闭添加窗口
		 			$("#modifyDiv").window('close');
		 		});
			     
			     //添加
			     $("#addBtn").click(function(){
			    	 var r = $("#modifyForm").form('validate');
			    	 if(flag==true){
			    		 if(r){
					     		$("#modifyForm").submit();
					     	}
			    	 }
			     	
			     });

			 </script>
	 </form>
  </div>
<!-- <script src="assets/js/jquery.min.js"></script> -->
    <!-- <![endif]-->
    <!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->
    <!--[if !IE]> -->
<!--     <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
    </script> -->
    <!-- <![endif]-->

    <!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<!-- 	<script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script> -->
<!-- 	<script src="assets/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="assets/layer/layer.js"></script>
<script type="text/javascript">
function hide1(){
document.getElementById('ycxz').style.display='';
document.getElementById('gys_name').style.display='none';
  
}
function display1(){
document.getElementById('ycxz').style.display='none';
document.getElementById('gys_name').style.display='';
}

$(function(){
	//删除
	//删除
	 $("#search").click(function(){$("#searchForm").submit();});                 $("#delete").click(function(){
		 var check = $("input[name='check']");
		if($(check).is(":checked")){
			layer.confirm('是否删除这个个工程？', {
				  btn: ['是','否'] //按钮
				},function(){
					alert(22);
					 var array = new Array(); 
					 for(var i=0;i<check.length;i++){
						 if(check[i].checked==true){
							 //将所有id添加到数值中
							 array.push($(check[i]).val());
						 }
						 //将每个id用“,”隔开
					 }
					 var ids = array.join(",");
					 location.href="/tset/delete?ids="+ids;
					 $("#Role_list").datagrid("reload");
			}); 
		}else{
			alert("请选择要删除的目标");
		}
	 });
	 /*修改*/
	$('#edit').on('click', function(){
		var checks = $("input[name='check']");
		//判断被选中的有几个
		var a=0;
		var id;
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked==true){
				a++;
				id=$(checks[i]).val();
			}
		}
		var params="id:"+id;
		if(a==1){
			//$.post("/spot/check/findByid",params,function(data){
		   layer.open({
	        type: 1,
	        title: '数据修改',
			maxmin: true, 
			shadeClose: true, //点击遮罩关闭层
	        area : ['860px' , '400px'],
	        content:$('#Add_user_style'),		
			btn: ['保存','取消'],
		    yes: function(index, layero){
		    	$("#modifys").submit();
		    	if ($("#city").val()=="-请选择市---"){}  
		     else{
				  layer.alert('修改成功！',{
	               title: '提示框',				
				icon:1,		
				  });
				  alert("添加成功")
				  layer.close(index);      
			  }	
	     } 	, 
		 cancel: function(index){
			 
			   layer.alert('确定退出！',{
	               title: '提示框',				
				  icon:1,		
				  });
			
				} 
		    });
		  //});
		}else{
			alert("只能选择一个进行修改")
		}
	});
});

/***判断用户添加文本**/
 $('#Add_user_btn').on('click', function(){
	   layer.open({
        type: 1,
        title: '数据写入',
		maxmin: true, 
		shadeClose: true, //点击遮罩关闭层
        area : ['860px' , '400px'],
        content:$('#Add_user_style'),		
		btn: ['添加','取消'],
	    yes: function(index, layero){ 	 
		  if ($("#add_SIRArea").val()==""){
			  layer.alert('自检上报面积不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }  
          if ($("#add_VTArea").val()==""){
			  layer.alert('核实面积不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          } 
		  
		   if ($("#add_VerifiedArea").val()==""){
			  layer.alert('核实合格面积不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }   
		    
		   if ($("#phone").val()==""){
			  layer.alert('电话号码不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }  
		   if ($("#user_name").val()==""){
			  layer.alert('用户名不能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          } 
		  
		  if ($("#select_status").val()==""){
			  layer.alert('用户状态能为空!',{
              title: '提示框',				
				icon:0,
			    
			 });
			return false;
          }   
		 if(!$(':radio[name=check]:checked').length) {
              layer.alert('请选择用户性质!',{
                title: '提示框',				
				icon:0,				
          }); 
		   return false;
		   
             }
		
	     else{		
	    	 $.java
			  layer.alert('添加成功！',{
               title: '提示框',				
			icon:1,		
			  }); 
			  layer.close(index);      
		  }	
     } 	, 
	 cancel: function(index){
		 
		   layer.alert('确定退出！',{
               title: '提示框',				
			  icon:1,		
			  });
		
	} 
    });
});

</script> -->
</body>
</html>
