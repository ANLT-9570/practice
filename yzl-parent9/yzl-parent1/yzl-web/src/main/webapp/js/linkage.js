
var citys=["东城","西城","朝阳","丰台","石景山"];
 var countys=[["我的的","尔城","西亚","若斯","尼亚"],
	             ["俄罗斯","美国","日本","英国","法国","德国"],
	             ["澳大利亚","东南亚","阿拉伯半岛","非洲","南美洲"],
	             ["香港","九龙","新界"],
	             ["拉萨","阿里","昌都","林芝","那曲","日喀则","山南"]];
  $(function(){
 	var city = document.getElementById('city');
 	 var county = $("#county");
 	for(var i=0;i<citys.length;i++){
 		var op=new Option(citys[i]);
		city.appendChild(op);
 	}
  });
  //获取当前选择城市的索引
	 function getIndex(){
		  for(var i=0;i<citys.length;i++) {
			  if(citys[i]==$("#city").val()){
				  return i;
			  }
		  }
	 }
	 //联动添加县级
	 function addCity(){
		var index = getIndex();
		 var county = document.getElementById('county');
		county.length=0;
		for(var i=0;i<countys[index].length;i++){
			county.add(new Option(countys[index][i]));
		}
		
	} 

  