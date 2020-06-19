<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
 		<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css">
		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
        <link rel="stylesheet" href="css/style.css"/>
 <head>
<!--         <meta charset="utf-8"><link rel="icon" href="https://jscdn.com.cn/highcharts/images/favicon.ico"> -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            /* css 代码  */
        </style>
<!--         <script src="https://code.highcharts.com.cn/jquery/jquery-1.8.3.min.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/data.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/drilldown.js"></script> -->
    </head>
<title>无标题文档</title>
<style type="text/css">
.index_style{
	padding: 0px;
}
#tableDiv{
	
}
.pagination{
	width:100%
}
#sector{
/* 
	max-width: 500px;
	min-width: 250px;
	margin: 0 auto; */
	max-height: 330px;
	min-height: 260px;
	width: 500px;
	margin: 0 auto; 
	margin-top:20px; 
}
#container{
	height: 400px;
	max-width: 2000px;
	min-width: 1000px;
	margin: 0 auto;
}
/* 图形 下钻后返回上层的按钮 */
.highcharts-button-box{
	height: 30px;
}



</style>
		<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>    
		<script src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/highcharts/highcharts.js"></script>
		<script src="${pageContext.request.contextPath}/highcharts/drilldown.js"></script>
		<script src="${pageContext.request.contextPath}/highcharts/exporting.js"></script>
<!-- 		<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>  -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/plugins/jquery.messager.js"></script>
		  
		<!-- 导入jquery核心类库 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
		<script src="js/text.js" type="text/javascript" ></script>


<script type="text/javascript">


