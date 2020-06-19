package com.yzl.mapper;

import com.alibaba.dubbo.config.support.Parameter;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlTaskMapper {
    int countByExample(YzlTaskExample example);

    int deleteByExample(YzlTaskExample example);

    int deleteByPrimaryKey(Integer tcode);

    int insert(YzlTask record);

    int insertSelective(YzlTask record);

    List<YzlTask> selectByExample(YzlTaskExample example);

    YzlTask selectByPrimaryKey(Integer tcode);

    int updateByExampleSelective(@Param("record") YzlTask record, @Param("example") YzlTaskExample example);

    int updateByExample(@Param("record") YzlTask record, @Param("example") YzlTaskExample example);

    int updateByPrimaryKeySelective(YzlTask record);

    int updateByPrimaryKey(YzlTask record);
    
  //根据工程id查询所有任务
    List<YzlTask> queryByXbGCLB(@Param("ecode") int ecode,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("disCode")String disCode,@Param("stats")List<Integer> stats);
//  分局条件查询+分页
    List<YzlTask> selectByCondition(String value);
    //通过任务下发数据的GCLB查询任务
    List<YzlTask> queryByTaskIssuedGCLB(@Param("ecode") Integer ecode,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("areaCode")String areaCode);
    //根据名称查询
	YzlTask selectByTname(String tname);

	//根据编号查询
	YzlTask selectByMark(String zllb);

	List<YzlTask> selectshow();

	List<YzlTask> findByEpcEcode(int ecode);
	//根据工程编号 和 年份统计小班完成作业的任务类别数量
	Integer countyXbTaskNumberByGCLLAndYear(@Param("GCLB")String GCLB,@Param("year")String year,@Param("authoritys")List<String> authoritys,@Param("disCode")String disCode,@Param("stats")List<Integer> stats);
	//根据工程编号 和 年份统计任务下发的任务类别数量
	Integer countyIssueTaskNumberByGCLLAndYear(@Param("areaCode")String areaCode,@Param("GCLB")String GCLB,@Param("year")String year,@Param("authoritys")List<String> authoritys);
	//查询通过工程ecode
	List<YzlTask> findByEpcEcode(Integer ecode);

	List<YzlTask> select();
	//根据造林类别集合查询Epctaskprogress表中所包含的任务
	List<YzlTask> queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("GCLB")String GCLB);
	
	//根据造林类别集合查询xb表中所包含的任务
	List<YzlTask> queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("stats")List<String> stats,@Param("zllb")String zllb);
	//根据造林类别集合查询Epctaskprogress表中所包含的任务
	List<YzlTask> queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(@Param("year")String year,@Param("areaCode")String areaCode,@Param("authoritys")List<String> authoritys,@Param("GCLB")String GCLB,@Param("message")YzlMessage message);

	
}