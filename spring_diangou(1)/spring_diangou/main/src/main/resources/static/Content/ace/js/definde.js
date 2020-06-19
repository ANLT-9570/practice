





$.ajax({
		url:'/gxsl/user/login.do',
		dataType:'json',
		type:'post',
		data:{"username":name,"password":pass},
		success:function(data){
			
			var msg = data.list;
			
			if(msg == 400){//说明没登录
				window.location.href="../login.html";
			}
			
			for(var i = 0;i<msg.length;i++){//遍历主菜单
				
				var list = msg[i];//主菜单
				var chil = list.childrens;//undefined子节点
				if(list.ismenu == 1){
					$("#menu").append('<li id='+list.id+'><a href="#" class="dropdown-toggle"><i class="icon-user"></i><span class="menu-text">'+list.name+'</span><b class="arrow icon-angle-down"></b></a></li>');
				}
				if(chil != undefined){
					$("#"+list.id).append('<ul class="submenu" id='+list.id+list.name+'></ul>');
					for(var x=0;x<chil.length;x++){//遍历子节点
						$("#"+list.id+list.name).append('<li id='+chil[x].id+'><a href="javascript:addTabs({id:"'+chil[x].id+'",title:"'+chil[x].name+'",close:true,url:"'+chil[x].url+'",icon:"icon-user"})"><i class="icon-user"></i><span class="menu-text">'+chil[x].name+'</span></a></li>');
					}
				}
			}
		},
	})
	/*<li id="li_3"><a href="#" class="dropdown-toggle"><i
							class="icon-user"></i><span class="menu-text">权限管理</span><b
							class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							<li id="li_31"><a
								href="javascript:addTabs({id:'31',title: '用户管理',close: true,url: '/gxsl/hello.test',icon:'icon-user'});"><i
									class="icon-user"></i><span class="menu-text">用户管理</span></a></li>
									
							<li id="li_32"><a
								href="javascript:addTabs({id:'32',title: '角色管理',close: true,url: '/SystemSetting/Role',icon:'icon-apple'});"><i
									class="icon-apple"></i><span class="menu-text">角色管理</span></a></li>
									
							<li id="li_33"><a
								href="javascript:addTabs({id:'33',title: '菜单管理',close: true,url: '/SystemSetting/Menu',icon:'icon-list'});"><i
									class="icon-list"></i><span class="menu-text">菜单管理</span></a></li>
										
						</ul>
					</li>*/