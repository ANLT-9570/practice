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
$(".btn-warning").each(function(){
	 $(this).click(function(){
		var e=$(this);
		var id=10;
		var el= e.parent().parent().children("input").get(0);
		var id=$(el).val();
		 layer.confirm('是否删除这个个工程？', {
			  btn: ['是','否'] //按钮
			}, function(){
				var params = {"TId":id};
				$.post("/spot/check/delete",params,function(data){
					if(data.status==200){
						$.messager.alert('提示','删除工程成功!',undefined,function(){
							//layer.msg('删除成功！', {time: 1000,icon: 1});
							$("#contentList").datagrid("reload");
						});
					}
			});
			});
	});
});
