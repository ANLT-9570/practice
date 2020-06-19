package com.yzl.distriEpcTaskService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.HighchartsResultVO;

public interface XbService {

	//highcharts图形显示
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request,String year);
	//统计县自检上报面积 通过County、GCLB、ZLLB进行分组
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLB(HttpServletRequest request,Integer page,Integer rows,String year);
	//统计县自检上报面积 通过County、GCLB、ZLLB进行分组和通过行政地区编号进行查询
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(HttpServletRequest request,Integer page,Integer rows,String areaCode,String year);
	List<HighchartsResultVO> pie(HttpServletRequest request, String year);
}
