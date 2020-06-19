<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
  <![endif]-->
  <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/linkage.js"></script>
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


<title>用户管理</title>

 <script type="text/javascript">
  //全选
$(function(){
	$("#all").click(function(){
		var check = $("input[name='check']");
		if($("#all").is(":checked")){
			for(var i=0;i<check.length;i++){
				check[i].checked=true;
			}
		}else{
			for(var i=0;i<check.length;i++){
				check[i].checked=false;
			}
		}
	});
	
 });
  </script>
</head>

<body>


 <!--用户管理-->
 <div class="user_Manager_style">

  <div class="Manager_style add_user_info">
  
    <div class="title_name">抽查情况操作</div>
    <form id="search" meth="post" action="">
     <button class="btn btn-primary" type="button" id="Add_user_btn">添加数据</button>&nbsp;&nbsp;&nbsp;
	
	<label class="label_name">市名:</label>
		&nbsp;&nbsp;&nbsp;
		   
		<select size="1" id="city" name="city" style="width:200px; margin-left:10px"><option>---请选择市---</option>
			<option>hhs市</option>
			<option>dfs市</option>	
		</select>&nbsp;&nbsp;&nbsp;
	<label class="label_name">工程名:</label>
		&nbsp;&nbsp;&nbsp;
		   
				<select size="1" id="city" name="county" style="width:200px; margin-left:10px">----请选择工程---</option>
				<option>sda工程</option>
				<option>hds工程</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
		<button class="btn btn-primary" type="search">查询</button>
		</form>
     <div id="Add_user_style" style="display:none">
    <div class="add_user_style clearfix">
    <div><label class="label_name">选择单位和工程:</label>&nbsp;&nbsp;&nbsp;
    	   
        	<select size="1" id="city" name="city" style="width:200px; margin-left:10px"><option>---请选择市---</option>
        	<option>as市</option>
        	<option>fh市</option>
        </select>&nbsp;&nbsp;
           
        	<select size="1" id="county" name="county" style="width:200px; margin-left:10px"><option>---请选择县---</option>
        	<option>as县</option>
        	<option>fh县</option>
        </select>
        &nbsp;&nbsp;
           
        	<select size="1" id="city" name="county" style="width:200px; margin-left:10px">---请选择工程---</option>
        	<option>xx工程</option>
        	<option>ko工程</option>
        </select>
        </div>
        <br>

     <div id="hao"></div>
