package com.yzl.mapper;

import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcExample;
import com.yzl.pojo.YzlMessage;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlEpcMapper {
    int countByExample(YzlEpcExample example);

    int deleteByExample(YzlEpcExample example);

    int deleteByPrimaryKey(Integer ecode);

    int insert(YzlEpc record);

    int insertSelective(YzlEpc record);

    List<YzlEpc> selectByExample(YzlEpcExample example);

    YzlEpc selectByPrimaryKey(Integer ecode);

    int updateByExampleSelective(@Param("record") YzlEpc record, @Param("example") YzlEpcExample example);

    int updateByExample(@Param("record") YzlEpc record, @Param("example") YzlEpcExample example);

    int updateByPrimaryKeySelective(YzlEpc record);

    int updateByPrimaryKey(YzlEpc record);
    
  //模糊查询
    List<YzlEpc> selectByCondition(String value);

	YzlEpc selectByEname(String ename);

	//根据编号查询
	YzlEpc selectByMark(String gclb);

	List<YzlEpc> gclb();
	//根据任务下发的数据中 查询工程
	List<YzlEpc> queryEpcByTaskProgressData(@Param("year")String year, @Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys);
	//根据小班为审核通过的任务的数据中  根据year 、数据权限
	List<YzlEpc> queryEpcByXbTaskWoring(@Param("year")String year,@Param("disCode")String disCode,@Param("stats")List<Integer> stats,@Param("authoritys")List<String> authoritys);
	//根据造林类别集合查询epctaskprogress下发表中所包含的工程
	List<YzlEpc> queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB);
	
	//根据造林类别集合查询xb表中所包含的工程
	List<YzlEpc> queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("stats")List<String> stats);

	List<YzlEpc> queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("ZLLB")String ZLLB,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);
}