$(function(){
	//ajax请求获取数据
	$.post("${pageContext.request.contextPath}/xb/show/highcharts",{"year":$("#sectorYear").val()},function(data){
		var drilldownNodeList = new Array();
		//取柱形图下钻 节点的数据
		for(var i=0;i<data.length;i++){
			drilldownNodeList.push(data[i].drilldownNode);
			data[i].drilldownNode==null
		}
		//柱形图初始化
		contarinerInit(data,drilldownNodeList);
		//sectorInint(data,drilldownNodeList);
		/* 饼形图初始化 */
		

	});
	
	//ajax请求获取饼形图的数据 
	$.post("${pageContext.request.contextPath}/xb/show/pie",{"year":$("#sectorYear").val()},function(data){
		var drilldownNodeList = new Array();
		//取饼形图下钻 节点的数据
		for(var i=0;i<data.length;i++){
			drilldownNodeList.push(data[i].drilldownNode);
			data[i].drilldownNode==null
		}
		//饼形图初始化
		sectorInint(data,drilldownNodeList);
		/* 饼形图初始化 */
	});

	//柱形图初始化
	function contarinerInit(data,drilldownNodeList){
    	Highcharts.setOptions({
			lang: {
				drillUpText: '《返回',  // 下钻后返回按钮
				noData:"数据为空",
			}
		});
		// 柱形图  
		Highcharts.chart('container', {
			credits:{
			     enabled: false // 禁用版权信息
			},
			chart: {
				type: 'column'
			},
			title: {
				text:  '<span style="font-size:21px">'+$("#containerYear").val() +'</span>年造林完成比例', 
	
			},
			subtitle: {
				text: '',
			},
			xAxis: {
				type: 'category'
			},
			yAxis: {
				title: {
					text: '总的造林任务完成比例'
				}
			},
			legend: {
				enabled: false
			},
			plotOptions: {
				series: {
					borderWidth: 0,
					dataLabels: {
						enabled: true,
						format: '{point.y:.1f}%'
					}
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%'
			},
			series: [{
				name: '广西营造林',
				colorByPoint: true,
				data: data
			}],
			drilldown: {
				series:  drilldownNodeList,
		
			},
			//按钮样式
			navigation: {
				buttonOptions: {
					theme: {
						// Good old text links
						style: {
							color: 'blue',
							textDecoration: 'underline'
						}
					}
				}
			},
			//打印 下载
			exporting: {

				buttons: {
					contextButton: {
						enabled: false
					},
					exportButton: {
						text: '下载',
						// Use only the download related menu items from the default context button
						menuItems: Highcharts.getOptions().exporting.buttons.contextButton.menuItems.splice(2)
					},
					printButton: {
						text: '打印',
						onclick: function () {
							this.print();
						}
					}
				}
			}
		});
	}

	
	//饼形图初始化
	function sectorInint(data,drilldownNodeList){
				        // 创建图例
			        	Highcharts.setOptions({
							lang: {
								drillUpText: '《返回',
								noData:"数据为空",
							}
						});
				        Highcharts.chart('sector',{
							credits:{
							     enabled: false // 禁用版权信息
							},
				            chart: {
				                type: 'pie'
				            },
							title: {
								text: "<span style='mfont-size:15px;'><span>"+$('#sectorYear').val() +"</span>年各地区造林完成量所占比例",
								//x:-20
							},
				            plotOptions: {
				                series: {
				                    dataLabels: {
				                        enabled: true,
				                        format: '{point.name}: {point.y:.1f}%',
				                    }
				                }
				            },

				            tooltip: {
				                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}%'
				            },
							series: [{
								name: '广西',
								colorByPoint: true,
								data: data
							}],
				            drilldown: {//下钻的数据
				                series: drilldownNodeList,
/* 								theme: {
									fill: 'white',
									'stroke-width': 1,
									stroke: 'silver',
									r: 0,
									states: {
										hover: {
											fill: '#bada55'
										},
										select: {
											stroke: '#039',
											fill: '#bada55'
										}
									}
								} */
				            },
							//按钮样式
							navigation: {
								buttonOptions: {
									theme: {
										// Good old text links
										style: {
											color: 'blue',
											textDecoration: 'underline'
										}
									}
								}
							},
				            exporting: {
								buttons: {
									contextButton: {
										enabled: false
									},	
									exportButton: {
										text: '下载',
										x:-30,
										y:-15,
										onclick: function () {
											this.exportChart();
										}
									},
									printButton: {
										text: '打印',
										x:9,
										y:-15,
										onclick: function () {
											this.print();
										}
										
									}
								}
							}
				        });
				}


	//柱形图点击年份的时候触发
	$("#containerYear").numberspinner({
		"onChange":function(){
			//ajax请求获取数据
			$.post("${pageContext.request.contextPath}/xb/show/highcharts",{"year":$("#containerYear").val()},function(data){
				var drilldownNodeList = new Array();
				for(var i=0;i<data.length;i++){
					drilldownNodeList.push(data[i].drilldownNode);	
					data[i].drilldownNode==null
				}
				//柱形图初始化
				contarinerInit(data,drilldownNodeList);
		    });
		}
	});

	//饼形图点击年份的时候触发
	$("#sectorYear").numberspinner({
		"onChange":function(){
			//ajax请求获取数据
			$.post("${pageContext.request.contextPath}/xb/show/pie",{"year":$("#sectorYear").val()},function(data){
				var drilldownNodeList = new Array();//存储下钻的数据
				//取饼形图下钻 节点的数据
				for(var i=0;i<data.length;i++){
					drilldownNodeList.push(data[i].drilldownNode);
					data[i].drilldownNode==null
				}
				//饼形图初始化
				sectorInint(data,drilldownNodeList);
				/* 饼形图初始化 */
			});
		}
	});

	
 	$("#DataTemplateTable").datagrid({
		url:'${pageContext.request.contextPath}/backlogTaskIssued/pageQuery',
		fit:true,
		//url:'${pageContext.request.contextPath}/taskIssued/queryTaskData?year='+year+'&areaCode='+areaCode+"&ZLLB="+ZLLB+"&usr="+usr,
		rownumbers:true,
		pagination:true,
		checkOnSelect:false,
		fitColumns:false,
		border : false,
		striped : true,
		rownumbers:true,
		frozenColumns:[
						[
							{align:'center',width:100,title:'序号',  rowspan:'2',field:'id',checkbox:true},//frozen:true,
							{align:'center',rowspan:2,width:100,title:'待办事项',	field:'name'},
							{align:'center',rowspan:2,width:100,title:'数量',field:'number'},
							{align:'center',rowspan:2,width:100,title:'时间',field:'updateTime'},
							{align:'center',rowspan:2,width:100,title:'市',field:'city'},
							{align:'center',rowspan:2,width:100,title:'县/区',field:'county'},
							{align:'center',rowspan:2,width:100,title:'查看',field:'check'}
						]
					],
    	onClickCell: function(index,field,value){
    		//截取jsp文件名
			var begin = value.indexOf("value='&");//获取前索引
			var end = value.indexOf("&'");//获后前索引
			var valJsp = value.substring(begin,end);//截取
			var val = valJsp.split("value='&")[1];
   			var jsp = val;
   			//截取状态
   			idValueBegin = value.indexOf("id='");
   			idValueEnd = value.indexOf(">'");
   			console.log("value="+value);
   			var idValue = value.substring(idValueBegin,idValueEnd);//截取
   			var statu = idValue.split("id='")[1];//去掉前缀
   			var cname="待办任务"
   			if(statu==1){
    			cname = "待审核的任务";
   			}else if(statu==3){
   				cname = "被退回的任务";
   			}
    		var _iframe = window.parent;
    		_iframe.$('#nav_list').find('li.home').removeClass('active');
    		//_iframe.$("[name='taskIssued.jsp']").parent().addClass('active');;
    		_iframe.$("#iframe").attr("src", jsp).ready();
    		_iframe.$("#iframe").attr("value", statu).ready();//设置需要查询的状态
    		_iframe.$("#Bcrumbs").attr("href", jsp).ready();
    		_iframe.$(".Current_page a").attr('href', jsp).ready();
    		_iframe.$(".Current_page").html(cname).ready();
    		_iframe.$("#parentIframe").html("").css("display",
    				"none").ready();
    			
    	}
	}); 
 	

  $(function(){
	 	//获取当前浏览器的类型
		var userAgent = navigator.userAgent;
		console.info(userAgent);
		if(userAgent.indexOf("Firefox") > -1){//判断是否是火狐
			//$("#tet").css("width","335px");
		$("#upt").css("height","400px");
		$("#tet").css("width","650px");
		}
		
		if(userAgent.indexOf("Trident") > -1){
			$("#upt").css("height","400px");
	    	$("#tet").css("width","650px");
		}
		if(userAgent.indexOf("QQBrowser") > -1){//QQBrowser frame xjdd_style
			//console.info(userAgent.indexOf("QQBrowser"));: ;: ;
			$(".col-xs-12").css("width","1700px");
			$(".highcharts-root").css("width","1700px");
			$("#sector").css("margin-left","100px");
			$("#highcharts-jil4tm2-3").css("width","1200px");
			$("#container").css("width","1300px");
			
		}
		if(userAgent.indexOf("InfoPath") > -1){//QQBrowser frame xjdd_style
			//console.info(userAgent.indexOf("QQBrowser"));: ;: ;: ;
			$(".col-xs-12").css("width","1700px");
			$(".highcharts-root").css("width","1700px");
			$("#sector").css("margin-left","100px");
			$("#highcharts-jil4tm2-3").css("width","1200px");
			$("#container").css("width","1300px");
		}
		if(userAgent.indexOf("Chrome") > -1){//谷歌
			//console.info(userAgent.indexOf("QQBrowser"));: ;: ;
			//$(".frame xjdd_style").css("width","555px");!important
			$(".xjdd_style").css("width","555px");
		}
		if(userAgent.indexOf("Firefox") > -1){//火狐
			//console.info(userAgent.indexOf("QQBrowser"));: ;: ;Firefox
			//$(".frame xjdd_style").css("width","555px");!important
			$(".xjdd_style").css("width","555px");
		}
  })
 	
/*  	$("#taskissued").click(function(){
 		
 	}); */

 	
/*  	$("#taskWorking").click(function(){
		var cid = 'taskIssued.jsp';
		var cname = "任务下发";
		$('#nav_list').find('li.home').removeClass('active');
		$("[name='taskIssued.jsp']").parent().addClass('active');;
		$("#iframe").attr("src", cid).ready();
		$("#Bcrumbs").attr("href", cid).ready();
		$(".Current_page a").attr('href', cid).ready();
		$(".Current_page").html(cname).ready();
		$("#parentIframe").html("").css("display",
				"none").ready();
 	}); */
});
	


