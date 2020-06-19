package com.yzl.distriEpcTaskService;

import java.util.List;

import com.yzl.pojo.YzlEpc;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;

public interface EpcService {

	//查询所有工程
	List<YzlEpc> findAll(String year, String areaCode);
	//分页查询
	EasyUIResult pageQuery(String value,Integer page,Integer rows);
	//根据用户名查询工程
	YzlEpc findByName(String name);
	//添加工程
	YzlResult addEpc(YzlEpc epc);
	//批量删除
	YzlResult deleterEpc(String [] ids);
	List<YzlEpc> queryByYearAndAreaCodeAndZLLBAndGCLB(String year,String areaCode,String ZLLB,String GCLB);
	List<YzlTaskNumberEpcResult> queryAllAndNumber(String year,String areaCode);
	List<YzlEpc> show();
	//通过小班完成的数据中查询工程
	List<YzlTaskNumberEpcResult> queryByXbData(String year,String disCode);
	//
}
