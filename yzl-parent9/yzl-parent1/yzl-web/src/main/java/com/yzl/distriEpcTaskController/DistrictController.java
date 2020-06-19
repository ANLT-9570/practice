package com.yzl.distriEpcTaskController;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yzl.distriEpcTaskService.DistrictService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.DistrictTreeVO;


/***
 * 地区管理
 * @author administrator_
 *
 */
@Controller
public class DistrictController {
	
	@Autowired
	private DistrictService districtService;
	
	/***
	 * 添加地区
	 * @param district
	 * @return
	 */
	@RequestMapping("district/addDistrict")
	public String addRegion(YzlDistrict district){
		try {
			districtService.save(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/地区";
	}
	
	/***
	 * 分页查询
	 * @return
	 */
	@RequestMapping("district/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(@RequestParam(value="page",defaultValue="1")int page,@RequestParam(value="row",defaultValue="10")int rows){
		EasyUIResult result = districtService.pageQuery(page,rows);
		return result;
	}
	
	/***
	 * 根据Id删除地区
	 * @return
	 */
	@RequestMapping("/district/delete")
	@ResponseBody
	public YzlResult delete(String ids){
		districtService.delete(ids);
		return YzlResult.ok();
	}
	


	/***
	 * 搜索
	 */
	@RequestMapping("/district/search")
	@ResponseBody
	public YzlResult search(String searchKey){
		YzlResult result = districtService.searchByCityOrCounty(searchKey);
		return result;
	}
	
	/***
	 * 查询所有
	 * @param districtFile
	 * @return
	 */
	@RequestMapping("/district/queryDistinctCity")
	@ResponseBody
	public YzlResult queryCity(){
		YzlResult result = districtService.queryDistinctCity();
		return result;
	}
	
	/***
	 * 根据市名查询所有的县/区
	 * @return
	 */
	@RequestMapping("/district/queryCountyByCityName")
	@ResponseBody
	public List<YzlDistrict> queryCountyByCityName(@RequestParam("cityName")String cityName){
		List<YzlDistrict> districts = districtService.queryCountyByCityName(cityName);
		return districts;
	}
	
	
	/*****
	 * xml导入地区数据
	 * @param districtFile
	 * @return
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping(value="district/importXls",method=RequestMethod.POST)
	public String importXls(@RequestParam ("districtFile") CommonsMultipartFile districtFile ){
		int flag=1;
		try {
			//获取上传文件流
			InputStream stream = districtFile.getInputStream();
			//使用POI解析Excel文件
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(stream);
			//去excel的第一个sheet
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			//遍历所有行
			for (Row row : sheet) {
				//获取总行数
				int rowNum = row.getRowNum();
				//第一行是表头，则跳过
				if(rowNum==0){
					continue;
				}
				
				//取当前行的第一个单元格数据 ，以此类推
				String city = row.getCell(0).getStringCellValue();
				String county = row.getCell(1).getStringCellValue();
				//封装数据
				YzlDistrict district = new YzlDistrict();
				district.setCity(city);
				district.setCounty(county);
				//添加到数据库
				districtService.save(district);
			}
		} catch (IOException e) {
			e.printStackTrace();
			flag=0;
		}
		return "地区";
	}
	
	//展示所有地区
	@RequestMapping("/show_diss")
	@ResponseBody
	public List<YzlDistrict> show(String q){
		return districtService.show(q);
	}
	
	//地区校验
	@RequestMapping("/verifyCounty")
	@ResponseBody
	public YzlDistrict ver(String county){
		return districtService.ver(county);
	}
	
	//查询所有地区 树形展示
	@RequestMapping("/district/queryTreeNode")
	@ResponseBody
	public List<DistrictTreeVO> queryTreeNode(HttpServletRequest request){
		return districtService.queryTreeNode(request);
	}
}