<form id="modifys" action="sds/csaxz/saaa" method="post" > 
     <table id="Finalist_list" class="table table-striped table-bordered table-hover">
     	<div style="position: fixed;"> 
	     	<tr align="center">
	     		<th >自检上报面积</th>
	     		<th >核实面积</th>
	     		<th >核实合格面积</th>
	     		<th >作业设计面积</th>
	     		<th >按作业设计施工面积</th>
	     		<th >有档案面积</th>
	     		<th >开展县级自检面积</th>
	     		<th >抚育面积</th>
	     		<th >设计育林面积</th>
	     		<th >核实育林面积</th>
	     		<th >管护面积</th>
	     	</tr>
     	</div>
     	<tr align="center">
     		<td><input id="add_SIRArea" name="sIRArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_VTArea" name="vTArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_VerifiedArea" name="verifiedArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_JobArea" name="jobArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_DTCAATTW" name="dTCAATTW" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_FileArea" name="fileArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_COCLSIA" name="cOCLSIA" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_WBArea" name="wBArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_DFArea" name="dFArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_VFArea" name="vFArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     		<td><input id="add_TMArea" name="tMArea" type="text" style="width: 100%; margin-left: -0.2px;"></td>
     	</tr>
     	
     </table>
 </form>
    </div>
    </div>
    </div>
     
      <div class="Manager_style">
    <div class="title_name">抽查情况列表</div>
    <div class="Role_list">
     <table id="Role_list" cellpadding="0" cellspacing="0" class="table table-striped table-bordered table-hover">
     <br><br>
      <button  type="button" id="delete" class="btn btn-warning">删除</button>&nbsp;&nbsp;&nbsp;
      <button  type="button" id="edit" class="btn btn-primary ">修改</button>&nbsp;&nbsp;&nbsp;
		<button  type="button" id="testForm" class="btn btn-primary ">测试</button>
		<br><br>
      <thead>
      	
       <tr>
       	   <th width="1%"><input id="all" type="checkbox" ></th>
		   <th width="60">序列号</th>
		   <th width="70">单位</th>
		   <th width="90">项目</th>
		   <th width="90">自检上报面积</th>
		   <th width="90">核实面积</th>
		   <th width="90">核实合格面积</th>
		   <th width="90">作业设置面积</th>
		   <th width="100">按作业设置施工面积</th>
		   <th width="80">有档案面积</th>
		   <th width="90">开展县级自检面积</th>
		   <th width="90">抚育面积</th>
		   <th width="90">设置育林面积</th>
		   <th width="90">核实育林面积</th>
		   <th width="90">管护面积</th>
	   </tr>
      </thead>
      <tbody>
       <tr>
       <th width="1%"><input value="5" name="check" type="checkbox" ></th>
       <td >1</td>
       <td name="county">xx县</td>
       <td>xxx工程</td>
	   <td>321</td>
       <td >234</td>
       <td>453</td>
       <td>345</td>
       <td>345</td>
       <td>102</td>
       <td>546</td>
	   <td >234</td>
       <td>      
        789
        </td>
        <td>123</td>
        <td>456</td>
        </tr>
        <tr>
       <th width="1%"><input value="4" name="check" type="checkbox" ></th>
       <td >2</td>
       <td>xx县</td>
       <td>xxx工程</td>
       <td >522</td>
       <td>546</td><td>213</td>
       <td>754</td><td></td>
       <td>102</td><td></td>
	   <td ></td>
       <td >      789
        </td>
        <td>123</td>
        <td>456</td>
        </tr> 
        <tr>
        <th width="1%"><input value="3" name="check" type="checkbox" ></th>
       <td >3</td>
       <td>xx县</td>
       <td>xxx工程</td>
       <td>457</td>
       <td>247</td><td>890</td>
       <td>278</td><td>756</td>
       <td>102</td><td>354</td>
	   <td></td>
       <td >      
        </td>
        <td>123</td>
        <td>456</td>
        </tr>  
 
        <tr>
        <th width="1%"><input name="check" type="checkbox" ></th>
       <td >4</td>
       <td>xx县</td>
       <td>xxx工程</td>
       <td>234</td>
       <td>453</td>
       <td>213</td>
       <td>657</td>
       <td>213</td>
       <td>602</td>
       <td>543</td>
	   <td >788</td>
       <td >      789
        </td>
        <td>123</td>
        <td>456</td>
        </tr>
      </tbody>
     </table>
     
     <div class="page_style">
  <select name="" size="1">
    <option value="1">10</option>
    <option value="2">20</option>
    <option value="3">30</option>
  </select>
  <a href="" class="icon-step-backward page_btn" ></a>
  <a href="" class="icon-caret-left page_btn"></a>
  第
  <select name="" size="1">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
  </select>
  共2页
   <a href="" class=" icon-caret-right page_btn"></a>
  <a href="" class="icon-step-forward page_btn"></a>
  </div>
     
     
    </div>
  </div>
 </div>
 </div>
 
 
 
<script src="assets/js/jquery.min.js"></script>
    <!-- <![endif]-->
    <!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->
    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
    </script>
    <!-- <![endif]-->

    <!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
	<script src="assets/js/bootstrap.min.js"></script>
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
	/*
	$(".btn-warning").each(function(){
		 $(this).click(function(){
			var e=$(this);
			var id=10;
			 layer.confirm('是否删除这个个工程？', {
				  btn: ['是','否'] //按钮
				}, function(){
					var params = {"TId":id};
					$.post("/finish/task/delete",params,function(data){
						if(data.status==200){
							$.messager.alert('提示','删除商品成功!',undefined,function(){
								layer.msg('删除成功！', {time: 1000,icon: 1});
							});
						}
				});
				});
		});
	});*/
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
