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
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>	
<script type="text/javascript">
var Mids=null;
	$(function(){
		
		//初始化树形控件
/* 		var setting = {
			    async: {
			        enable: true,
			        url:'${pageContext.request.contextPath}/function/getParentNode',
			        autoParam:["functionTree","nameq"],
			        otherParam:{"functionTree":"namec"},
			        dataFilter: filter,
			        type: "get"
			    },
			    callback: {
			        beforeAsync: beforeAsync,
			        onAsyncSuccess: onAsyncSuccess,
			    },
				check : {
					enable : true,
				}
			};	


		function filter(treeId, parentNode, childNodes) {
		    if (!childNodes) return null;
		    for (var i=0, l=childNodes.length; i<l; i++) {
		        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		    }
		    return childNodes;
		}

			function beforeAsync() {
			    curAsyncCount++;
			}

			function onAsyncSuccess(event, treeId, treeNode, msg) {
			    curAsyncCount--;
			    if (curStatus == "expand") {
			        expandNodes(treeNode.children);
			    } else if (curStatus == "async") {
			        asyncNodes(treeNode.children);
			    }

			    if (curAsyncCount <= 0) {
			        curStatus = "";
			    }
			}

			var curStatus = "init", curAsyncCount = 0, goAsync = false;
			function expandAll() {
				if (!check()) {
			        return;
			    }
			    var zTree = $.fn.zTree.getZTreeObj("functionTree");
			    expandNodes(zTree.getNodes());
			    if (!goAsync) {
			        curStatus = "";
			    }
			}
			function expandNodes(nodes) {
			    if (!nodes) return;
			    curStatus = "expand";
			    var zTree = $.fn.zTree.getZTreeObj("functionTree");
			    for (var i=0, l=nodes.length; i<l; i++) {
			    	Mids.push(nodes[i].id);
			        zTree.expandNode(nodes[i], true, false, false);//展开节点就会调用后台查询子节点
			        if (nodes[i].isParent && nodes[i].zAsync) {
			            expandNodes(nodes[i].children);//递归
			        } else {
			            goAsync = true;
			        }
			    }
			}

			function check() {
			    if (curAsyncCount > 0) {
			        return false;
			    }
			    return true;
			}

			$(document).ready(function(){
			    $.fn.zTree.init($("#functionTree"), setting);
			    setTimeout(function(){
			        expandAll("functionTree");
			    },300);//延迟加载
			}); */
			
			
			Mids = new Array();//定义一个数组存放MenuId	
			//递归查询所有树节点
 			function FindIdsByRecursion(row){
				//获取子节点
				var children = row.children;
				if(children!=null||children != ""){
					//把父节点添加到数组
					Mids.push(row.id);
					console.log(children.length);
					for(var i=0;i<children.length;i++){
						FindIdsByRecursion(children[i]);
					}
				}else{
					//如果没有子节点直接添加到数组
					Mids.push(row.id);
				}
			} 
		
		// 授权树初始化
		var setting = {
			data : {
				key : {
					title : "t",
				},
				simpleData : {
					enable : true
				}
			},
			check : {
				enable : true,
				nocheckInherit: true,
			}/* ,
			callback: { 
				onClick: function (e, treeId, treeNode, clickFlag) { 
					var zTree = $.fn.zTree.getZTreeObj("functionTree");
					console.log(e+" || "+ treeId+" || "+ treeNode+" || "+ clickFlag);
					zTree.checkNode(treeNode, !treeNode.checked, true); 
				}  
			} */


		};
		
		$.ajax({
			url : '${pageContext.request.contextPath}/function/getParentNode',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				//console.log("树初始化");
				for(var i=0;i<data.length;i++){
					//获取所有数据id 用作回显数据
					FindIdsByRecursion(data[i]);
				}
				for(var i=0;i<data.length;i++){
					if(data[i].icon != null){
						data[i].icon=null;
					}
				}
				$.fn.zTree.init($("#functionTree"), setting, data);
			},
			error : function(msg) {
				//alert('树加载异常!');
				
				//alert('树加载异常!');
			}
		});
		 
		

		// 数据表格属性
		$("#grid").datagrid({
			toolbar :"#tb",
			url : '${pageContext.request.contextPath}/role/pageQuery',
			pageList: [10,30,50],
			rownumbers : true,
			pagination : true,
			fit:true,
			columns : [[
				{
			    	checkbox : true,
					field : 'id',
					title : '编号',
					width : 200
				},
				{
					field : 'name',
					title : '名称',
					width : 200
				}, 
				{
					field : 'description',
					title : '描述',
					width : 200
				},
				{
					field : 'createTime',
					title : '创建时间',
					width : 200
				} 
			]]
		});
		
	});
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>
	
	<div id="tb">
     	
    	<shiro:hasPermission name="sys:jsgl:tj">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="add()">添加</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:jsgl:xg">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
    	</shiro:hasPermission>
    	
    	<shiro:hasPermission name="sys:jsgl:sc">
    		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="deldata()">删除</a>
    	</shiro:hasPermission>
    	
    </div>
	<div id="loading">
	</div>
	<!-- 修改窗口 -->
		<div id="modifyDiv"   class="easyui-window" region="center" data-options="region:'center',closable:false,draggable:true,closed:true,iconCls:'icon-edit',resizable:true,minimizable:false,maximizable:false,modal:true,shadow:true" style="overflow-y:auto;height: 600px; width:450px;" border="false">
			<form id="modifyForm" action="${pageContext.request.contextPath }/role/updateRole" method="post">
				<table   id="modifyTab" fit="true"   width="400px" height="500px"  class="table-edit"   align="center">
					<tr class="title">
						<td colspan="2">角色信息</td>
					</tr>
					<tr>
						<td>名称</td>
						<td>
						<input type="hidden" name='id'/>
						<input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>授权</td>
						<td>
							<input type="hidden" id="MenuIds" name="functionIds"/>
							<ul id="functionTree" class="ztree"></ul>
						</td>
					</tr>
					</table>
					
										   <br><br><br>
		     <button style="margin-left: 250px" class="btn btn-primary" type="button" id="modifyBtn">保存</button>
		     <button style="margin-left: 50px" class="btn btn-primary" type="button" id="cancelBtn">取消</button>
		     <script type="text/javascript">
		     //删除
		     function deldata(){
					var rows = $("#grid").datagrid('getSelections');
					if(rows.length>0){
						$.messager.confirm('提示','你确定删除这'+ rows.length +'条数据吗？',function(r){
							if(r){
								var array = new Array();
								for(var i=0;i<rows.length;i++){
									//把所有的id添加到集合中
									array.push(rows[i].id);
								}
								//用,把id隔开
								var ids = array.join(",");
								$.post("${pageContext.request.contextPath}/delete/role",{"ids":ids},function(data){
									$("#grid").datagrid('reload');
								});
							}
						});
					}else{
						$.messager.alert('提示','<h1>请最少选择一行进行删除!</h1>','warning');
					}
				}
		     
		     //修改
		     function update(){
					var rows = $("#grid").datagrid('getSelections');
					var mid = new Array(Mids);
					//console.info(mid);
					if(rows.length == 1){
						var rowsData  = $("#grid").datagrid("getSelected");
						$("#modifyDiv").window('open');
						$("#modifyTab").form('load',rowsData);
						$.post("${pageContext.request.contextPath}/function/getFunctionByRoleId",{"roleId":rowsData.id},function(data){
							//关联角色的权限数据回显，并设置为选中状态
							var rows = data.data;
							
							if(rows != null && rows.length>0){
								for(var i=0;i<rows.length;i++){
									for(var j=0;j<Mids.length;j++){//console.info(1);
										//判断id是否相同
										if(rows[i].id==Mids[j]){
											//获取当前树对象
											var treeObj = $.fn.zTree.getZTreeObj("functionTree");
											
											
											//获取当前节点
											var node = treeObj.getNodesByParam("id",rows[i].id);
											//勾选当前选中的节点
											treeObj.checkNode(node[0],true,false);
										}
									}
								}
							}
						});
					}else{
						$.messager.alert('提示','<h1>请选择一行进行编辑!</h1>','warning');
					}
				}
		     //添加
		     function add(){
					location.href='${pageContext.request.contextPath}/admin/role_add.jsp';
				}
			     //取消
			 		$("#cancelBtn").click(function(){
			 			$("#modifyDiv").window('close');
						//获取当前树对象
						var treeObj = $.fn.zTree.getZTreeObj("functionTree");
						treeObj.checkAllNodes(false);
			 		});
			     
			     	//修改
					$("#modifyBtn").click(function(){
						//表单校验
						var trObj = $.fn.zTree.getZTreeObj("functionTree");
						//获取所有被勾选的权限
						var nodes = trObj.getCheckedNodes(true);
						//定义一个集合，用来存放权限 Id
						var nodeArray = new Array();
						for(var i=0;i<nodes.length;i++){
							//把id存入集合
							nodeArray.push(nodes[i].id);
						}
						//把所有权限Id用','，隔开
						var ids = nodeArray.join(',');
						//把ids设置到隐藏域
						$("#MenuIds").val(ids);
						var r = $("#modifyForm").form("validate");
						if(r){
							//表单提交
							$("#modifyForm").submit();
						}
					});

		     </script>
			</form>
		</div>

</body>
</html>