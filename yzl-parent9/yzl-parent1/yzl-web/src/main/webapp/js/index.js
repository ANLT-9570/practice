//生成菜单
var menuItem=Vue.extend({
	name:'menu-item',
	props:{item:{}},
	template:[
		'<li>',
		'<a href="#" class="dropdown-toggle"><i class="icon-edit"></i><span class="menu-text"> {{item.name}} </span><b class="arrow icon-angle-down"></b></a>',
		'</li>',
	].join('')
});


//创建一个组件
Vue.component("menuItem",menuItem);

var vm = new vue({
	el:'#rrapp',
	data:{
		
	}
});
/*<li>
<a href="#" class="dropdown-toggle"><i class="icon-edit"></i><span class="menu-text"> 地区工程管理 </span><b class="arrow icon-angle-down"></b></a>
	<ul class="submenu">
		<li class="home"><a href="javascript:void(0)" name="地区.jsp" title="地区" class="iframeurl"><i class="icon-double-angle-right"></i>地区</a></li>
		<li class="home"><a href="javascript:void(0)" name="epc.jsp" title="工程"  class="iframeurl"><i class="icon-double-angle-right"></i>工程</a></li>
		<li class="home"><a href="javascript:void(0)" name="task.jsp" title="任务" class="iframeurl"><i class="icon-double-angle-right"></i>任务</a></li>
		<li class="home"><a href="javascript:void(0)" name="EpcTemplate.jsp" title="工程模板" class="iframeurl"><i class="icon-double-angle-right"></i>工程模板</a></li>
	</ul>
</li>*/