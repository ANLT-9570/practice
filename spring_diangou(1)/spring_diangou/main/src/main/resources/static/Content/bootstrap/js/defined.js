
//获取选择的所有行
function getSelected(tableId){
	return $(tableId).bootstrapTable('getSelections');
}

//表格刷新
function refreshTable(tableId){
	$(tableId).bootstrapTable('refresh');
}

//判断
function judgeData(row){
	if(row.length != 1){
		bootbox.alert("请选择一个");
		return false;
	}
}


//便利数组数据,获取指定的..元素
function traArray(array,element){
	
	var newArray = new Array;
	for(var i=0;i<array.length;i++){
		newArray.push(array[i].element);
	}
	return newArray;
}