</script>
        <style type="text/css">
         html { overflow-x:hidden; }
         .datagrid-view{
         /* margin-left:-10px;margin-top:-10px;
         width: 700px !important; */
         }
         .datagrid-pager{
         	/* margin-top: 20px !important;margin-left:-10px !important; */
         	
         }
         .datagrid-wrap{
         width: 690px !important;
         }
        </style>
	<body >
<div class="page-content" id="tt">
						<!-- <div class="page-header">
							<h1>首页展示<small></small></h1>
						</div>/.page-header width:1284px;-->
						<div class="row">
							<div class="col-xs-12" style="width:1313px;">							
                               <div class="index_style" style="float:left">
                              <!-- 待办事项padding:5px; -->
                              	<div id="tableDIV" style="border:1px solid #CCC;position: absolute;z-index:1000;width:700px;height:340px;margin-left: 15px;margin-top: 10px;">
                              			 <table  id='DataTemplateTable' style="displae:none;"></table>
                              	</div> 			
                             			<!-- 饼形图 width:510px; -->
                           		<div style="position: absolute;z-index:1000;width:945px;height:340px;margin-left:735px;margin-top:10px;min-height:300px;max-height:400px;" class="frame xjdd_style">
                               		 <!-- <div style="margin-top:-30px;background-color:initial" class="n_tit"><span>年各地区造林完成量所占比例</span><a href="/news/index.html" class="more"></a></div>  -->
                               	  	<div style="margin-top: -35px;margin-left: -30px;">
                               		<label   class="label_name"><b>年份:</b></label>
        							<input style="width:110px;height:19px;"  id="sectorYear" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  />
                           	       </div>
                           	         <!-- <div style="margin-top:-30px;background-color:initial;font-size: 24px;" class=""><font id="showSectorYear"></font><span>年各地区造林完成量所占比例</span><a href="/news/index.html" class="more"></a></div>  -->
                                 	<div style="margin-left: -50px;"  id="sector" class="content"></div>
                               	</div> 
	                            </div>
                                <div  style="border:1px solid #CCC;margin-top:360px;absolute;margin-left:15px;z-index:1000;" class="frame ddgl_style">
                                 	<!-- 柱形图  width:800px;-->
                                 	<div style="margin-top:15px;width:1313px;">
                               	 	<label style="margin-top:0px;margin-left:10px;" class="label_name"> <b>年份:</b></label>
       								<input id="containerYear" value=<%=new SimpleDateFormat("yyyy").format(new Date())%>; data-options="min:2000,max:2050,editable:false" class="easyui-numberspinner"  style="margin-top:0px;width:110px;;height:19px;" />
                                	</div>
                                <!--  	<span  class="title_name">柱形图</span> -->
                                 	<div  style="width:1000px" id="container" class="content"> </div>
                                 </div>
							</div>
						</div>
               
					</div>					
					
					
</body>
<script type="text/javascript">

function read(){
	window.location.href="${pageContext.request.contextPath}/taskIssued.jsp";
}
</script>
</html>
