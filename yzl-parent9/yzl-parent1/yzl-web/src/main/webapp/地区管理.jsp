<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
  <![endif]-->
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="css/style.css"/>
<title>å°åºç®¡ç</title>
</head>
<body>
<div class="page-content">
<div class="gys_style">
 <div class="Manager_style">
    <div class="title_name">æ·»å å°åº</div>
    <button type="button" class="btn btn-primary" id="Add_supplier_btn">æ·»å å°åº</button>
    <div id="Add_supplier_style" style="display:none">
    <div class="Add_Manager_style">
    <div class="add_user_style clearfix">
     <ul class="clearfix">
      <li><label class="label_name">ä¾åºååç§°</label> <input name="ä¾åºååç§°" type="text"  class="text_add"/><i style="color:#F60; ">*</i></li>
      <li><label class="label_name">èç³»äºº</label><input name="ä¾åºåèç³»äºº" type="text"  class="text_add"/><i style="color:#F60; ">*</i></li>
      <li><label class="label_name">èç³»çµè¯</label><input name="ä¾åºåèç³»çµè¯" type="text"  class="text_add"/><i style="color:#F60; ">*</i></li>
      <li><label class="label_name">èç³»é®ç®±</label><input name="ä¾åºåèç³»é®ç®±" type="text"  class="text_add"/></li>
      </ul>    
      <div class="Remark"><label class="label_name">å¤æ³¨</label><textarea name="" cols="" rows="" style=" width:433px; height:200px; padding:5px;"></textarea></div>
<!--      <div class="btn_operating"><button id="submit" type="button" class="btn btn-primary">ä¿å­</button><button  type="button" class="btn btn-warning">éç½®</button></div>-->
      </div>       
      </div>
      </div>
    </div>
    <div class="Manager_style">
     <span class="title_name">å°åºä¿¡æ¯</span>
     <table class="table table-striped table-bordered table-hover">
      <thead>
       <tr>
        <th>åºå·</th>
        <th>å°åºåç§°</th>
        <th>å°åºç¼å·</th>
        <th>ä¸çº§ç¼å·</th>
        <!-- <th>èç³»é®ç®±</th> -->
        <th>å¤æ³¨</th>
        <th>æä½</th>
       </tr>
      </thead>
      <tbody>
       <tr>
        <td>1</td><td>ä¾æåA</td><td>åæå¸®</td><td>13505140602</td><!-- <td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>
        <td><button type="button" class="btn btn-primary">ä¿®æ¹</button> <button type="button" class="btn btn-warning">å é¤</button></td>
       </tr>
       <tr>
        <td>2</td><td>ä¾åºåA</td><td>åæå¸®</td><td>13505140602</td><!-- <td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>
        <td><button type="button" class="btn btn-primary">ä¿®æ¹</button> <button type="button" class="btn btn-warning">å é¤</button></td>
       </tr>
       <tr>
        <td>3</td><td>ä¾åºåA</td><td>åæå¸®</td><td>13505140602</td><!-- <td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>
        <td><button type="button" class="btn btn-primary">ä¿®æ¹</button> <button type="button" class="btn btn-warning">å é¤</button></td>
       </tr>
       <tr>
        <td>4</td><td>ä¾åºåA</td><td>åæå¸®</td><td>13505140602</td><!-- <td>13505140602@qq.com</td> --><td>å¤æ³¨ä¿¡æ¯</td>
        <td><button type="button" class="btn btn-primary">ä¿®æ¹</button> <button type="button" class="btn btn-warning">å é¤</button></td>
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
  ç¬¬
  <select name="" size="1">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
  </select>
  å±2é¡µ
   <a href="" class=" icon-caret-right page_btn"></a>
  <a href="" class="icon-step-forward page_btn"></a>
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
    <script src="assets/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
/***å¤æ­ç¨æ·æ·»å ææ¬**/
  $('#Add_supplier_btn').on('click', function(){
    layer.open({
        type: 1,
        title: 'æ·»å å°åº',
		maxmin: true, 
		shadeClose: true, //ç¹å»é®ç½©å³é­å±
        area : ['600px' , ''],
        content:$('#Add_supplier_style'),
		btn:['æäº¤','åæ¶'],
		yes:function(index, layero){
			 var num=0;
			 var str="";
     $("input[type$='text']").each(function(n){
          if($(this).val()=="")
          {
               
			    layer.alert(str+=""+$(this).attr("name")+"ä¸è½ä¸ºç©ºï¼\r\n",{
                title: 'æç¤ºæ¡',				
				icon:0,	
							
          }); 
		     num++;
             return false;            
          } });
		  if(num>0)
     {
        
          return false;
     }
          else{
			  
			  layer.alert('æ·»å æåï¼',{
               title: 'æç¤ºæ¡',				
			icon:1,		
			  });
			   layer.close(index);	
		  }		  		     				
		}
    });
});
</script>
</body>
</html>
