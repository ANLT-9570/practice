package com.yzl.mapper;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.utils.vo.DistrictTreeVO;
import com.yzl.utils.vo.TreeVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface YzlDistrictMapper {
    int countByExample(YzlDistrictExample example);

    int deleteByExample(YzlDistrictExample example);

    int deleteByPrimaryKey(Integer dcode);

    int insert(YzlDistrict record);

    int insertSelective(YzlDistrict record);

    List<YzlDistrict> selectByExample(YzlDistrictExample example);

    YzlDistrict selectByPrimaryKey(Integer dcode);

    int updateByExampleSelective(@Param("record") YzlDistrict record, @Param("example") YzlDistrictExample example);

    int updateByExample(@Param("record") YzlDistrict record, @Param("example") YzlDistrictExample example);

    int updateByPrimaryKeySelective(YzlDistrict record);

    int updateByPrimaryKey(YzlDistrict record);
    
  //根据地区id查询地区标识
    String findMarkByDcode(@RequestParam("dcode")Integer dcode);

    List<YzlDistrict> findByCityOrCounty(@Param("searchKey") String searchKey);
    //查询所有市级 并去重复
    List<String> findDistinctCity();
    //
    List<Map> queryDistinctCity();
	List<YzlDistrict> selectValue(@Param("q")String q);
	
	YzlDistrict selectByFlag(@Param("flag")String flag);
	
	//根据是名词查询得到地区标志
	YzlDistrict selectByDname(String dname);
	
	String findMarkByCounty(String county);

	List<YzlDistrict> selectFindAllFlag();

	//根据市名称查询所有县
	List<YzlDistrict> selectByCity(@Param("city")String city,@Param("permsList")List<String> permsList);

	//根据县名称查询
	YzlDistrict selectByCounty(String county);
	
	//根据市名查询市行政编号
	String selectCitycodeByCity(String cityName);
	
	//查询所有的市去重
	List<YzlDistrict> selectByDisinctCity(@Param("permsList")List<String> permsList);
	
	//根据标志查询市拥有的县
	List<YzlDistrict> selectByFlags (@Param("flag")String flag,@Param("permsList")List<String> permsList);

	//根据编号查询
	YzlDistrict selectByNumber(String county);
	//根据用户权限 递归查询地区节点
	List<DistrictTreeVO> queryRecursionDistrict(@Param("aNumbers")List<String> aNumbers);

	List<DistrictTreeVO>  findDistrictByCityCode(@Param("aNumbers")List<String> aNumbers,@Param("cityCode")String cityCode);

}